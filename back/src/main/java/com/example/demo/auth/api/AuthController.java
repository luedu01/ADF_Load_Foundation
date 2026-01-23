package com.example.demo.auth.api;

import com.example.demo.auth.api.dto.AuthRequest;
import com.example.demo.auth.api.dto.AuthResponse;
import com.example.demo.auth.application.JwtService;
import com.example.demo.config.DatabaseConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Collections;
import java.util.Map;

@RestController
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DatabaseConfig databaseConfig;

    @Value("${app.auth.secret-key}")
    private String secretKey;

    @PostMapping("/authenticate")
    public AuthResponse authenticate(@RequestBody AuthRequest authRequest) {
        String schema = databaseConfig.getSchema();
        String sql = "SELECT u.User_Id, u.User_Name, u.Is_Active, c.Password_Hash " +
                "FROM " + schema + ".\"USER\" u " +
                "JOIN " + schema + ".USER_CREDENTIAL c ON u.User_Id = c.User_Id " +
                "WHERE u.User_Name = ?";

        Map<String, Object> result;
        try {
            result = jdbcTemplate.queryForMap(sql, authRequest.getUsername());
        } catch (EmptyResultDataAccessException e) {
            throw new BadCredentialsException("Credenciales inválidas");
        }

        String storedHash = (String) result.get("Password_Hash");
        if (storedHash == null) {
            throw new BadCredentialsException("Credenciales inválidas");
        }

        String encryptedPassword = encryptPassword(authRequest.getPassword());
        if (!storedHash.equals(encryptedPassword)) {
             throw new BadCredentialsException("Credenciales inválidas");
         }

        String username = (String) result.get("User_Name");
        UserDetails userDetails = new User(username, "", Collections.emptyList());
        String token = jwtService.generateToken(userDetails);
        return new AuthResponse(token);
    }

    private String encryptPassword(String password) {
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            byte[] keyBytes = sha.digest(secretKey.getBytes(StandardCharsets.UTF_8));
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] encryptedBytes = cipher.doFinal(password.getBytes(StandardCharsets.UTF_8));
            String encrypted = Base64.getEncoder().encodeToString(encryptedBytes);
            // System.out.println("Encrypted password: " + encrypted);
            return encrypted;
        } catch (Exception e) {
            throw new RuntimeException("Error encriptando la contraseña", e);
        }
    }
}

package com.load.fundation.auth.application.service;

import com.load.fundation.auth.application.dto.AuthRequest;
import com.load.fundation.auth.application.dto.AuthResponse;
import com.load.fundation.auth.domain.port.in.AuthUseCase;
import com.load.fundation.auth.domain.port.out.UserCredentialPort;
import com.load.fundation.shared.util.EncryptionUtil;
import com.load.fundation.shared.util.constants.AuthMessages;
import com.load.fundation.shared.util.constants.DatabaseColumns;
import com.load.fundation.shared.util.constants.SecurityConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthUseCase {

    private final JwtService jwtService;
    private final UserCredentialPort userCredentialPort;

    @Value("${app.auth.secret-key}")
    private String secretKey;

    @Override
    public AuthResponse authenticate(AuthRequest authRequest) {
        Map<String, Object> result = userCredentialPort.findUserCredentialsByUsername(authRequest.getUsername());

        if (result == null || result.isEmpty()) {
            throw new BadCredentialsException(AuthMessages.INVALID_CREDENTIALS);
        }

        String storedHash = (String) result.get(DatabaseColumns.PASSWORD_HASH);
        if (storedHash == null) {
            throw new BadCredentialsException(AuthMessages.INVALID_CREDENTIALS);
        }

        String encryptedPassword = EncryptionUtil.encryptPassword(authRequest.getPassword(), secretKey);
        if (!storedHash.equals(encryptedPassword)) {
            throw new BadCredentialsException(AuthMessages.INVALID_CREDENTIALS);
        }

        String username = (String) result.get(DatabaseColumns.USER_NAME);
        UserDetails userDetails = new User(username, SecurityConstants.DEFAULT_PASSWORD, Collections.emptyList());
        String token = jwtService.generateToken(userDetails);
        return new AuthResponse(token);
    }
}

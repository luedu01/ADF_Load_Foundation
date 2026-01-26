package com.load.fundation.shared.util;

import com.load.fundation.shared.util.constants.SecurityConstants;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

public class EncryptionUtil {

    public static String encryptPassword(String password, String secretKey) {
        try {
            MessageDigest sha = MessageDigest.getInstance(SecurityConstants.HASH_ALGORITHM_SHA256);
            byte[] keyBytes = sha.digest(secretKey.getBytes(StandardCharsets.UTF_8));
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, SecurityConstants.ENCRYPTION_ALGORITHM_AES);
            Cipher cipher = Cipher.getInstance(SecurityConstants.CIPHER_TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] encryptedBytes = cipher.doFinal(password.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException(SecurityConstants.ENCRYPTION_ERROR_MSG, e);
        }
    }
}

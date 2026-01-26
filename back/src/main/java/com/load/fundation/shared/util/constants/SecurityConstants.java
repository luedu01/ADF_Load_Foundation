package com.load.fundation.shared.util.constants;

/**
 * Constantes de seguridad y encriptación
 */
public final class SecurityConstants {

    private SecurityConstants() {
        throw new UnsupportedOperationException("Esta es una clase de utilidad y no puede ser instanciada");
    }

    // Algoritmos de encriptación
    public static final String HASH_ALGORITHM_SHA256 = "SHA-256";
    public static final String ENCRYPTION_ALGORITHM_AES = "AES";
    public static final String CIPHER_TRANSFORMATION = "AES/ECB/PKCS5Padding";

    // Mensajes de error de encriptación
    public static final String ENCRYPTION_ERROR_MSG = "Error encriptando la contraseña";
    public static final String DECRYPTION_ERROR_MSG = "Error desencriptando datos";

    // Configuración de seguridad
    public static final String DEFAULT_PASSWORD = "";
    public static final String SECURITY_ROLE_USER = "USER";
    public static final String SECURITY_ROLE_ADMIN = "ADMIN";
}

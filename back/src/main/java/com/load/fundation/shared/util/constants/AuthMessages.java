package com.load.fundation.shared.util.constants;

/**
 * Mensajes de error y éxito de autenticación
 */
public final class AuthMessages {

    private AuthMessages() {
        throw new UnsupportedOperationException("Esta es una clase de utilidad y no puede ser instanciada");
    }

    // Mensajes de error de autenticación
    public static final String INVALID_CREDENTIALS = "Credenciales inválidas";
    public static final String USER_NOT_FOUND = "Usuario no encontrado";
    public static final String PASSWORD_HASH_NOT_FOUND = "Hash de contraseña no encontrado";
    public static final String AUTHENTICATION_FAILED = "Autenticación fallida";
    public static final String TOKEN_EXPIRED = "Token expirado";
    public static final String TOKEN_INVALID = "Token inválido";
    public static final String UNAUTHORIZED_ACCESS = "Acceso no autorizado";

    // Mensajes de éxito
    public static final String AUTHENTICATION_SUCCESS = "Autenticación exitosa";
    public static final String TOKEN_GENERATED = "Token generado exitosamente";
}


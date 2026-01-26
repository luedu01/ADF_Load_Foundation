package com.load.fundation.shared.util.constants;

/**
 * Constantes relacionadas con JWT (JSON Web Tokens)
 */
public final class JwtConstants {

    private JwtConstants() {
        throw new UnsupportedOperationException("Esta es una clase de utilidad y no puede ser instanciada");
    }

    // JWT Secret (Nota: En producción debería estar en variables de entorno)
    public static final String JWT_SECRET = "afafasfafafasfasfasfafacasdasfasxASFACASDFACASDFASFASFDAFASFASDAADSCSDFADCVSGCFVADXCcadwavfsfarvf";

    // Algoritmo de firma
    public static final String JWT_ALGORITHM = "HS256";

    // Tiempo de expiración del token (10 horas en milisegundos)
    public static final long JWT_EXPIRATION_MS = 1000L * 60 * 60 * 10;

    // Prefijo del header Authorization
    public static final String TOKEN_PREFIX = "Bearer ";

    // Nombre del header
    public static final String HEADER_STRING = "Authorization";
}

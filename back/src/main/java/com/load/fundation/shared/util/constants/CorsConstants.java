package com.load.fundation.shared.util.constants;

/**
 * Constantes de CORS (Cross-Origin Resource Sharing)
 */
public final class CorsConstants {

    private CorsConstants() {
        throw new UnsupportedOperationException("Esta es una clase de utilidad y no puede ser instanciada");
    }

    // Orígenes permitidos
    public static final String ALLOWED_ORIGIN_LOCALHOST = "http://localhost:3000";

    // Métodos HTTP permitidos
    public static final String METHOD_GET = "GET";
    public static final String METHOD_POST = "POST";
    public static final String METHOD_PUT = "PUT";
    public static final String METHOD_DELETE = "DELETE";
    public static final String METHOD_OPTIONS = "OPTIONS";

    // Headers
    public static final String ALLOW_ALL_HEADERS = "*";

    // Paths
    public static final String ALL_PATHS = "/**";
}

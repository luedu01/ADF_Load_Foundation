package com.load.fundation.shared.util.constants;

/**
 * Constantes para mensajes de respuesta HTTP
 */
public final class HttpMessages {

    private HttpMessages() {
        throw new UnsupportedOperationException("Esta es una clase de utilidad y no puede ser instanciada");
    }

    // Claves de respuesta JSON
    public static final String KEY_TIMESTAMP = "timestamp";
    public static final String KEY_STATUS = "status";
    public static final String KEY_ERROR = "error";
    public static final String KEY_EXCEPTION = "exception";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_PATH = "path";

    // Mensajes de error generales
    public static final String INTERNAL_SERVER_ERROR_MSG = "Error interno del servidor";
    public static final String BAD_REQUEST_MSG = "Solicitud incorrecta";
    public static final String NOT_FOUND_MSG = "Recurso no encontrado";
    public static final String FORBIDDEN_MSG = "Acceso prohibido";
    public static final String UNAUTHORIZED_MSG = "No autorizado";

    // Mensajes de éxito
    public static final String SUCCESS_MSG = "Operación exitosa";
    public static final String CREATED_MSG = "Recurso creado exitosamente";
    public static final String UPDATED_MSG = "Recurso actualizado exitosamente";
    public static final String DELETED_MSG = "Recurso eliminado exitosamente";
}

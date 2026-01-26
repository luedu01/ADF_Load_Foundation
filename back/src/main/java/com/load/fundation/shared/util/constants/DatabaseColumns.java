package com.load.fundation.shared.util.constants;

/**
 * Constantes para nombres de columnas de base de datos
 */
public final class DatabaseColumns {

    private DatabaseColumns() {
        throw new UnsupportedOperationException("Esta es una clase de utilidad y no puede ser instanciada");
    }

    // Columnas de tabla USER
    public static final String USER_ID = "User_Id";
    public static final String USER_NAME = "User_Name";
    public static final String EMAIL_DESC = "Email_Desc";
    public static final String FULL_NAME = "Full_Name";
    public static final String IS_ACTIVE = "Is_Active";
    public static final String LAST_LOGIN_DTTM = "Last_Login_Dttm";
    public static final String CREATED_BY = "Created_By";
    public static final String CREATED_DTTM = "Created_Dttm";
    public static final String UPDATED_BY = "Updated_By";
    public static final String UPDATED_DTTM = "Updated_Dttm";

    // Columnas de tabla USER_CREDENTIAL
    public static final String PASSWORD_HASH = "Password_Hash";

    // Nombres de tablas
    public static final String TABLE_USER = "\"USER\"";
    public static final String TABLE_USER_CREDENTIAL = "USER_CREDENTIAL";

    // Funciones SQL
    public static final String SQL_CURRENT_TIMESTAMP = "CURRENT_TIMESTAMP";
}

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

    //columnas de tabla CONNECTIONS
    public static final String CONNECTION_ID = "Connection_Id";
    public static final String CONNECTION_NAME = "Connection_Name";
    public static final String CONNECTION_DESCRIPTION = "Connection_Description";
    public static final String CONNECTION_TYPE_ID = "Connection_Type_Id";
    public static final String CONNECTION_SUBTYPE_ID = "Connection_Subtype_Id";
    public static final String SERVER_NAME = "Server_Name";
    public static final String ADDITIONAL_INFO = "Additional_Info";

    // Columnas de tabla CONNECTION_GROUP
    public static final String GROUP_ID = "Group_Id";


    // Nombres de tablas
    public static final String TABLE_USER = "\"USER\"";
    public static final String TABLE_USER_CREDENTIAL = "USER_CREDENTIAL";
    public static final String TABLE_CONNECTION = "CONNECTION";
    public static final String TABLE_CONNECTION_GROUP = "CONNECTION_GROUP";

    // Funciones SQL
    public static final String SQL_CURRENT_TIMESTAMP = "CURRENT_TIMESTAMP";
}

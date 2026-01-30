package com.load.fundation.connection.infrastructure.persistence.util.query;

public class SqlQueryConnection {

    //CONNECTIONS BY GROUPID
    public static final String SELECT_CONNECTION_BY_GROUPID = """
    SELECT
        conn.Connection_Id,
        conn.Connection_Name,
        conn.Connection_Description,
        conn.Connection_Type_Id,
        connType.Connection_Type_Name,
        conn.Connection_Subtype_Id,
        connSubtype.Connection_Subtype_Name,
        conn.Server_Name,
        conn.Additional_Info_Desc,
        conn.Created_By,
        conn.Created_Dttm,
        conn.Updated_By,
        conn.Updated_Dttm,
        cred.User_Name      AS Credential_User_Name,
        cred.Password_Hash  AS Credential_Password_Hash,
        tok.Token_Id,
        tok.Token_Name,
        tok.Token_Hash,
        tok.Is_Active       AS Token_Is_Active,
        cg.Group_Id
    FROM LOAD_FND.CONNECTION conn
    JOIN LOAD_FND.CONNECTION_GROUP cg
         ON cg.Connection_Id = conn.Connection_Id
    JOIN LOAD_FND.CONNECTION_TYPE connType
         ON conn.Connection_Type_Id = connType.Connection_Type_Id
    JOIN LOAD_FND.CONNECTION_SUBTYPE connSubtype
         ON conn.Connection_Type_Id    = connSubtype.Connection_Type_Id
          AND conn.Connection_Subtype_Id = connSubtype.Connection_Subtype_Id
    LEFT JOIN LOAD_FND.CONNECTION_CREDENTIAL cred
        ON conn.Connection_Id = cred.Connection_Id
    LEFT JOIN LOAD_FND.CONNECTION_TOKEN tok
    ON conn.Connection_Id = tok.Connection_Id
    AND tok.Is_Active = 'Y'
    WHERE cg.Group_Id = ?
    ORDER BY conn.Connection_Name;
    """;


    //ALL CONNECTIONS
    public static final String SELECT_ALL_CONNECTIONS = """
    SELECT
        conn.Connection_Id,
        conn.Connection_Name,
        conn.Connection_Description,
        conn.Connection_Type_Id,
        connType.Connection_Type_Name,
        conn.Connection_Subtype_Id,
        connSubtype.Connection_Subtype_Name,
        conn.Server_Name,
        conn.Additional_Info_Desc,
        conn.Created_By,
        conn.Created_Dttm,
        conn.Updated_By,
        conn.Updated_Dttm,
        cred.User_Name      AS Credential_User_Name,
        cred.Password_Hash  AS Credential_Password_Hash,
        tok.Token_Id,
        tok.Token_Name,
        tok.Token_Hash,
        tok.Is_Active       AS Token_Is_Active
    FROM LOAD_FND.CONNECTION conn
    JOIN LOAD_FND.CONNECTION_TYPE connType
        ON conn.Connection_Type_Id = connType.Connection_Type_Id
    JOIN LOAD_FND.CONNECTION_SUBTYPE connSubtype
        ON conn.Connection_Type_Id    = connSubtype.Connection_Type_Id
        AND conn.Connection_Subtype_Id = connSubtype.Connection_Subtype_Id
    LEFT JOIN LOAD_FND.CONNECTION_CREDENTIAL cred
        ON conn.Connection_Id = cred.Connection_Id
    LEFT JOIN LOAD_FND.CONNECTION_TOKEN tok
        ON conn.Connection_Id = tok.Connection_Id
       AND tok.Is_Active = 'Y'
    ORDER BY conn.Connection_Name
    """;

  //CONNECTIONS BY ID
    //TODO Requiere inclusion de Joins para (CREDENCIALES,TOKENS,TYPE;SYBTYPE)
    public static final String SELECT_CONNECTION_BY_ID ="""
    SELECT
        conn.Connection_Id,
        conn.Connection_Name,
        conn.Connection_Description,
        conn.Connection_Type_Id,
        connType.Connection_Type_Name,
        conn.Connection_Subtype_Id,
        connSubtype.Connection_Subtype_Name,
        conn.Server_Name,
        conn.Additional_Info_Desc,
        conn.Created_By,
        conn.Created_Dttm,
        conn.Updated_By,
        conn.Updated_Dttm,
        cred.User_Name      AS Credential_User_Name,
        cred.Password_Hash  AS Credential_Password_Hash,
        tok.Token_Id,
        tok.Token_Name,
        tok.Token_Hash,
        tok.Is_Active       AS Token_Is_Active
    FROM LOAD_FND.CONNECTION conn
    JOIN LOAD_FND.CONNECTION_TYPE connType
        ON conn.Connection_Type_Id = connType.Connection_Type_Id
    JOIN LOAD_FND.CONNECTION_SUBTYPE connSubtype
        ON conn.Connection_Type_Id    = connSubtype.Connection_Type_Id
        AND conn.Connection_Subtype_Id = connSubtype.Connection_Subtype_Id
    LEFT JOIN LOAD_FND.CONNECTION_CREDENTIAL cred
        ON conn.Connection_Id = cred.Connection_Id
    LEFT JOIN LOAD_FND.CONNECTION_TOKEN tok
        ON conn.Connection_Id = tok.Connection_Id
       AND tok.Is_Active = 'Y'
    WHERE conn.Connection_Id = ?
    """;
}


package com.load.fundation.connection.infrastructure.persistence.util.query;

public final class SqlQueryConnectionType {

    private SqlQueryConnectionType() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static final String SELECT_ALL_TYPES =
            "SELECT Connection_Type_Id, Connection_Type_Name, Created_By, Created_Dttm, " +
                    "Updated_By, Updated_Dttm FROM %s.CONNECTION_TYPE ORDER BY Connection_Type_Name";

    public static final String SELECT_TYPE_BY_ID =
            "SELECT Connection_Type_Id, Connection_Type_Name, Created_By, Created_Dttm, " +
                    "Updated_By, Updated_Dttm FROM %s.CONNECTION_TYPE WHERE Connection_Type_Id = ?";

    public static final String INSERT_TYPE =
            "INSERT INTO %s.CONNECTION_TYPE (Connection_Type_Id, Connection_Type_Name, " +
                    "Created_By, Created_Dttm, Updated_By, Updated_Dttm) VALUES (?, ?, ?, ?, ?, ?)";

    public static final String UPDATE_TYPE =
            "UPDATE %s.CONNECTION_TYPE SET Connection_Type_Name = ?, Updated_By = ?, Updated_Dttm = ? " +
                    "WHERE Connection_Type_Id = ?";

    public static final String DELETE_TYPE =
            "DELETE FROM %s.CONNECTION_TYPE WHERE Connection_Type_Id = ?";

    public static final String SELECT_EXISTS =
            "SELECT COUNT(1) FROM %s.CONNECTION_TYPE WHERE Connection_Type_Id = ?";
}
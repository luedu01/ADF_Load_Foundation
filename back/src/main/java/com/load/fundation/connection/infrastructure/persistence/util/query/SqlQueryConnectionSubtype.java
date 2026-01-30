package com.load.fundation.connection.infrastructure.persistence.util.query;

public final class SqlQueryConnectionSubtype {

        private SqlQueryConnectionSubtype() {
            throw new UnsupportedOperationException("Utility class");
        }

        public static final String SELECT_ALL_SUBTYPES =
                "SELECT Connection_Subtype_Id, Connection_Type_Id, Connection_Subtype_Name, " +
                        "Created_By, Created_Dttm, Updated_By, Updated_Dttm " +
                        "FROM %s.CONNECTION_SUBTYPE ORDER BY Connection_Subtype_Name";

        public static final String SELECT_SUBTYPES_BY_TYPE =
                "SELECT Connection_Subtype_Id, Connection_Type_Id, Connection_Subtype_Name, " +
                        "Created_By, Created_Dttm, Updated_By, Updated_Dttm " +
                        "FROM %s.CONNECTION_SUBTYPE WHERE Connection_Type_Id = ? ORDER BY Connection_Subtype_Name";

        public static final String SELECT_SUBTYPE_BY_ID =
                "SELECT Connection_Subtype_Id, Connection_Type_Id, Connection_Subtype_Name, " +
                        "Created_By, Created_Dttm, Updated_By, Updated_Dttm " +
                        "FROM %s.CONNECTION_SUBTYPE WHERE Connection_Subtype_Id = ?";

        public static final String INSERT_SUBTYPE =
                "INSERT INTO %s.CONNECTION_SUBTYPE (Connection_Subtype_Id, Connection_Type_Id, " +
                        "Connection_Subtype_Name, Created_By, Created_Dttm, Updated_By, Updated_Dttm) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)";

        public static final String UPDATE_SUBTYPE =
                "UPDATE %s.CONNECTION_SUBTYPE SET Connection_Type_Id = ?, Connection_Subtype_Name = ?, " +
                        "Updated_By = ?, Updated_Dttm = ? WHERE Connection_Subtype_Id = ?";

        public static final String DELETE_SUBTYPE =
                "DELETE FROM %s.CONNECTION_SUBTYPE WHERE Connection_Subtype_Id = ?";

        public static final String SELECT_EXISTS =
                "SELECT COUNT(1) FROM %s.CONNECTION_SUBTYPE WHERE Connection_Subtype_Id = ?";
    }


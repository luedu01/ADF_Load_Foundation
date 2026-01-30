package com.load.fundation.dataset.infrastructure.persistence.util.query;

public final class SqlQueryFieldDatatype {
    private SqlQueryFieldDatatype() { throw new UnsupportedOperationException("Utility class"); }

    public static final String SELECT_ALL_DATATYPES =
            "SELECT Field_Datatype_Id, Datatype_Name, Datatype_Description, Created_By, Created_Dttm, Updated_By, Updated_Dttm FROM %s.SOURCE_FIELD_DATATYPE ORDER BY Field_Datatype_Id";

    public static final String SELECT_DATATYPE_BY_ID =
            "SELECT Field_Datatype_Id, Datatype_Name, Datatype_Description, Created_By, Created_Dttm, Updated_By, Updated_Dttm FROM %s.SOURCE_FIELD_DATATYPE WHERE Field_Datatype_Id = ?";

    public static final String SELECT_DATATYPES_BASE =
            "SELECT Field_Datatype_Id, Datatype_Name, Datatype_Description, Created_By, Created_Dttm, Updated_By, Updated_Dttm FROM %s.SOURCE_FIELD_DATATYPE WHERE 1=1";

    public static final String INSERT_DATATYPE =
            "INSERT INTO %s.SOURCE_FIELD_DATATYPE (Field_Datatype_Id, Datatype_Name, Datatype_Description, Created_By, Created_Dttm, Updated_By, Updated_Dttm) VALUES (?, ?, ?, ?, ?, ?, ?)";

    public static final String UPDATE_DATATYPE =
            "UPDATE %s.SOURCE_FIELD_DATATYPE SET Datatype_Name = ?, Datatype_Description = ?, Updated_By = ?, Updated_Dttm = ? WHERE Field_Datatype_Id = ?";

    public static final String DELETE_DATATYPE =
            "DELETE FROM %s.SOURCE_FIELD_DATATYPE WHERE Field_Datatype_Id = ?";

    public static final String SELECT_EXISTS =
            "SELECT COUNT(1) FROM %s.SOURCE_FIELD_DATATYPE WHERE Field_Datatype_Id = ?";
}

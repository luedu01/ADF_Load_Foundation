package com.load.fundation.dataset.infrastructure.persistence.util.query;

public final class SqlQueryFieldSemantic {
    private SqlQueryFieldSemantic() { throw new UnsupportedOperationException("Utility class"); }

    public static final String SELECT_ALL =
            "SELECT Field_Semantic_Datatype_Id, Field_Datatype_Id, DataType_Name, DataType_Description, Created_By, Created_Dttm, Updated_By, Updated_Dttm FROM %s.SOURCE_FIELD_SEMANTIC_DATATYPE ORDER BY Field_Semantic_Datatype_Id";

    public static final String SELECT_BY_ID =
            "SELECT Field_Semantic_Datatype_Id, Field_Datatype_Id, DataType_Name, DataType_Description, Created_By, Created_Dttm, Updated_By, Updated_Dttm FROM %s.SOURCE_FIELD_SEMANTIC_DATATYPE WHERE Field_Semantic_Datatype_Id = ?";

    public static final String INSERT =
            "INSERT INTO %s.SOURCE_FIELD_SEMANTIC_DATATYPE (Field_Semantic_Datatype_Id, Field_Datatype_Id, DataType_Name, DataType_Description, Created_By, Created_Dttm, Updated_By, Updated_Dttm) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String UPDATE =
            "UPDATE %s.SOURCE_FIELD_SEMANTIC_DATATYPE SET Field_Datatype_Id = ?, DataType_Name = ?, DataType_Description = ?, Updated_By = ?, Updated_Dttm = ? WHERE Field_Semantic_Datatype_Id = ?";

    public static final String DELETE =
            "DELETE FROM %s.SOURCE_FIELD_SEMANTIC_DATATYPE WHERE Field_Semantic_Datatype_Id = ?";

    public static final String SELECT_EXISTS =
            "SELECT COUNT(1) FROM %s.SOURCE_FIELD_SEMANTIC_DATATYPE WHERE Field_Semantic_Datatype_Id = ?";
}

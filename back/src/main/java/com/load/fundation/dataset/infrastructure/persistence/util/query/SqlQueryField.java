package com.load.fundation.dataset.infrastructure.persistence.util.query;

public final class SqlQueryField {
    private SqlQueryField() { throw new UnsupportedOperationException("Utility class"); }

    public static final String SELECT_ALL_FIELDS =
            "SELECT Field_Id, Schema_Id, Position_Num, Field_Name, Field_Description, Field_Datatype_Id, " +
            "Length, Precision, Is_Nullable, Is_Primary_Key, Field_Semantic_Datatype_Id, Source_Path_Expression, " +
            "Created_By, Created_Dttm, Updated_By, Updated_Dttm FROM %s.SOURCE_FIELD ORDER BY Field_Id";

    public static final String SELECT_FIELD_BY_ID =
            "SELECT Field_Id, Schema_Id, Position_Num, Field_Name, Field_Description, Field_Datatype_Id, " +
            "Length, Precision, Is_Nullable, Is_Primary_Key, Field_Semantic_Datatype_Id, Source_Path_Expression, " +
            "Created_By, Created_Dttm, Updated_By, Updated_Dttm FROM %s.SOURCE_FIELD WHERE Field_Id = ?";

    // base SELECT used for dynamic WHERE construction in findByCriteria
    public static final String SELECT_FIELDS_BASE =
            "SELECT Field_Id, Schema_Id, Position_Num, Field_Name, Field_Description, Field_Datatype_Id, " +
            "Length, Precision, Is_Nullable, Is_Primary_Key, Field_Semantic_Datatype_Id, Source_Path_Expression, " +
            "Created_By, Created_Dttm, Updated_By, Updated_Dttm FROM %s.SOURCE_FIELD WHERE 1=1";

    public static final String INSERT_FIELD =
            "INSERT INTO %s.SOURCE_FIELD (Field_Id, Schema_Id, Position_Num, Field_Name, Field_Description, Field_Datatype_Id, " +
            "Length, Precision, Is_Nullable, Is_Primary_Key, Field_Semantic_Datatype_Id, Source_Path_Expression, Created_By, Created_Dttm, Updated_By, Updated_Dttm) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String UPDATE_FIELD =
            "UPDATE %s.SOURCE_FIELD SET Schema_Id = ?, Position_Num = ?, Field_Name = ?, Field_Description = ?, Field_Datatype_Id = ?, " +
            "Length = ?, Precision = ?, Is_Nullable = ?, Is_Primary_Key = ?, Field_Semantic_Datatype_Id = ?, Source_Path_Expression = ?, Updated_By = ?, Updated_Dttm = ? WHERE Field_Id = ?";

    public static final String DELETE_FIELD =
            "DELETE FROM %s.SOURCE_FIELD WHERE Field_Id = ?";

    public static final String SELECT_EXISTS = "SELECT COUNT(1) FROM %s.SOURCE_FIELD WHERE Field_Id = ?";
}

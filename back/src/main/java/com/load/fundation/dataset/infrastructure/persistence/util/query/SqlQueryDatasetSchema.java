package com.load.fundation.dataset.infrastructure.persistence.util.query;

public final class SqlQueryDatasetSchema {
    private SqlQueryDatasetSchema() { throw new UnsupportedOperationException("Utility class"); }

    public static final String SELECT_ALL_SCHEMAS =
            "SELECT Schema_Id, Dataset_Id, Dataset_Schema_Version_Num, Is_Draft, Is_Current, Effective_From_Dttm, Effective_To_Dttm, Notes_Txt, Created_By, Created_Dttm, Updated_By, Updated_Dttm FROM %s.SOURCE_DATASET_SCHEMA ORDER BY Schema_Id";

    public static final String SELECT_SCHEMA_BY_ID =
            "SELECT Schema_Id, Dataset_Id, Dataset_Schema_Version_Num, Is_Draft, Is_Current, Effective_From_Dttm, Effective_To_Dttm, Notes_Txt, Created_By, Created_Dttm, Updated_By, Updated_Dttm FROM %s.SOURCE_DATASET_SCHEMA WHERE Schema_Id = ?";

    public static final String SELECT_SCHEMAS_BASE =
            "SELECT Schema_Id, Dataset_Id, Dataset_Schema_Version_Num, Is_Draft, Is_Current, Effective_From_Dttm, Effective_To_Dttm, Notes_Txt, Created_By, Created_Dttm, Updated_By, Updated_Dttm FROM %s.SOURCE_DATASET_SCHEMA WHERE 1=1";

    public static final String INSERT_SCHEMA =
            "INSERT INTO %s.SOURCE_DATASET_SCHEMA (Schema_Id, Dataset_Id, Dataset_Schema_Version_Num, Is_Draft, Is_Current, Effective_From_Dttm, Effective_To_Dttm, Notes_Txt, Created_By, Created_Dttm, Updated_By, Updated_Dttm) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String UPDATE_SCHEMA =
            "UPDATE %s.SOURCE_DATASET_SCHEMA SET Dataset_Id = ?, Dataset_Schema_Version_Num = ?, Is_Draft = ?, Is_Current = ?, Effective_From_Dttm = ?, Effective_To_Dttm = ?, Notes_Txt = ?, Updated_By = ?, Updated_Dttm = ? WHERE Schema_Id = ?";

    public static final String DELETE_SCHEMA =
            "DELETE FROM %s.SOURCE_DATASET_SCHEMA WHERE Schema_Id = ?";

    public static final String SELECT_EXISTS =
            "SELECT COUNT(1) FROM %s.SOURCE_DATASET_SCHEMA WHERE Schema_Id = ?";
}

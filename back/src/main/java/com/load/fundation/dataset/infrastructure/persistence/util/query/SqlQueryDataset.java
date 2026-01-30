package com.load.fundation.dataset.infrastructure.persistence.util.query;

public final class SqlQueryDataset {

    private SqlQueryDataset() { throw new UnsupportedOperationException("Utility class"); }

    public static final String SELECT_ALL_DATASETS =
            "SELECT Dataset_Id, Connection_Id, Dataset_Name, Is_Active, Dataset_Description, " +
            "Dataset_Extraction_Query_Description, Additional_Info_Json, Created_By, Created_Dttm, Updated_By, Updated_Dttm FROM %s.SOURCE_DATASET";

    public static final String SELECT_DATASET_BY_ID =
            "SELECT Dataset_Id, Connection_Id, Dataset_Name, Is_Active, Dataset_Description, " +
            "Dataset_Extraction_Query_Description, Additional_Info_Json, Created_By, Created_Dttm, Updated_By, Updated_Dttm FROM %s.SOURCE_DATASET WHERE Dataset_Id = ?";

    public static final String SELECT_DATASETS_BASE =
            "SELECT Dataset_Id, Connection_Id, Dataset_Name, Is_Active, Dataset_Description, " +
            "Dataset_Extraction_Query_Description, Additional_Info_Json, Created_By, Created_Dttm, Updated_By, Updated_Dttm FROM %s.SOURCE_DATASET WHERE 1=1";

    public static final String INSERT_DATASET =
            "INSERT INTO %s.SOURCE_DATASET (Dataset_Id, Connection_Id, Dataset_Name, Is_Active, Dataset_Description, " +
            "Dataset_Extraction_Query_Description, Additional_Info_Json, Created_By, Created_Dttm, Updated_By, Updated_Dttm) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String UPDATE_DATASET =
            "UPDATE %s.SOURCE_DATASET SET Connection_Id = ?, Dataset_Name = ?, Is_Active = ?, Dataset_Description = ?, " +
            "Dataset_Extraction_Query_Description = ?, Additional_Info_Json = ?, Updated_By = ?, Updated_Dttm = ? WHERE Dataset_Id = ?";

    public static final String DELETE_DATASET =
            "DELETE FROM %s.SOURCE_DATASET WHERE Dataset_Id = ?";

    public static final String SELECT_EXISTS = "SELECT COUNT(1) FROM %s.SOURCE_DATASET WHERE Dataset_Id = ?";

    public static final String SELECT_EXISTS_BY_CONNECTION_ID = "SELECT COUNT(1) FROM %s.CONNECTION WHERE Connection_Id = ?";

}

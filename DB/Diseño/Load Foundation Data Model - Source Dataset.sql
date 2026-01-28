CREATE MULTISET  TABLE LOAD_FND.SOURCE_DATASET
(

	Dataset_Id           INTEGER NOT NULL ,
	Connection_Id        INTEGER NULL ,
	Dataset_Name         VARCHAR(100) NULL ,
	Is_Active            CHAR(1) NULL ,
	Dataset_Description  VARCHAR(500) NULL ,
	Dataset_Extraction_Query_Description VARCHAR(5000) NULL ,
	Additional_Info_Json JSON(32000) NULL ,
	Created_By           INTEGER NULL ,
	Created_Dttm         TIMESTAMP(6) NULL ,
	Updated_By           INTEGER NULL ,
	Updated_Dttm         TIMESTAMP(6) NULL 
)
	 UNIQUE PRIMARY INDEX XPKSOURCE_DATASET
	 (
			Dataset_Id
	 );

COMMENT ON COLUMN LOAD_FND.SOURCE_DATASET.Dataset_Extraction_Query_Description IS 'En este campo se almacenará el query origen, en el caso de ser de tipo tabla de base de datos, o el nombre del archivo a extraer, por ejemplo SIGT_PPPS_*.json.';

COMMENT ON COLUMN LOAD_FND.SOURCE_DATASET.Additional_Info_Json IS 'Estructura JSON para el almacenamiento en formato clave-valor de atributos propios del tipo de conexión que no apliquen de manera genérica a todos los data sets, por ejemplo el esquema de la base de datos.';

CREATE MULTISET  TABLE LOAD_FND.SOURCE_DATASET_SCHEMA
(

	Schema_Id            INTEGER NOT NULL ,
	Dataset_Id           INTEGER NULL ,
	Dataset_Schema_Version_Num INTEGER NULL ,
	Is_Draft             CHAR(1) NULL ,
	Is_Current           CHAR(1) NULL ,
	Effective_From_Dttm  TIMESTAMP(6) NULL ,
	Effective_To_Dttm    TIMESTAMP(6) NULL ,
	Notes_Txt            VARCHAR(5000) NULL ,
	Created_By           INTEGER NULL ,
	Created_Dttm         TIMESTAMP(6) NULL ,
	Updated_By           INTEGER NULL ,
	Updated_Dttm         TIMESTAMP(6) NULL 
)
	 UNIQUE PRIMARY INDEX XPKSOURCE_DATASET_SCHEMA
	 (
			Schema_Id
	 );

CREATE UNIQUE INDEX XAK1SOURCE_DATASET_SCHEMA
 (
		Dataset_Id,
		Dataset_Schema_Version_Num
 ) ON LOAD_FND.SOURCE_DATASET_SCHEMA;

CREATE MULTISET  TABLE LOAD_FND.SOURCE_FIELD
(

	Field_Id             INTEGER NOT NULL ,
	Schema_Id            INTEGER NULL ,
	Position_Num         INTEGER NULL ,
	Field_Name           VARCHAR(100) NULL ,
	Field_Description    VARCHAR(250) NULL ,
	Field_Datatype_Id    INTEGER NULL ,
	Length               INTEGER NULL ,
	Precision            INTEGER NULL ,
	Is_Nullable          CHAR(1) NULL ,
	Is_Primary_Key       CHAR(1) NULL ,
	Field_Semantic_Datatype_Id INTEGER NULL ,
	Source_Path_Expression_Txt VARCHAR(5000) NULL ,
	Created_By           INTEGER NULL ,
	Created_Dttm         TIMESTAMP(6) NULL ,
	Updated_By           INTEGER NULL ,
	Updated_Dttm         TIMESTAMP(6) NULL 
)
	 UNIQUE PRIMARY INDEX XPKSOURCE_FIELD
	 (
			Field_Id
	 );

COMMENT ON COLUMN LOAD_FND.SOURCE_FIELD.Source_Path_Expression_Txt IS 'Campo utilizado para archivos JSON y XML que define la ruta de recuperación del dato dentro del archivo, por ejemplo $.a.b[0]';

CREATE MULTISET  TABLE LOAD_FND.SOURCE_FIELD_DATATYPE
(

	Field_Datatype_Id    INTEGER NOT NULL ,
	DataType_Name        VARCHAR(100) NULL ,
	Datatype_Description VARCHAR(250) NULL ,
	Created_By           INTEGER NULL ,
	Created_Dttm         TIMESTAMP(6) NULL ,
	Updated_By           INTEGER NULL ,
	Updated_Dttm         TIMESTAMP(6) NULL 
)
	 UNIQUE PRIMARY INDEX XPKSOURCE_FIELD_DATATYPE
	 (
			Field_Datatype_Id
	 );

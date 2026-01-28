CREATE MULTISET  TABLE LOAD_FND.CONNECTION
(

	Connection_Id        INTEGER NOT NULL ,
	Connection_Name      VARCHAR(100) NULL ,
	Connection_Description VARCHAR(250) NULL ,
	Connection_Subtype_Id INTEGER NULL ,
	Connection_Type_Id   INTEGER NULL ,
	Server_Name          VARCHAR(100) NULL ,
	Additional_Info_Json JSON(32000) NULL ,
	Created_By           INTEGER NULL ,
	Created_Dttm         TIMESTAMP(6) NULL ,
	Updated_By           INTEGER NULL ,
	Updated_Dttm         TIMESTAMP(6) NULL 
)
	 UNIQUE PRIMARY INDEX XPKCONNECTION
	 (
			Connection_Id
	 );

CREATE MULTISET  TABLE LOAD_FND.CONNECTION_CREDENTIAL
(

	Connection_Id        INTEGER NOT NULL ,
	User_Name            VARCHAR(100) NULL ,
	Password_Hash        VARCHAR(100) NULL ,
	Created_By           INTEGER NULL ,
	Created_Dttm         TIMESTAMP(6) NULL ,
	Updated_By           INTEGER NULL ,
	Updated_Dttm         TIMESTAMP(6) NULL 
)
	 UNIQUE PRIMARY INDEX XPKCONNECTION_CREDENTIAL
	 (
			Connection_Id
	 );

CREATE MULTISET  TABLE LOAD_FND.CONNECTION_GROUP
(

	Connection_Id        INTEGER NOT NULL ,
	Group_Id             INTEGER NOT NULL ,
	Created_By           INTEGER NULL ,
	Created_Dttm         TIMESTAMP(6) NULL ,
	Updated_By           INTEGER NULL ,
	Updated_Dttm         TIMESTAMP(6) NULL 
)
	 UNIQUE PRIMARY INDEX XPKCONNECTION_GROUP
	 (
			Connection_Id,
			Group_Id
	 );

CREATE MULTISET  TABLE LOAD_FND.CONNECTION_SUBTYPE
(

	Connection_Subtype_Id INTEGER NOT NULL ,
	Connection_Type_Id   INTEGER NOT NULL ,
	Connection_Subtype_Name VARCHAR(100) NULL ,
	Created_By           INTEGER NULL ,
	Created_Dttm         TIMESTAMP(6) NULL ,
	Updated_By           INTEGER NULL ,
	Updated_Dttm         TIMESTAMP(6) NULL 
)
	 UNIQUE PRIMARY INDEX XPKCONNECTION_SUBTYPE
	 (
			Connection_Subtype_Id,
			Connection_Type_Id
	 );

CREATE MULTISET  TABLE LOAD_FND.CONNECTION_TOKEN
(

	Token_Id             INTEGER NOT NULL ,
	Connection_Id        INTEGER NULL ,
	Token_Name           VARCHAR(100) NULL ,
	Token_Hash           VARCHAR(100) NULL ,
	Is_Active            CHAR(1) NULL ,
	Created_By           INTEGER NULL ,
	Created_Dttm         TIMESTAMP(6) NULL ,
	Updated_By           INTEGER NULL ,
	Updated_Dttm         TIMESTAMP(6) NULL ,
	Revoked_By           INTEGER NULL ,
	Revoked_Dttm         TIMESTAMP(6) NULL 
)
	 UNIQUE PRIMARY INDEX XPKCONNECTION_TOKEN
	 (
			Token_Id
	 );

CREATE MULTISET  TABLE LOAD_FND.CONNECTION_TYPE
(

	Connection_Type_Id   INTEGER NOT NULL ,
	Connection_Type_Name VARCHAR(100) NULL ,
	Created_By           INTEGER NULL ,
	Created_Dttm         TIMESTAMP(6) NULL ,
	Updated_By           INTEGER NULL ,
	Updated_Dttm         TIMESTAMP(6) NULL 
)
	 UNIQUE PRIMARY INDEX XPKCONNECTION_TYPE
	 (
			Connection_Type_Id
	 );

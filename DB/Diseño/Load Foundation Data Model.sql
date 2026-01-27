CREATE MULTISET  TABLE LOAD_FND.AUDIT_TRAIL
(

	Audit_Trail_Id       INTEGER NOT NULL ,
	Audit_Type_Cd        VARCHAR(50) NULL ,
	Created_By           INTEGER NULL ,
	Created_Dttm         TIMESTAMP(6) NULL ,
	Table_Name           VARCHAR(100) NULL ,
	Previous_Data_Txt    VARCHAR(5000) NULL 
)
	 UNIQUE PRIMARY INDEX XPKAUDIT_TRAIL
	 (
			Audit_Trail_Id
	 );

CREATE MULTISET  TABLE LOAD_FND.AUTH_TOKEN
(

	Token_Id             INTEGER NOT NULL ,
	User_Id              INTEGER NULL ,
	Token_Hash           VARCHAR(100) NULL ,
	Token_Type_Cd        VARCHAR(250) NULL ,
	Is_Active            CHAR(1) NULL ,
	Created_By           INTEGER NULL ,
	Created_Dttm         TIMESTAMP(6) NULL ,
	Updated_By           INTEGER NULL ,
	Updated_Dttm         TIMESTAMP(6) NULL ,
	Revoked_By           INTEGER NULL ,
	Revoked_Dttm         TIMESTAMP(6) NULL 
)
	 UNIQUE PRIMARY INDEX XPKAUTH_TOKEN
	 (
			Token_Id
	 );

CREATE MULTISET  TABLE LOAD_FND.ENVIRONMENT
(

	Environment_Id       INTEGER NOT NULL ,
	Environment_Name     VARCHAR(100) NULL ,
	Environment_Description VARCHAR(250) NULL ,
	Created_By           INTEGER NULL ,
	Created_Dttm         TIMESTAMP(6) NULL ,
	Updated_By           INTEGER NULL ,
	Updated_Dttm         TIMESTAMP(6) NULL 
)
	 UNIQUE PRIMARY INDEX XPKENVIRONMENT
	 (
			Environment_Id
	 );

CREATE MULTISET  TABLE LOAD_FND.GROUP
(

	Group_Id             INTEGER NOT NULL ,
	Group_Name           VARCHAR(100) NULL ,
	Group_Description    VARCHAR(250) NULL ,
	Created_By           INTEGER NULL ,
	Created_Dttm         TIMESTAMP(6) NULL ,
	Updated_By           INTEGER NULL ,
	Updated_Dttm         TIMESTAMP(6) NULL 
)
	 UNIQUE PRIMARY INDEX XPKGROUP
	 (
			Group_Id
	 );

CREATE MULTISET  TABLE LOAD_FND.PERMISSION
(

	Permission_Id        INTEGER NOT NULL ,
	Permission_Name      VARCHAR(100) NULL ,
	Resource_Name        VARCHAR(250) NULL ,
	Created_By           INTEGER NULL ,
	Created_Dttm         TIMESTAMP(6) NULL ,
	Updated_By           INTEGER NULL ,
	Updated_Dttm         TIMESTAMP(6) NULL 
)
	 UNIQUE PRIMARY INDEX XPKPERMISSION
	 (
			Permission_Id
	 );

CREATE MULTISET  TABLE LOAD_FND.ROL
(

	Rol_Id               INTEGER NOT NULL ,
	Rol_Name             VARCHAR(100) NULL ,
	Rol_Description      VARCHAR(250) NULL ,
	Created_By           INTEGER NULL ,
	Created_Dttm         TIMESTAMP(6) NULL ,
	Updated_By           INTEGER NULL ,
	Updated_Dttm         TIMESTAMP(6) NULL 
)
	 UNIQUE PRIMARY INDEX XPKROL
	 (
			Rol_Id
	 );

CREATE MULTISET  TABLE LOAD_FND.ROLE_PERMISSION
(

	Rol_Id               INTEGER NOT NULL ,
	Permission_Id        INTEGER NOT NULL ,
	Created_By           INTEGER NULL ,
	Created_Dttm         TIMESTAMP(6) NULL ,
	Updated_By           INTEGER NULL ,
	Updated_Dttm         TIMESTAMP(6) NULL 
)
	 UNIQUE PRIMARY INDEX XPKROLE_PERMISSION
	 (
			Rol_Id,
			Permission_Id
	 );

CREATE MULTISET  TABLE LOAD_FND.TARGETS
(

	Target_Id            INTEGER NOT NULL ,
	Target_Name          VARCHAR(100) NULL ,
	Target_Description   VARCHAR(250) NULL ,
	Environment_Id       INTEGER NULL ,
	Created_By           INTEGER NULL ,
	Created_Dttm         TIMESTAMP(6) NULL ,
	Updated_By           INTEGER NULL ,
	Updated_Dttm         TIMESTAMP(6) NULL 
)
	 UNIQUE PRIMARY INDEX XPKTARGETS
	 (
			Target_Id
	 );

CREATE MULTISET  TABLE LOAD_FND."USER"
(

	User_Id              INTEGER NOT NULL ,
	User_Name            VARCHAR(100) NULL ,
	Email_Desc           VARCHAR(100) NULL ,
	Full_Name            VARCHAR(250) NULL ,
	Is_Active            CHAR(1) NULL ,
	Last_Login_Dttm      TIMESTAMP(6) NULL ,
	Created_By           INTEGER NULL ,
	Created_Dttm         TIMESTAMP(6) NULL ,
	Updated_By           INTEGER NULL ,
	Updated_Dttm         TIMESTAMP(6) NULL 
)
	 UNIQUE PRIMARY INDEX XPKUSER
	 (
			User_Id
	 );

CREATE MULTISET  TABLE LOAD_FND.USER_CREDENTIAL
(

	User_Id              INTEGER NOT NULL ,
	Password_Hash        VARCHAR(100) NULL ,
	Password_Updated_Dttm TIMESTAMP(6) NULL ,
	Created_By           INTEGER NULL ,
	Created_Dttm         TIMESTAMP(6) NULL ,
	Updated_By           INTEGER NULL ,
	Updated_Dttm         TIMESTAMP(6) NULL 
)
	 UNIQUE PRIMARY INDEX XPKUSER_CREDENTIAL
	 (
			User_Id
	 );

CREATE MULTISET  TABLE LOAD_FND.USER_ENVIRONMENT
(

	User_Id              INTEGER NOT NULL ,
	Environment_Id       INTEGER NOT NULL ,
	Created_By           INTEGER NULL ,
	Created_Dttm         TIMESTAMP(6) NULL ,
	Updated_By           INTEGER NULL ,
	Updated_Dttm         TIMESTAMP(6) NULL 
)
	 UNIQUE PRIMARY INDEX XPKUSER_ENVIRONMENT
	 (
			User_Id,
			Environment_Id
	 );

CREATE MULTISET  TABLE LOAD_FND.USER_GROUP
(

	User_Id              INTEGER NOT NULL ,
	Group_Id             INTEGER NOT NULL ,
	Created_By           INTEGER NULL ,
	Created_Dttm         TIMESTAMP(6) NULL ,
	Updated_By           INTEGER NULL ,
	Updated_Dttm         TIMESTAMP(6) NULL 
)
	 UNIQUE PRIMARY INDEX XPKUSER_GROUP
	 (
			User_Id,
			Group_Id
	 );

CREATE MULTISET  TABLE LOAD_FND.USER_ROL
(

	User_Id              INTEGER NOT NULL ,
	Rol_Id               INTEGER NOT NULL ,
	Created_By           INTEGER NULL ,
	Created_Dttm         TIMESTAMP(6) NULL ,
	Updated_By           INTEGER NULL ,
	Updated_Dttm         TIMESTAMP(6) NULL 
)
	 UNIQUE PRIMARY INDEX XPKUSER_ROL
	 (
			User_Id,
			Rol_Id
	 );

CREATE MULTISET  TABLE LOAD_FND.USER_TARGET
(

	User_Id              INTEGER NOT NULL ,
	Target_Id            INTEGER NOT NULL ,
	Created_By           INTEGER NULL ,
	Created_Dttm         TIMESTAMP(6) NULL ,
	Updated_By           INTEGER NULL ,
	Updated_Dttm         TIMESTAMP(6) NULL 
)
	 UNIQUE PRIMARY INDEX XPKUSER_TARGET
	 (
			User_Id,
			Target_Id
	 );


COMMENT ON DATABASE LOAD_FND IS 'Base de datos del load foundation framework de Teradata.';

CREATE MULTISET  TABLE LOAD_FND.CONNECTION
(

	Connection_Id        INTEGER NOT NULL ,
	Connection_Name      VARCHAR(100) NULL ,
	Connection_Description VARCHAR(250) NULL ,
	Connection_Subtype_Id INTEGER NULL ,
	Connection_Type_Id   INTEGER NULL ,
	Server_Name          VARCHAR(100) NULL ,
	Additional_Info_Desc VARCHAR(200) NULL ,
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

COMMENT ON TABLE LOAD_FND.CONNECTION_TYPE IS 'Tipos de conexión origen.

Ejemplos:
- Database
- Local
- SFTP
- Blob Storage
- API';

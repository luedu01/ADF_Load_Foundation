package com.load.fundation.user.infrastructure.persistence.util.query;

public final class SqlQueryUser {
    private SqlQueryUser() { throw new UnsupportedOperationException("Utility class"); }

    public static final String SELECT_ALL_USERS =
            "SELECT User_Id, User_Name, Email_Desc, Full_Name, Is_Active, Last_Login_Dttm, Created_By, Created_Dttm, Updated_By, Updated_Dttm FROM %s.\"USER\"";

    public static final String SELECT_USER_BY_ID =
            "SELECT User_Id, User_Name, Email_Desc, Full_Name, Is_Active, Last_Login_Dttm, Created_By, Created_Dttm, Updated_By, Updated_Dttm FROM %s.\"USER\" WHERE User_Id = ?";

    public static final String INSERT_USER =
            "INSERT INTO %s.\"USER\" (User_Id, User_Name, Email_Desc, Full_Name, Is_Active, Last_Login_Dttm, Created_By, Created_Dttm, Updated_By, Updated_Dttm) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String UPDATE_USER =
            "UPDATE %s.\"USER\" SET User_Name = ?, Email_Desc = ?, Full_Name = ?, Is_Active = ?, Last_Login_Dttm = ?, Updated_By = ?, Updated_Dttm = ? WHERE User_Id = ?";

    public static final String DELETE_USER =
            "DELETE FROM %s.\"USER\" WHERE User_Id = ?";

    public static final String SELECT_USER_CREDENTIALS =
            "SELECT u.User_Id, u.User_Name, u.Is_Active, c.Password_Hash FROM %s.\"USER\" u JOIN %s.USER_CREDENTIAL c ON u.User_Id = c.User_Id WHERE u.User_Name = ?";
}

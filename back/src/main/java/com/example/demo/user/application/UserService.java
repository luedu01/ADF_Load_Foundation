package com.example.demo.user.application;

import com.example.demo.config.DatabaseConfig;
import com.example.demo.user.api.dto.UserDto;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class UserService {

    private final JdbcTemplate jdbcTemplate;
    private final DatabaseConfig databaseConfig;

    public UserService(JdbcTemplate jdbcTemplate, DatabaseConfig databaseConfig) {
        this.jdbcTemplate = jdbcTemplate;
        this.databaseConfig = databaseConfig;
    }

    public List<UserDto> getUsers() {
        String schema = databaseConfig.getSchema();
        String sql = "SELECT User_Id, User_Name, Email_Desc, Full_Name, Is_Active, Last_Login_Dttm, Created_By, Created_Dttm, Updated_By, Updated_Dttm FROM " + schema + ".\"USER\"";
        return jdbcTemplate.query(sql, userRowMapper());
    }

    public UserDto getUserById(Integer userId) {
        String schema = databaseConfig.getSchema();
        String sql = "SELECT User_Id, User_Name, Email_Desc, Full_Name, Is_Active, Last_Login_Dttm, Created_By, Created_Dttm, Updated_By, Updated_Dttm FROM " + schema + ".\"USER\" WHERE User_Id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, userRowMapper(), userId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void createUser(UserDto user) {
        String schema = databaseConfig.getSchema();
        String sql = "INSERT INTO " + schema + ".\"USER\" (User_Id, User_Name, Email_Desc, Full_Name, Is_Active, Last_Login_Dttm, Created_By, Created_Dttm, Updated_By, Updated_Dttm) VALUES (?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, ?, CURRENT_TIMESTAMP)";
        jdbcTemplate.update(sql,
                user.getUserId(),
                user.getUserName(),
                user.getEmailDesc(),
                user.getFullName(),
                user.getIsActive(),
                user.getLastLoginDttm(),
                user.getCreatedBy(),
                user.getUpdatedBy());
    }

    public void updateUser(Integer userId, UserDto user) {
        String schema = databaseConfig.getSchema();
        String sql = "UPDATE " + schema + ".\"USER\" SET User_Name = ?, Email_Desc = ?, Full_Name = ?, Is_Active = ?, Last_Login_Dttm = ?, Updated_By = ?, Updated_Dttm = CURRENT_TIMESTAMP WHERE User_Id = ?";
        jdbcTemplate.update(sql,
                user.getUserName(),
                user.getEmailDesc(),
                user.getFullName(),
                user.getIsActive(),
                user.getLastLoginDttm(),
                user.getUpdatedBy(),
                userId);
    }

    public void deleteUser(Integer userId) {
        String schema = databaseConfig.getSchema();
        String sql = "DELETE FROM " + schema + ".\"USER\" WHERE User_Id = ?";
        jdbcTemplate.update(sql, userId);
    }

    private RowMapper<UserDto> userRowMapper() {
        return new RowMapper<UserDto>() {
            @Override
            public UserDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                UserDto user = new UserDto();
                user.setUserId(rs.getInt("User_Id"));
                user.setUserName(rs.getString("User_Name"));
                user.setEmailDesc(rs.getString("Email_Desc"));
                user.setFullName(rs.getString("Full_Name"));
                user.setIsActive(rs.getString("Is_Active"));
                if (rs.getTimestamp("Last_Login_Dttm") != null) {
                    user.setLastLoginDttm(rs.getTimestamp("Last_Login_Dttm").toLocalDateTime());
                }
                if (rs.getObject("Created_By") != null) {
                    user.setCreatedBy(rs.getInt("Created_By"));
                }
                if (rs.getTimestamp("Created_Dttm") != null) {
                    user.setCreatedDttm(rs.getTimestamp("Created_Dttm").toLocalDateTime());
                }
                if (rs.getObject("Updated_By") != null) {
                    user.setUpdatedBy(rs.getInt("Updated_By"));
                }
                if (rs.getTimestamp("Updated_Dttm") != null) {
                    user.setUpdatedDttm(rs.getTimestamp("Updated_Dttm").toLocalDateTime());
                }
                return user;
            }
        };
    }
}

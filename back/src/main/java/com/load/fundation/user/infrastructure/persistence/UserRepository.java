package com.load.fundation.user.infrastructure.persistence;

import com.load.fundation.shared.config.DatabaseConfig;
import com.load.fundation.shared.util.constants.DatabaseColumns;
import com.load.fundation.user.infrastructure.persistence.util.query.SqlQueryUser;
import com.load.fundation.user.domain.model.User;
import com.load.fundation.user.domain.port.out.UserPersistencePort;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepository implements UserPersistencePort {

    private final JdbcTemplate jdbcTemplate;
    private final DatabaseConfig databaseConfig;

    public UserRepository(JdbcTemplate jdbcTemplate, DatabaseConfig databaseConfig) {
        this.jdbcTemplate = jdbcTemplate;
        this.databaseConfig = databaseConfig;
    }

    public List<User> findAll() {
        String schema = databaseConfig.getSchema();
        String sql = String.format(SqlQueryUser.SELECT_ALL_USERS, schema);
        return jdbcTemplate.query(sql, userRowMapper());
    }

    public User findById(Integer userId) {
        String schema = databaseConfig.getSchema();
        String sql = String.format(SqlQueryUser.SELECT_USER_BY_ID, schema);
        try {
            return jdbcTemplate.queryForObject(sql, userRowMapper(), userId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void save(User user) {
        String schema = databaseConfig.getSchema();
        String sql = String.format(SqlQueryUser.INSERT_USER, schema);
        jdbcTemplate.update(sql,
                user.getUserId(),
                user.getUserName(),
                user.getEmailDesc(),
                user.getFullName(),
                user.getIsActive(),
                user.getLastLoginDttm(),
                user.getCreatedBy(),
                user.getCreatedDttm(),
                user.getUpdatedBy(),
                user.getUpdatedDttm());
    }

    public void update(Integer userId, User user) {
        String schema = databaseConfig.getSchema();
        String sql = String.format(SqlQueryUser.UPDATE_USER, schema);
        jdbcTemplate.update(sql,
                user.getUserName(),
                user.getEmailDesc(),
                user.getFullName(),
                user.getIsActive(),
                user.getLastLoginDttm(),
                user.getUpdatedBy(),
                user.getUpdatedDttm(),
                userId);
    }

    @Override
    public void deleteById(Integer userId) {
        String schema = databaseConfig.getSchema();
        String sql = String.format(SqlQueryUser.DELETE_USER, schema);
        jdbcTemplate.update(sql, userId);
    }

    private RowMapper<User> userRowMapper() {
        return new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setUserId(rs.getInt(DatabaseColumns.USER_ID));
                user.setUserName(rs.getString(DatabaseColumns.USER_NAME));
                user.setEmailDesc(rs.getString(DatabaseColumns.EMAIL_DESC));
                user.setFullName(rs.getString(DatabaseColumns.FULL_NAME));
                user.setIsActive(rs.getString(DatabaseColumns.IS_ACTIVE));
                if (rs.getTimestamp(DatabaseColumns.LAST_LOGIN_DTTM) != null) {
                    user.setLastLoginDttm(rs.getTimestamp(DatabaseColumns.LAST_LOGIN_DTTM).toLocalDateTime());
                }
                if (rs.getObject(DatabaseColumns.CREATED_BY) != null) {
                    user.setCreatedBy(rs.getInt(DatabaseColumns.CREATED_BY));
                }
                if (rs.getTimestamp(DatabaseColumns.CREATED_DTTM) != null) {
                    user.setCreatedDttm(rs.getTimestamp(DatabaseColumns.CREATED_DTTM).toLocalDateTime());
                }
                if (rs.getObject(DatabaseColumns.UPDATED_BY) != null) {
                    user.setUpdatedBy(rs.getInt(DatabaseColumns.UPDATED_BY));
                }
                if (rs.getTimestamp(DatabaseColumns.UPDATED_DTTM) != null) {
                    user.setUpdatedDttm(rs.getTimestamp(DatabaseColumns.UPDATED_DTTM).toLocalDateTime());
                }
                return user;
            }
        };
    }
}

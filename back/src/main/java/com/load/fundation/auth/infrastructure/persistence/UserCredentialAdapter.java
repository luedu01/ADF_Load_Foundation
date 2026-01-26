package com.load.fundation.auth.infrastructure.persistence;

import com.load.fundation.auth.domain.port.out.UserCredentialPort;
import com.load.fundation.shared.config.DatabaseConfig;
import com.load.fundation.shared.util.constants.SqlQueries;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserCredentialAdapter implements UserCredentialPort {

    private final JdbcTemplate jdbcTemplate;
    private final DatabaseConfig databaseConfig;

    public UserCredentialAdapter(JdbcTemplate jdbcTemplate, DatabaseConfig databaseConfig) {
        this.jdbcTemplate = jdbcTemplate;
        this.databaseConfig = databaseConfig;
    }

    @Override
    public Map<String, Object> findUserCredentialsByUsername(String username) {
        String schema = databaseConfig.getSchema();
        String sql = String.format(SqlQueries.SELECT_USER_CREDENTIALS, schema, schema);

        try {
            return jdbcTemplate.queryForMap(sql, username);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}

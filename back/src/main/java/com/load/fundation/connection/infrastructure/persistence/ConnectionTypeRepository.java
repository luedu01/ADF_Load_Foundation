package com.load.fundation.connection.infrastructure.persistence;


import com.load.fundation.connection.domain.model.ConnectionType;
import com.load.fundation.connection.domain.port.out.ConnectionTypePersistencePort;
import com.load.fundation.connection.infrastructure.persistence.util.query.SqlQueryConnectionType;
import com.load.fundation.shared.config.DatabaseConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ConnectionTypeRepository implements ConnectionTypePersistencePort {

    private final JdbcTemplate jdbcTemplate;
    private final DatabaseConfig databaseConfig;

    @Override
    public List<ConnectionType> findAll() {
        String sql = String.format(SqlQueryConnectionType.SELECT_ALL_TYPES, databaseConfig.getSchema());
        return jdbcTemplate.query(sql, rowMapper());
    }

    @Override
    public ConnectionType findById(Integer id) {
        String sql = String.format(SqlQueryConnectionType.SELECT_TYPE_BY_ID, databaseConfig.getSchema());
        return jdbcTemplate.query(sql, rs -> {
            if (rs.next()) {
                return mapRow(rs);
            }
            return null;
        }, id);
    }

    @Override
    public void save(ConnectionType type) {
        String sql = String.format(SqlQueryConnectionType.INSERT_TYPE, databaseConfig.getSchema());
        jdbcTemplate.update(
                sql,
                type.getId(),
                type.getName(),
                type.getCreatedBy(),
                toTimestamp(type.getCreatedDttm()),
                type.getUpdatedBy(),
                toTimestamp(type.getUpdatedDttm())
        );
    }

    @Override
    public void update(Integer id, ConnectionType type) {
        String sql = String.format(SqlQueryConnectionType.UPDATE_TYPE, databaseConfig.getSchema());
        jdbcTemplate.update(
                sql,
                type.getName(),
                type.getUpdatedBy(),
                toTimestamp(type.getUpdatedDttm()),
                id
        );
    }

    @Override
    public void deleteById(Integer id) {
        String sql = String.format(SqlQueryConnectionType.DELETE_TYPE, databaseConfig.getSchema());
        jdbcTemplate.update(sql, id);
    }

    @Override
    public boolean existsById(Integer id) {
        String sql = String.format(SqlQueryConnectionType.SELECT_EXISTS, databaseConfig.getSchema());
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }

    private RowMapper<ConnectionType> rowMapper() {
        return (rs, rowNum) -> mapRow(rs);
    }

    private ConnectionType mapRow(java.sql.ResultSet rs) throws java.sql.SQLException {
        return ConnectionType.builder()
                .id(rs.getObject("Connection_Type_Id", Integer.class))
                .name(rs.getString("Connection_Type_Name"))
                .createdBy(rs.getObject("Created_By", Integer.class))
                .createdDttm(toLocalDateTime(rs.getTimestamp("Created_Dttm")))
                .updatedBy(rs.getObject("Updated_By", Integer.class))
                .updatedDttm(toLocalDateTime(rs.getTimestamp("Updated_Dttm")))
                .build();
    }

    private LocalDateTime toLocalDateTime(Timestamp ts) {
        return ts != null ? ts.toLocalDateTime() : null;
    }

    private Timestamp toTimestamp(LocalDateTime dt) {
        return dt != null ? Timestamp.valueOf(dt) : null;
    }
}
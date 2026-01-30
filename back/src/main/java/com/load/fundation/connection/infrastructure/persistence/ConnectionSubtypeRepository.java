package com.load.fundation.connection.infrastructure.persistence;

import com.load.fundation.connection.domain.model.ConnectionSubtype;
import com.load.fundation.connection.domain.port.out.ConnectionSubtypePersistencePort;
import com.load.fundation.connection.infrastructure.persistence.util.query.SqlQueryConnectionSubtype;
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
public class ConnectionSubtypeRepository implements ConnectionSubtypePersistencePort {

    private final JdbcTemplate jdbcTemplate;
    private final DatabaseConfig databaseConfig;

    @Override
    public List<ConnectionSubtype> findAll() {
        String sql = String.format(SqlQueryConnectionSubtype.SELECT_ALL_SUBTYPES, databaseConfig.getSchema());
        return jdbcTemplate.query(sql, rowMapper());
    }

    @Override
    public List<ConnectionSubtype> findByTypeId(Integer typeId) {
        String sql = String.format(SqlQueryConnectionSubtype.SELECT_SUBTYPES_BY_TYPE, databaseConfig.getSchema());
        return jdbcTemplate.query(sql, rowMapper(), typeId);
    }

    @Override
    public ConnectionSubtype findById(Integer id) {
        String sql = String.format(SqlQueryConnectionSubtype.SELECT_SUBTYPE_BY_ID, databaseConfig.getSchema());
        return jdbcTemplate.query(sql, rs -> {
            if (rs.next()) {
                return mapRow(rs);
            }
            return null;
        }, id);
    }

    @Override
    public void save(ConnectionSubtype subtype) {
        String sql = String.format(SqlQueryConnectionSubtype.INSERT_SUBTYPE, databaseConfig.getSchema());
        jdbcTemplate.update(
                sql,
                subtype.getId(),
                subtype.getTypeId(),
                subtype.getName(),
                subtype.getCreatedBy(),
                toTimestamp(subtype.getCreatedDttm()),
                subtype.getUpdatedBy(),
                toTimestamp(subtype.getUpdatedDttm())
        );
    }

    @Override
    public void update(Integer id, ConnectionSubtype subtype) {
        String sql = String.format(SqlQueryConnectionSubtype.UPDATE_SUBTYPE, databaseConfig.getSchema());
        jdbcTemplate.update(
                sql,
                subtype.getTypeId(),
                subtype.getName(),
                subtype.getUpdatedBy(),
                toTimestamp(subtype.getUpdatedDttm()),
                id
        );
    }

    @Override
    public void deleteById(Integer id) {
        String sql = String.format(SqlQueryConnectionSubtype.DELETE_SUBTYPE, databaseConfig.getSchema());
        jdbcTemplate.update(sql, id);
    }

    @Override
    public boolean existsById(Integer id) {
        String sql = String.format(SqlQueryConnectionSubtype.SELECT_EXISTS, databaseConfig.getSchema());
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }

    private RowMapper<ConnectionSubtype> rowMapper() {
        return (rs, rowNum) -> mapRow(rs);
    }

    private ConnectionSubtype mapRow(java.sql.ResultSet rs) throws java.sql.SQLException {
        return ConnectionSubtype.builder()
                .id(rs.getObject("Connection_Subtype_Id", Integer.class))
                .typeId(rs.getObject("Connection_Type_Id", Integer.class))
                .name(rs.getString("Connection_Subtype_Name"))
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
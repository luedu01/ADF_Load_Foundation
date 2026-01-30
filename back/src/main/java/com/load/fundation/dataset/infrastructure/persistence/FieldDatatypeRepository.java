package com.load.fundation.dataset.infrastructure.persistence;

import com.load.fundation.dataset.domain.model.FieldDatatype;
import com.load.fundation.dataset.domain.port.out.FieldDatatypePersistencePort;
import com.load.fundation.shared.config.DatabaseConfig;
import com.load.fundation.dataset.infrastructure.persistence.util.query.SqlQueryFieldDatatype;
import com.load.fundation.dataset.application.dto.FieldDatatypeSearchCriteria;
import com.load.fundation.dataset.exception.DatasetInternalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Adaptador de persistencia para FieldDatatype - Tabla SOURCE_FIELD_DATATYPE
 */
@Repository
@RequiredArgsConstructor
@Slf4j
public class FieldDatatypeRepository implements FieldDatatypePersistencePort {

    private final JdbcTemplate jdbcTemplate;
    private final DatabaseConfig databaseConfig;

    @Override
    public List<FieldDatatype> findAll() {
        String schema = databaseConfig.getSchema();
        String sql = String.format(SqlQueryFieldDatatype.SELECT_ALL_DATATYPES, schema);
        try {
            return jdbcTemplate.query(sql, fieldDatatypeRowMapper());
        } catch (Exception e) {
            log.error("Error executing findAll for SOURCE_FIELD_DATATYPE", e);
            throw new DatasetInternalException("Error fetching all field datatypes", e);
        }
    }

    @Override
    public FieldDatatype findById(Integer fieldDatatypeId) {
        String schema = databaseConfig.getSchema();
        String sql = String.format(SqlQueryFieldDatatype.SELECT_DATATYPE_BY_ID, schema);
        try {
            List<FieldDatatype> results = jdbcTemplate.query(sql, fieldDatatypeRowMapper(), fieldDatatypeId);
            return results.isEmpty() ? null : results.get(0);
        } catch (Exception e) {
            log.error("Error executing findById for SOURCE_FIELD_DATATYPE, id={}", fieldDatatypeId, e);
            throw new DatasetInternalException("Error fetching field datatype by id", e);
        }
    }

    @Override
    public List<FieldDatatype> findByCriteria(FieldDatatypeSearchCriteria criteria) {
        String schema = databaseConfig.getSchema();
        String base = String.format(SqlQueryFieldDatatype.SELECT_DATATYPES_BASE, schema);
        StringBuilder sql = new StringBuilder();
        sql.append(base);

        List<Object> params = new ArrayList<>();

        if (criteria == null) {
            sql.append(" ORDER BY Field_Datatype_Id DESC");
            try {
                return jdbcTemplate.query(sql.toString(), fieldDatatypeRowMapper());
            } catch (Exception e) {
                log.error("Error executing findByCriteria (no criteria) for SOURCE_FIELD_DATATYPE", e);
                throw new DatasetInternalException("Error fetching field datatypes by criteria", e);
            }
        }

        appendEqIfNotNull(sql, params, "Field_Datatype_Id", criteria.getFieldDatatypeId());
        appendLikeIfNotBlank(sql, params, "Datatype_Name", criteria.getDataTypeName());
        appendLikeIfNotBlank(sql, params, "Datatype_Description", criteria.getDataTypeDescription());

        sql.append(" ORDER BY Field_Datatype_Id DESC");

        try {
            if (params.isEmpty()) {
                return jdbcTemplate.query(sql.toString(), fieldDatatypeRowMapper());
            }
            return jdbcTemplate.query(sql.toString(), fieldDatatypeRowMapper(), params.toArray());
        } catch (Exception e) {
            log.error("Error executing findByCriteria for SOURCE_FIELD_DATATYPE with params={}", params, e);
            throw new DatasetInternalException("Error fetching field datatypes by criteria", e);
        }
    }

    @Override
    public void save(FieldDatatype fieldDatatype) {
        String schema = databaseConfig.getSchema();
        fieldDatatype.setFieldDatatypeId(nextIdForColumn(schema, "SOURCE_FIELD_SEMANTIC_DATATYPE", "Field_Datatype_Id"));
        String sql = String.format(SqlQueryFieldDatatype.INSERT_DATATYPE, schema);
        try {
            jdbcTemplate.update(sql,
                    fieldDatatype.getFieldDatatypeId(),
                    fieldDatatype.getDataTypeName(),
                    fieldDatatype.getDataTypeDescription(),
                    fieldDatatype.getCreatedBy(),
                    fieldDatatype.getCreatedDttm(),
                    fieldDatatype.getUpdatedBy(),
                    fieldDatatype.getUpdatedDttm());
        } catch (Exception e) {
            log.error("Error executing save for SOURCE_FIELD_DATATYPE, id={}", fieldDatatype.getFieldDatatypeId(), e);
            throw new DatasetInternalException("Error saving field datatype", e);
        }
    }

    @Override
    public void update(Integer fieldDatatypeId, FieldDatatype fieldDatatype) {
        String schema = databaseConfig.getSchema();
        String sql = String.format(SqlQueryFieldDatatype.UPDATE_DATATYPE, schema);
        try {
            jdbcTemplate.update(sql,
                    fieldDatatype.getDataTypeName(),
                    fieldDatatype.getDataTypeDescription(),
                    fieldDatatype.getUpdatedBy(),
                    fieldDatatype.getUpdatedDttm(),
                    fieldDatatypeId);
        } catch (Exception e) {
            log.error("Error executing update for SOURCE_FIELD_DATATYPE, id={}", fieldDatatypeId, e);
            throw new DatasetInternalException("Error updating field datatype", e);
        }
    }

    @Override
    public void deleteById(Integer fieldDatatypeId) {
        String schema = databaseConfig.getSchema();
        String sql = String.format(SqlQueryFieldDatatype.DELETE_DATATYPE, schema);
        try {
            jdbcTemplate.update(sql, fieldDatatypeId);
        } catch (Exception e) {
            log.error("Error executing deleteById for SOURCE_FIELD_DATATYPE, id={}", fieldDatatypeId, e);
            throw new DatasetInternalException("Error deleting field datatype", e);
        }
    }

    @Override
    public boolean existsById(Integer fieldDatatypeId) {
        String schema = databaseConfig.getSchema();
        String sql = String.format(SqlQueryFieldDatatype.SELECT_EXISTS, schema);
        try {
            int count = jdbcTemplate.queryForObject(sql, Integer.class, fieldDatatypeId);
            return count > 0;
        } catch (Exception e) {
            log.error("Error executing existsById for SOURCE_FIELD_DATATYPE, id={}", fieldDatatypeId, e);
            throw new DatasetInternalException("Error checking existence of field datatype", e);
        }
    }

    private void appendEqIfNotNull(StringBuilder sql, List<Object> params, String column, Object value) {
        if (value != null) {
            sql.append(" AND ").append(column).append(" = ?");
            params.add(value);
        }
    }

    private void appendLikeIfNotBlank(StringBuilder sql, List<Object> params, String column, String value) {
        if (value != null && !value.trim().isEmpty()) {
            sql.append(" AND ").append(column).append(" LIKE ?");
            params.add("%" + value + "%");
        }
    }

    private int nextIdForColumn(String schema, String table, String idColumn) {
        String sql = String.format("SELECT MAX(%s) FROM %s.%s", idColumn, schema, table);
        try {
            Integer max = jdbcTemplate.queryForObject(sql, Integer.class);
            return (max == null) ? 1 : (max + 1);
        } catch (Exception e) {
            log.error("Error retrieving next id for {}.{}", table, idColumn, e);
            throw new DatasetInternalException("Error generating next id", e);
        }
    }

    private RowMapper<FieldDatatype> fieldDatatypeRowMapper() {
        return (rs, rowNum) -> FieldDatatype.builder()
                .fieldDatatypeId(rs.getObject("Field_Datatype_Id", Integer.class))
                .dataTypeName(rs.getString("Datatype_Name"))
                .dataTypeDescription(rs.getString("Datatype_Description"))
                .createdBy(rs.getObject("Created_By", Integer.class))
                .createdDttm(rs.getTimestamp("Created_Dttm") != null ? rs.getTimestamp("Created_Dttm").toLocalDateTime() : null)
                .updatedBy(rs.getObject("Updated_By", Integer.class))
                .updatedDttm(rs.getTimestamp("Updated_Dttm") != null ? rs.getTimestamp("Updated_Dttm").toLocalDateTime() : null)
                .build();
    }
}

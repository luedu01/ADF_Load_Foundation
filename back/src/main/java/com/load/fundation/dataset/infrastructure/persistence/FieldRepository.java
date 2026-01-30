package com.load.fundation.dataset.infrastructure.persistence;

import com.load.fundation.dataset.domain.model.Field;
import com.load.fundation.dataset.domain.port.out.FieldPersistencePort;
import com.load.fundation.shared.config.DatabaseConfig;
import com.load.fundation.dataset.infrastructure.persistence.util.query.SqlQueryField;
import com.load.fundation.dataset.application.dto.FieldSearchCriteria;
import com.load.fundation.dataset.exception.DatasetInternalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Repositorio JDBC para la tabla SOURCE_FIELD
 */
@Repository
@RequiredArgsConstructor
@Slf4j
public class FieldRepository implements FieldPersistencePort {

    private static final String AND = " AND ";
    public static final String CREATED_DTTM = "Created_Dttm";
    public static final String UPDATED_DTTM = "Updated_Dttm";
    private final JdbcTemplate jdbcTemplate;
    private final DatabaseConfig databaseConfig;

    @Override
    public List<Field> findAll() {
        String schema = databaseConfig.getSchema();
        String sql = String.format(SqlQueryField.SELECT_ALL_FIELDS, schema);
        try {
            return jdbcTemplate.query(sql, fieldRowMapper());
        } catch (Exception e) {
            log.error("Error executing findAll for SOURCE_FIELD", e);
            throw new DatasetInternalException("Error fetching all fields", e);
        }
    }

    @Override
    public Field findById(Integer fieldId) {
        String schema = databaseConfig.getSchema();
        String sql = String.format(SqlQueryField.SELECT_FIELD_BY_ID, schema);
        try {
            List<Field> results = jdbcTemplate.query(sql, fieldRowMapper(), fieldId);
            return results.isEmpty() ? null : results.get(0);
        } catch (Exception e) {
            log.error("Error executing findById for SOURCE_FIELD, id={}", fieldId, e);
            throw new DatasetInternalException("Error fetching field by id", e);
        }
    }

    @Override
    public List<Field> findByCriteria(FieldSearchCriteria criteria) {
        String schema = databaseConfig.getSchema();
        String base = String.format(SqlQueryField.SELECT_FIELDS_BASE, schema);
        StringBuilder sql = new StringBuilder();
        sql.append(base);

        List<Object> params = new ArrayList<>();

        if (criteria == null) {
            sql.append(" ORDER BY Field_Id DESC");
            try {
                return jdbcTemplate.query(sql.toString(), fieldRowMapper());
            } catch (Exception e) {
                log.error("Error executing findByCriteria (no criteria) for SOURCE_FIELD", e);
                throw new DatasetInternalException("Error fetching fields by criteria", e);
            }
        }

        appendEqIfNotNull(sql, params, "Field_Id", criteria.getFieldId());
        appendEqIfNotNull(sql, params, "Schema_Id", criteria.getSchemaId());
        appendEqIfNotNull(sql, params, "Position_Num", criteria.getPositionNum());
        appendEqIfNotNull(sql, params, "Field_Datatype_Id", criteria.getFieldDatatypeId());
        appendEqIfNotNull(sql, params, "Field_Semantic_Datatype_Id", criteria.getFieldSemanticDatatypeId());
        appendEqIfNotBlank(sql, params, "Is_Nullable", criteria.getIsNullable());
        appendEqIfNotBlank(sql, params, "Is_Primary_Key", criteria.getIsPrimaryKey());
        appendLikeIfNotBlank(sql, params, "Field_Name", criteria.getFieldName());
        appendLikeIfNotBlank(sql, params, "Field_Description", criteria.getFieldDescription());
        appendEqIfNotNull(sql, params, "Created_By", criteria.getCreatedBy());
        appendDateGeIfNotNull(sql, params, CREATED_DTTM, criteria.getCreatedDttm());
        appendEqIfNotNull(sql, params, "Updated_By", criteria.getUpdatedBy());
        appendDateGeIfNotNull(sql, params, UPDATED_DTTM, criteria.getUpdatedDttm());

        sql.append(" ORDER BY Field_Id DESC");

        try {
            if (params.isEmpty()) {
                return jdbcTemplate.query(sql.toString(), fieldRowMapper());
            }
            return jdbcTemplate.query(sql.toString(), fieldRowMapper(), params.toArray());
        } catch (Exception e) {
            log.error("Error executing findByCriteria for SOURCE_FIELD with params={}", params, e);
            throw new DatasetInternalException("Error fetching fields by criteria", e);
        }
    }

    private void appendDateGeIfNotNull(StringBuilder sql, List<Object> params, String column, java.time.LocalDateTime value) {
        if (value != null) {
            sql.append(AND).append(column).append(" >= ?");
            params.add(Timestamp.valueOf(value));
        }
    }

    private void appendEqIfNotNull(StringBuilder sql, List<Object> params, String column, Object value) {
        if (value != null) {
            sql.append(AND).append(column).append(" = ?");
            params.add(value);
        }
    }

    private void appendEqIfNotBlank(StringBuilder sql, List<Object> params, String column, String value) {
        if (value != null && !value.trim().isEmpty()) {
            sql.append(AND).append(column).append(" = ?");
            params.add(value);
        }
    }

    private void appendLikeIfNotBlank(StringBuilder sql, List<Object> params, String column, String value) {
        if (value != null && !value.trim().isEmpty()) {
            sql.append(AND).append(column).append(" LIKE ?");
            params.add("%" + value + "%");
        }
    }

    @Override
    public void save(Field field) {
        String schema = databaseConfig.getSchema();
        field.setFieldId(nextIdForColumn(schema, "SOURCE_FIELD", "Field_Id"));
        String sql = String.format(SqlQueryField.INSERT_FIELD, schema);
        try {
            jdbcTemplate.update(sql,
                    field.getFieldId(),
                    field.getSchemaId(),
                    field.getPositionNum(),
                    field.getFieldName(),
                    field.getFieldDescription(),
                    field.getFieldDatatypeId(),
                    field.getLength(),
                    field.getPrecision(),
                    field.getIsNullable(),
                    field.getIsPrimaryKey(),
                    field.getFieldSemanticDatatypeId(),
                    field.getSourcePathExpression(),
                    field.getCreatedBy(),
                    field.getCreatedDttm(),
                    field.getUpdatedBy(),
                    field.getUpdatedDttm());
        } catch (Exception e) {
            log.error("Error executing save for SOURCE_FIELD, id={}", field.getFieldId(), e);
            throw new DatasetInternalException("Error saving field", e);
        }
    }

    @Override
    public void update(Integer fieldId, Field field) {
        String schema = databaseConfig.getSchema();
        String sql = String.format(SqlQueryField.UPDATE_FIELD, schema);
        try {
            jdbcTemplate.update(sql,
                    field.getSchemaId(),
                    field.getPositionNum(),
                    field.getFieldName(),
                    field.getFieldDescription(),
                    field.getFieldDatatypeId(),
                    field.getLength(),
                    field.getPrecision(),
                    field.getIsNullable(),
                    field.getIsPrimaryKey(),
                    field.getFieldSemanticDatatypeId(),
                    field.getSourcePathExpression(),
                    field.getUpdatedBy(),
                    field.getUpdatedDttm(),
                    fieldId);
        } catch (Exception e) {
            log.error("Error executing update for SOURCE_FIELD, id={}", fieldId, e);
            throw new DatasetInternalException("Error updating field", e);
        }
    }

    @Override
    public void deleteById(Integer fieldId) {
        String schema = databaseConfig.getSchema();
        String sql = String.format(SqlQueryField.DELETE_FIELD, schema);
        try {
            jdbcTemplate.update(sql, fieldId);
        } catch (Exception e) {
            log.error("Error executing deleteById for SOURCE_FIELD, id={}", fieldId, e);
            throw new DatasetInternalException("Error deleting field", e);
        }
    }

    @Override
    public boolean existsById(Integer fieldId) {
        String schema = databaseConfig.getSchema();
        String sql = String.format(SqlQueryField.SELECT_EXISTS, schema);
        try {
            int count = jdbcTemplate.queryForObject(sql, Integer.class, fieldId);
            return count > 0;
        } catch (Exception e) {
            log.error("Error executing existsById for SOURCE_FIELD, id={}", fieldId, e);
            throw new DatasetInternalException("Error checking existence of field", e);
        }
    }

    private RowMapper<Field> fieldRowMapper() {
        return (rs, rowNum) -> Field.builder()
                .fieldId(rs.getObject("Field_Id", Integer.class))
                .schemaId(rs.getObject("Schema_Id", Integer.class))
                .positionNum(rs.getObject("Position_Num", Integer.class))
                .fieldName(rs.getString("Field_Name"))
                .fieldDescription(rs.getString("Field_Description"))
                .fieldDatatypeId(rs.getObject("Field_Datatype_Id", Integer.class))
                .length(rs.getObject("Length", Integer.class))
                .precision(rs.getObject("Precision", Integer.class))
                .isNullable(rs.getString("Is_Nullable"))
                .isPrimaryKey(rs.getString("Is_Primary_Key"))
                .fieldSemanticDatatypeId(rs.getObject("Field_Semantic_Datatype_Id", Integer.class))
                .sourcePathExpression(rs.getString("Source_Path_Expression"))
                .createdBy(rs.getObject("Created_By", Integer.class))
                .createdDttm(rs.getTimestamp(CREATED_DTTM) != null ? rs.getTimestamp(CREATED_DTTM).toLocalDateTime() : null)
                .updatedBy(rs.getObject("Updated_By", Integer.class))
                .updatedDttm(rs.getTimestamp(UPDATED_DTTM) != null ? rs.getTimestamp(UPDATED_DTTM).toLocalDateTime() : null)
                .build();
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

}

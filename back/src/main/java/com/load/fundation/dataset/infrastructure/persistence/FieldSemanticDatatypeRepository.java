package com.load.fundation.dataset.infrastructure.persistence;

import com.load.fundation.dataset.domain.model.FieldSemanticDatatype;
import com.load.fundation.dataset.domain.port.out.FieldSemanticDatatypePersistencePort;
import com.load.fundation.shared.config.DatabaseConfig;
import com.load.fundation.dataset.infrastructure.persistence.util.query.SqlQueryFieldSemantic;
import com.load.fundation.dataset.application.dto.FieldSemanticDatatypeSearchCriteria;
import com.load.fundation.dataset.exception.DatasetInternalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class FieldSemanticDatatypeRepository implements FieldSemanticDatatypePersistencePort {

    private final JdbcTemplate jdbcTemplate;
    private final DatabaseConfig databaseConfig;

    @Override
    public List<FieldSemanticDatatype> findAll() {
        String schema = databaseConfig.getSchema();
        String sql = String.format(SqlQueryFieldSemantic.SELECT_ALL, schema);
        try {
            return jdbcTemplate.query(sql, fieldSemanticRowMapper());
        } catch (Exception e) {
            log.error("Error executing findAll for SOURCE_FIELD_SEMANTIC_DATATYPE", e);
            throw new DatasetInternalException("Error fetching all field semantic datatypes", e);
        }
    }

    @Override
    public FieldSemanticDatatype findById(Integer id) {
        String schema = databaseConfig.getSchema();
        String sql = String.format(SqlQueryFieldSemantic.SELECT_BY_ID, schema);
        try {
            List<FieldSemanticDatatype> results = jdbcTemplate.query(sql, fieldSemanticRowMapper(), id);
            return results.isEmpty() ? null : results.get(0);
        } catch (Exception e) {
            log.error("Error executing findById for SOURCE_FIELD_SEMANTIC_DATATYPE, id={}", id, e);
            throw new DatasetInternalException("Error fetching field semantic datatype by id", e);
        }
    }

    @Override
    public List<FieldSemanticDatatype> findByCriteria(FieldSemanticDatatypeSearchCriteria criteria) {
        String schema = databaseConfig.getSchema();
        String base = String.format(SqlQueryFieldSemantic.SELECT_ALL.substring(0, SqlQueryFieldSemantic.SELECT_ALL.indexOf(" ORDER BY ")).replace("%s", schema), schema);
        StringBuilder sql = new StringBuilder();
        sql.append(base);

        List<Object> params = new ArrayList<>();

        if (criteria == null) {
            sql.append(" ORDER BY Field_Semantic_Datatype_Id DESC");
            try {
                return jdbcTemplate.query(sql.toString(), fieldSemanticRowMapper());
            } catch (Exception e) {
                log.error("Error executing findByCriteria (no criteria) for SOURCE_FIELD_SEMANTIC_DATATYPE", e);
                throw new DatasetInternalException("Error fetching field semantic datatypes", e);
            }
        }

        appendEqIfNotNull(sql, params, "Field_Semantic_Datatype_Id", criteria.getFieldSemanticDatatypeId());
        appendEqIfNotNull(sql, params, "Field_Datatype_Id", criteria.getFieldDatatypeId());
        appendLikeIfNotBlank(sql, params, "DataType_Name", criteria.getDataTypeName());
        appendLikeIfNotBlank(sql, params, "DataType_Description", criteria.getDatatypeDescription());

        sql.append(" ORDER BY Field_Semantic_Datatype_Id DESC");

        try {
            if (params.isEmpty()) {
                return jdbcTemplate.query(sql.toString(), fieldSemanticRowMapper());
            }
            return jdbcTemplate.query(sql.toString(), fieldSemanticRowMapper(), params.toArray());
        } catch (Exception e) {
            log.error("Error executing findByCriteria for SOURCE_FIELD_SEMANTIC_DATATYPE with params={}", params, e);
            throw new DatasetInternalException("Error fetching field semantic datatypes", e);
        }
    }

    @Override
    public void save(FieldSemanticDatatype entity) {
        String schema = databaseConfig.getSchema();
        entity.setFieldSemanticDatatypeId(nextIdForColumn(schema));
        String sql = String.format(SqlQueryFieldSemantic.INSERT, schema);
        try {
            jdbcTemplate.update(sql,
                    entity.getFieldSemanticDatatypeId(),
                    entity.getFieldDatatypeId(),
                    entity.getDataTypeName(),
                    entity.getDatatypeDescription(),
                    entity.getCreatedBy(),
                    entity.getCreatedDttm(),
                    entity.getUpdatedBy(),
                    entity.getUpdatedDttm());
        } catch (Exception e) {
            log.error("Error executing save for SOURCE_FIELD_SEMANTIC_DATATYPE, id={}", entity.getFieldSemanticDatatypeId(), e);
            throw new DatasetInternalException("Error saving field semantic datatype", e);
        }
    }

    @Override
    public void update(Integer id, FieldSemanticDatatype entity) {
        String schema = databaseConfig.getSchema();
        String sql = String.format(SqlQueryFieldSemantic.UPDATE, schema);
        try {
            jdbcTemplate.update(sql,
                    entity.getFieldDatatypeId(),
                    entity.getDataTypeName(),
                    entity.getDatatypeDescription(),
                    entity.getUpdatedBy(),
                    entity.getUpdatedDttm(),
                    id);
        } catch (Exception e) {
            log.error("Error executing update for SOURCE_FIELD_SEMANTIC_DATATYPE, id={}", id, e);
            throw new DatasetInternalException("Error updating field semantic datatype", e);
        }
    }

    @Override
    public void deleteById(Integer id) {
        String schema = databaseConfig.getSchema();
        String sql = String.format(SqlQueryFieldSemantic.DELETE, schema);
        try {
            jdbcTemplate.update(sql, id);
        } catch (Exception e) {
            log.error("Error executing deleteById for SOURCE_FIELD_SEMANTIC_DATATYPE, id={}", id, e);
            throw new DatasetInternalException("Error deleting field semantic datatype", e);
        }
    }

    @Override
    public boolean existsById(Integer id) {
        String schema = databaseConfig.getSchema();
        String sql = String.format(SqlQueryFieldSemantic.SELECT_EXISTS, schema);
        try {
            Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
            return count != null && count > 0;
        } catch (Exception e) {
            log.error("Error executing existsById for SOURCE_FIELD_SEMANTIC_DATATYPE, id={}", id, e);
            throw new DatasetInternalException("Error checking existence of field semantic datatype", e);
        }
    }

    // helper utilities
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

    private int nextIdForColumn(String schema) {
        String sql = String.format("SELECT MAX(%s) FROM %s.%s", "Field_Semantic_Datatype_Id", schema, "SOURCE_FIELD_SEMANTIC_DATATYPE");
        try {
            Integer max = jdbcTemplate.queryForObject(sql, Integer.class);
            return (max == null) ? 1 : (max + 1);
        } catch (Exception e) {
            log.error("Error retrieving next id for {}.{}", "SOURCE_FIELD_SEMANTIC_DATATYPE", "Field_Semantic_Datatype_Id", e);
            throw new DatasetInternalException("Error generating next id", e);
        }
    }

    private RowMapper<FieldSemanticDatatype> fieldSemanticRowMapper() {
        return (rs, rowNum) -> FieldSemanticDatatype.builder()
                .fieldSemanticDatatypeId(rs.getObject("Field_Semantic_Datatype_Id", Integer.class))
                .fieldDatatypeId(rs.getObject("Field_Datatype_Id", Integer.class))
                .dataTypeName(rs.getString("DataType_Name"))
                .datatypeDescription(rs.getString("DataType_Description"))
                .createdBy(rs.getObject("Created_By", Integer.class))
                .createdDttm(rs.getTimestamp("Created_Dttm") != null ? rs.getTimestamp("Created_Dttm").toLocalDateTime() : null)
                .updatedBy(rs.getObject("Updated_By", Integer.class))
                .updatedDttm(rs.getTimestamp("Updated_Dttm") != null ? rs.getTimestamp("Updated_Dttm").toLocalDateTime() : null)
                .build();
    }
}

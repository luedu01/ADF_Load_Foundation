package com.load.fundation.dataset.infrastructure.persistence;

import com.load.fundation.dataset.domain.model.DatasetSchema;
import com.load.fundation.dataset.domain.port.out.DatasetSchemaPersistencePort;
import com.load.fundation.shared.config.DatabaseConfig;
import com.load.fundation.dataset.infrastructure.persistence.util.query.SqlQueryDatasetSchema;
import com.load.fundation.dataset.application.dto.DatasetSchemaSearchCriteria;
import com.load.fundation.dataset.exception.DatasetInternalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class DatasetSchemaRepository implements DatasetSchemaPersistencePort {

    private final JdbcTemplate jdbcTemplate;
    private final DatabaseConfig databaseConfig;

    @Override
    public List<DatasetSchema> findAll() {
        String schema = databaseConfig.getSchema();
        String sql = String.format(SqlQueryDatasetSchema.SELECT_ALL_SCHEMAS, schema);
        try {
            return jdbcTemplate.query(sql, datasetSchemaRowMapper());
        } catch (Exception e) {
            log.error("Error executing findAll for SOURCE_DATASET_SCHEMA", e);
            throw new DatasetInternalException("Error fetching all dataset schemas", e);
        }
    }

    @Override
    public DatasetSchema findById(Integer schemaId) {
        String schema = databaseConfig.getSchema();
        String sql = String.format(SqlQueryDatasetSchema.SELECT_SCHEMA_BY_ID, schema);
        try {
            List<DatasetSchema> results = jdbcTemplate.query(sql, datasetSchemaRowMapper(), schemaId);
            return results.isEmpty() ? null : results.get(0);
        } catch (Exception e) {
            log.error("Error executing findById for SOURCE_DATASET_SCHEMA, id={}", schemaId, e);
            throw new DatasetInternalException("Error fetching dataset schema by id", e);
        }
    }

    @Override
    public List<DatasetSchema> findByCriteria(DatasetSchemaSearchCriteria criteria) {
        String schema = databaseConfig.getSchema();
        String base = String.format(SqlQueryDatasetSchema.SELECT_SCHEMAS_BASE, schema);
        StringBuilder sql = new StringBuilder();
        sql.append(base);

        List<Object> params = new ArrayList<>();

        if (criteria == null) {
            sql.append(" ORDER BY Schema_Id DESC");
            try {
                return jdbcTemplate.query(sql.toString(), datasetSchemaRowMapper());
            } catch (Exception e) {
                log.error("Error executing findByCriteria (no criteria) for SOURCE_DATASET_SCHEMA", e);
                throw new DatasetInternalException("Error fetching dataset schemas by criteria", e);
            }
        }

        applyDatasetSchemaFilters(criteria, sql, params);

        sql.append(" ORDER BY Schema_Id DESC");

        try {
            if (params.isEmpty()) {
                return jdbcTemplate.query(sql.toString(), datasetSchemaRowMapper());
            }
            return jdbcTemplate.query(sql.toString(), datasetSchemaRowMapper(), params.toArray());
        } catch (Exception e) {
            log.error("Error executing findByCriteria for SOURCE_DATASET_SCHEMA with params={}", params, e);
            throw new DatasetInternalException("Error fetching dataset schemas by criteria", e);
        }
    }

    private void applyDatasetSchemaFilters(DatasetSchemaSearchCriteria criteria, StringBuilder sql, List<Object> params) {
        appendEqIfNotNull(sql, params, "Schema_Id", criteria.getSchemaId());
        appendEqIfNotNull(sql, params, "Dataset_Id", criteria.getDatasetId());
        appendEqIfNotNull(sql, params, "Dataset_Schema_Version_Num", criteria.getDatasetSchemaVersionNum());
        appendEqIfNotBlank(sql, params, "Is_Draft", criteria.getIsDraft());
        appendEqIfNotBlank(sql, params, "Is_Current", criteria.getIsCurrent());
        appendLikeIfNotBlank(sql, params, "Notes_Txt", criteria.getNotesTxt());
        appendEqIfNotNull(sql, params, "Created_By", criteria.getCreatedBy());
        appendDateLeIfNotNull(sql, params, "Created_Dttm", criteria.getCreatedDttmTo());
        appendEqIfNotNull(sql, params, "Updated_By", criteria.getUpdatedBy());
        appendDateLeIfNotNull(sql, params, "Updated_Dttm", criteria.getUpdatedDttmTo());
    }

    // helper utilities
    private void appendEqIfNotNull(StringBuilder sql, List<Object> params, String column, Object value) {
        if (value != null) {
            sql.append(" AND ").append(column).append(" = ?");
            params.add(value);
        }
    }

    private void appendEqIfNotBlank(StringBuilder sql, List<Object> params, String column, String value) {
        if (value != null && !value.trim().isEmpty()) {
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

    private void appendDateLeIfNotNull(StringBuilder sql, List<Object> params, String column, java.time.LocalDateTime value) {
        if (value != null) {
            sql.append(" AND ").append(column).append(" <= ?");
            params.add(Timestamp.valueOf(value));
        }
    }

    @Override
    public void save(DatasetSchema datasetSchema) {
        String schema = databaseConfig.getSchema();
        datasetSchema.setSchemaId(nextIdForColumn(schema, "SOURCE_DATASET_SCHEMA", "Schema_Id"));
        String sql = String.format(SqlQueryDatasetSchema.INSERT_SCHEMA, schema);
        try {
            jdbcTemplate.update(sql,
                    datasetSchema.getSchemaId(),
                    datasetSchema.getDatasetId(),
                    datasetSchema.getDatasetSchemaVersionNum(),
                    datasetSchema.getIsDraft(),
                    datasetSchema.getIsCurrent(),
                    datasetSchema.getEffectiveFromDttm(),
                    datasetSchema.getEffectiveToDttm(),
                    datasetSchema.getNotesTxt(),
                    datasetSchema.getCreatedBy(),
                    datasetSchema.getCreatedDttm(),
                    datasetSchema.getUpdatedBy(),
                    datasetSchema.getUpdatedDttm());
        } catch (Exception e) {
            log.error("Error executing save for SOURCE_DATASET_SCHEMA, id={}", datasetSchema.getSchemaId(), e);
            throw new DatasetInternalException("Error saving dataset schema", e);
        }
    }

    private int nextIdForColumn(String schema, String table, String column) {
        String sql = String.format("SELECT MAX(%s) FROM %s.%s", column, schema, table);
        Integer max = jdbcTemplate.queryForObject(sql, Integer.class);
        return (max == null) ? 1 : (max + 1);
    }

    @Override
    public void update(Integer schemaId, DatasetSchema datasetSchema) {
        String schema = databaseConfig.getSchema();
        String sql = String.format(SqlQueryDatasetSchema.UPDATE_SCHEMA, schema);
        try {
            jdbcTemplate.update(sql,
                    datasetSchema.getDatasetId(),
                    datasetSchema.getDatasetSchemaVersionNum(),
                    datasetSchema.getIsDraft(),
                    datasetSchema.getIsCurrent(),
                    datasetSchema.getEffectiveFromDttm(),
                    datasetSchema.getEffectiveToDttm(),
                    datasetSchema.getNotesTxt(),
                    datasetSchema.getUpdatedBy(),
                    datasetSchema.getUpdatedDttm(),
                    schemaId);
        } catch (Exception e) {
            log.error("Error executing update for SOURCE_DATASET_SCHEMA, id={}", schemaId, e);
            throw new DatasetInternalException("Error updating dataset schema", e);
        }
    }

    @Override
    public void deleteById(Integer schemaId) {
        String schema = databaseConfig.getSchema();
        String sql = String.format(SqlQueryDatasetSchema.DELETE_SCHEMA, schema);
        try {
            jdbcTemplate.update(sql, schemaId);
        } catch (Exception e) {
            log.error("Error executing deleteById for SOURCE_DATASET_SCHEMA, id={}", schemaId, e);
            throw new DatasetInternalException("Error deleting dataset schema", e);
        }
    }

    @Override
    public boolean existsById(Integer schemaId) {
        String schema = databaseConfig.getSchema();
        String sql = String.format(SqlQueryDatasetSchema.SELECT_EXISTS, schema);
        try {
            Integer count = jdbcTemplate.queryForObject(sql, Integer.class, schemaId);
            return  count != null && count > 0;
        } catch (Exception e) {
            log.error("Error executing existsById for SOURCE_DATASET_SCHEMA, id={}", schemaId, e);
            throw new DatasetInternalException("Error checking existence of dataset schema", e);
        }
    }

    private RowMapper<DatasetSchema> datasetSchemaRowMapper() {
        return (rs, rowNum) -> DatasetSchema.builder()
                .schemaId(rs.getObject("Schema_Id", Integer.class))
                .datasetId(rs.getObject("Dataset_Id", Integer.class))
                .datasetSchemaVersionNum(rs.getObject("Dataset_Schema_Version_Num", Integer.class))
                .isDraft(rs.getString("Is_Draft"))
                .isCurrent(rs.getString("Is_Current"))
                .effectiveFromDttm(rs.getTimestamp("Effective_From_Dttm") != null ? rs.getTimestamp("Effective_From_Dttm").toLocalDateTime() : null)
                .effectiveToDttm(rs.getTimestamp("Effective_To_Dttm") != null ? rs.getTimestamp("Effective_To_Dttm").toLocalDateTime() : null)
                .notesTxt(rs.getString("Notes_Txt"))
                .createdBy(rs.getObject("Created_By", Integer.class))
                .createdDttm(rs.getTimestamp("Created_Dttm") != null ? rs.getTimestamp("Created_Dttm").toLocalDateTime() : null)
                .updatedBy(rs.getObject("Updated_By", Integer.class))
                .updatedDttm(rs.getTimestamp("Updated_Dttm") != null ? rs.getTimestamp("Updated_Dttm").toLocalDateTime() : null)
                .build();
    }
}

package com.load.fundation.dataset.infrastructure.persistence;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.load.fundation.dataset.application.dto.DatasetSearchCriteria;
import com.load.fundation.dataset.domain.model.Dataset;
import com.load.fundation.dataset.domain.port.out.DatasetPersistencePort;
import com.load.fundation.shared.config.DatabaseConfig;
import com.load.fundation.dataset.infrastructure.persistence.util.query.SqlQueryDataset;
import com.load.fundation.dataset.exception.DatasetInternalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Adaptador de persistencia para Dataset - Tabla SOURCE_DATA_SET
 */
@Repository
@RequiredArgsConstructor
@Slf4j
public class DatasetRepository implements DatasetPersistencePort {

    private final JdbcTemplate jdbcTemplate;
    private final DatabaseConfig databaseConfig;

    @Override
    public List<Dataset> findAll() {
        String schema = databaseConfig.getSchema();
        String sql = String.format(SqlQueryDataset.SELECT_ALL_DATASETS, schema);
        try {
            return jdbcTemplate.query(sql, datasetRowMapper());
        } catch (Exception e) {
            log.error("Error executing findAll for SOURCE_DATASET", e);
            throw new DatasetInternalException("Error fetching all datasets", e);
        }
    }

    @Override
    public Dataset findById(Integer datasetId) {
        String schema = databaseConfig.getSchema();
        String sql = String.format(SqlQueryDataset.SELECT_DATASET_BY_ID, schema);
        try {
            List<Dataset> results = jdbcTemplate.query(sql, datasetRowMapper(), datasetId);
            return results.isEmpty() ? null : results.get(0);
        } catch (Exception e) {
            log.error("Error executing findById for SOURCE_DATASET, id={}", datasetId, e);
            throw new DatasetInternalException("Error fetching dataset by id", e);
        }
    }

    @Override
    public List<Dataset> findByCriteria(DatasetSearchCriteria criteria) {
        String schema = databaseConfig.getSchema();
        String base = String.format(SqlQueryDataset.SELECT_DATASETS_BASE, schema);
        StringBuilder sql = new StringBuilder();
        sql.append(base);

        List<Object> params = new ArrayList<>();

        if (criteria == null) {
            sql.append(" ORDER BY Dataset_Id DESC");
            try {
                return jdbcTemplate.query(sql.toString(), datasetRowMapper());
            } catch (Exception e) {
                log.error("Error executing findByCriteria (no criteria) for SOURCE_DATASET", e);
                throw new DatasetInternalException("Error fetching datasets by criteria", e);
            }
        }

        // delegate building filters to helper to reduce method complexity
        applyDatasetFilters(criteria, sql, params);

        sql.append(" ORDER BY Dataset_Id DESC");

        try {
            if (params.isEmpty()) {
                return jdbcTemplate.query(sql.toString(), datasetRowMapper());
            }
            return jdbcTemplate.query(sql.toString(), datasetRowMapper(), params.toArray());
        } catch (Exception e) {
            log.error("Error executing findByCriteria for SOURCE_DATASET with params={}", params, e);
            throw new DatasetInternalException("Error fetching datasets by criteria", e);
        }
    }

    @Override
    public void save(Dataset dataset) {
        String schema = databaseConfig.getSchema();
        dataset.setDatasetId(nextIdForColumn(schema, "SOURCE_DATASET", "Dataset_Id"));
        String sql = String.format(SqlQueryDataset.INSERT_DATASET, schema);
        ObjectMapper objectMapper = new ObjectMapper();
        String additionalInfoJsonStr = convertToAdditionalInfoJson(dataset, objectMapper);
        try {

            jdbcTemplate.update(sql,
                    dataset.getDatasetId(),
                    dataset.getConnectionId(),
                    dataset.getDatasetName(),
                    dataset.getIsActive(),
                    dataset.getDatasetDescription(),
                    dataset.getDatasetExtractionQueryDescription(),
                    additionalInfoJsonStr,
                    dataset.getCreatedBy(),
                    dataset.getCreatedDttm(),
                    dataset.getUpdatedBy(),
                    dataset.getUpdatedDttm());
        } catch (Exception e) {
            log.error("Error executing save for SOURCE_DATASET, id={}", dataset.getDatasetId(), e);
            throw new DatasetInternalException("Error saving dataset", e);
        }
    }

    private static String convertToAdditionalInfoJson(Dataset dataset, ObjectMapper objectMapper) {
        try {
            return objectMapper.writeValueAsString(dataset.getAdditionalInfoJson());
        } catch (JsonProcessingException e) {
            throw new DatasetInternalException("No se pudo converti JSON campo additionalInfoJson", e);
        }
    }

    @Override
    public void update(Integer datasetId, Dataset dataset) {
        String schema = databaseConfig.getSchema();
        String sql = String.format(SqlQueryDataset.UPDATE_DATASET, schema);
        ObjectMapper objectMapper = new ObjectMapper();
        String additionalInfoJsonStr = convertToAdditionalInfoJson(dataset, objectMapper);
        try {
            jdbcTemplate.update(sql,
                    dataset.getConnectionId(),
                    dataset.getDatasetName(),
                    dataset.getIsActive(),
                    dataset.getDatasetDescription(),
                    dataset.getDatasetExtractionQueryDescription(),
                    additionalInfoJsonStr,
                    dataset.getUpdatedBy(),
                    dataset.getUpdatedDttm(),
                    datasetId);
        } catch (Exception e) {
            log.error("Error executing update for SOURCE_DATASET, id={}", datasetId, e);
            throw new DatasetInternalException("Error updating dataset", e);
        }
    }

    @Override
    public void deleteById(Integer datasetId) {
        String schema = databaseConfig.getSchema();
        String sql = String.format(SqlQueryDataset.DELETE_DATASET, schema);
        try {
            jdbcTemplate.update(sql, datasetId);
        } catch (Exception e) {
            log.error("Error executing deleteById for SOURCE_DATASET, id={}", datasetId, e);
            throw new DatasetInternalException("Error deleting dataset", e);
        }
    }

    @Override
    public boolean existsById(Integer datasetId) {
        String schema = databaseConfig.getSchema();
        String sql = String.format(SqlQueryDataset.SELECT_EXISTS, schema);
        try {
            int count = jdbcTemplate.queryForObject(sql, Integer.class, datasetId);
            return count > 0;
        } catch (Exception e) {
            log.error("Error executing existsById for SOURCE_DATASET, id={}", datasetId, e);
            throw new DatasetInternalException("Error checking existence of dataset", e);
        }
    }

    public boolean existConnectionId(Integer connectionId) {
        String schema = databaseConfig.getSchema();
        String sql = String.format(SqlQueryDataset.SELECT_EXISTS_BY_CONNECTION_ID, schema);
        try {
            int count = jdbcTemplate.queryForObject(sql, Integer.class, connectionId);
            return count > 0;
        } catch (Exception e) {
            log.error("Error executing existConnectionId for SOURCE_DATASET, connectionId={}", connectionId, e);
            throw new DatasetInternalException("Error checking existence of dataset by connection id", e);
        }
    }

    private RowMapper<Dataset> datasetRowMapper() {
        return (rs, rowNum) -> {
            Dataset dataset = new Dataset();
            dataset.setDatasetId(rs.getInt("Dataset_Id"));

            if (rs.getObject("Connection_Id") != null) {
                dataset.setConnectionId(rs.getInt("Connection_Id"));
            }

            dataset.setDatasetName(rs.getString("Dataset_Name"));
            dataset.setIsActive(rs.getString("Is_Active"));
            dataset.setDatasetDescription(rs.getString("Dataset_Description"));
            dataset.setDatasetExtractionQueryDescription(rs.getString("Dataset_Extraction_Query_Description"));
            ObjectMapper objectMapper = new ObjectMapper();
            String json = rs.getString("Additional_Info_Json");
            if (json != null && !json.isBlank()) {
                try {
                    dataset.setAdditionalInfoJson(objectMapper.readValue(json, Map.class));
                } catch (JsonProcessingException e) {
                    throw new DatasetInternalException("Error parsing Additional_Info_Json", e);
                }
            } else {
                dataset.setAdditionalInfoJson(new HashMap<>());
            }
            if (rs.getObject("Created_By") != null) {
                dataset.setCreatedBy(rs.getInt("Created_By"));
            }

            if (rs.getTimestamp("Created_Dttm") != null) {
                dataset.setCreatedDttm(rs.getTimestamp("Created_Dttm").toLocalDateTime());
            }

            if (rs.getObject("Updated_By") != null) {
                dataset.setUpdatedBy(rs.getInt("Updated_By"));
            }

            if (rs.getTimestamp("Updated_Dttm") != null) {
                dataset.setUpdatedDttm(rs.getTimestamp("Updated_Dttm").toLocalDateTime());
            }

            return dataset;
        };
    }

    // extracted filter builder
    private void applyDatasetFilters(DatasetSearchCriteria criteria, StringBuilder sql, List<Object> params) {
        appendEqIfNotNull(sql, params, "Dataset_Id", criteria.getDatasetId());
        appendEqIfNotNull(sql, params, "Connection_Id", criteria.getConnectionId());
        appendLikeIfNotBlank(sql, params, "Dataset_Name", criteria.getDatasetName());
        appendEqIfNotBlank(sql, params, "Is_Active", criteria.getIsActive());
        appendLikeIfNotBlank(sql, params, "Dataset_Description", criteria.getDatasetDescription());
        appendLikeIfNotBlank(sql, params, "Dataset_Extraction_Query_Description", criteria.getDatasetExtractionQueryDescription());
        appendLikeIfNotBlank(sql, params, "Additional_Info_Json", criteria.getAdditionalInfoJson());
        appendEqIfNotNull(sql, params, "Created_By", criteria.getCreatedBy());
        appendDateGeIfNotNull(sql, params, "Created_Dttm", criteria.getCreatedDttmFrom());
        appendDateLeIfNotNull(sql, params, "Created_Dttm", criteria.getCreatedDttmTo());
        appendEqIfNotNull(sql, params, "Updated_By", criteria.getUpdatedBy());
        appendDateGeIfNotNull(sql, params, "Updated_Dttm", criteria.getUpdatedDttmFrom());
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

    private void appendDateGeIfNotNull(StringBuilder sql, List<Object> params, String column, java.time.LocalDateTime value) {
        if (value != null) {
            sql.append(" AND ").append(column).append(" >= ?");
            params.add(Timestamp.valueOf(value));
        }
    }

    private void appendDateLeIfNotNull(StringBuilder sql, List<Object> params, String column, java.time.LocalDateTime value) {
        if (value != null) {
            sql.append(" AND ").append(column).append(" <= ?");
            params.add(Timestamp.valueOf(value));
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



}

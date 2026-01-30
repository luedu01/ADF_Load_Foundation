package com.load.fundation.dataset.domain.port.out;

import com.load.fundation.dataset.domain.model.DatasetSchema;
import com.load.fundation.dataset.application.dto.DatasetSchemaSearchCriteria;

import java.util.List;

/**
 * Puerto de salida para persistencia de DatasetSchema (SOURCE_DATASET_SCHEMA)
 */
public interface DatasetSchemaPersistencePort {
    List<DatasetSchema> findAll();
    DatasetSchema findById(Integer schemaId);
    void save(DatasetSchema datasetSchema);
    void update(Integer schemaId, DatasetSchema datasetSchema);
    void deleteById(Integer schemaId);
    boolean existsById(Integer schemaId);

    List<DatasetSchema> findByCriteria(DatasetSchemaSearchCriteria criteria);
}

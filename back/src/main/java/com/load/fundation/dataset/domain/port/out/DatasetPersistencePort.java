package com.load.fundation.dataset.domain.port.out;

import com.load.fundation.dataset.application.dto.DatasetSearchCriteria;
import com.load.fundation.dataset.domain.model.Dataset;

import java.util.List;

/**
 * Puerto de salida para persistencia de Dataset
 */
public interface DatasetPersistencePort {
    List<Dataset> findAll();
    Dataset findById(Integer datasetId);
    List<Dataset> findByCriteria(DatasetSearchCriteria criteria);
    void save(Dataset dataset);
    void update(Integer datasetId, Dataset dataset);
    void deleteById(Integer datasetId);
    boolean existsById(Integer datasetId);
}

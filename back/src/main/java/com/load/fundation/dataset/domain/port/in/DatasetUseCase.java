package com.load.fundation.dataset.domain.port.in;
import com.load.fundation.dataset.application.dto.DatasetDto;
import com.load.fundation.dataset.application.dto.DatasetSearchCriteria;
import com.load.fundation.dataset.exception.DatasetValidateException;

import java.util.List;
/**
 * Puerto de entrada para casos de uso de Dataset
 */
public interface DatasetUseCase {
    List<DatasetDto> getDatasets();
    DatasetDto getDatasetById(Integer datasetId);
    List<DatasetDto> searchDatasets(DatasetSearchCriteria criteria);
    void createDataset(DatasetDto datasetDto) throws DatasetValidateException;
    void updateDataset(Integer datasetId, DatasetDto datasetDto) throws DatasetValidateException;
    void deleteDataset(Integer datasetId);
    boolean existsById(Integer datasetId);
}
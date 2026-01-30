package com.load.fundation.dataset.application.service;

import com.load.fundation.dataset.application.dto.DatasetDto;
import com.load.fundation.dataset.application.dto.DatasetSearchCriteria;
import com.load.fundation.dataset.application.mapper.DatasetMapper;
import com.load.fundation.dataset.domain.model.Dataset;
import com.load.fundation.dataset.domain.port.in.DatasetUseCase;
import com.load.fundation.dataset.domain.port.out.DatasetPersistencePort;
import com.load.fundation.dataset.exception.DatasetValidateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

/**
 * Servicio de aplicación para Dataset
 */
@Service
@RequiredArgsConstructor
public class DatasetService implements DatasetUseCase {

    private final DatasetPersistencePort datasetPersistencePort;
    private final DatasetMapper datasetMapper;

    @Override
    public List<DatasetDto> getDatasets() {
        return datasetPersistencePort.findAll().stream()
                .map(datasetMapper::toDto)
                .toList();
    }

    @Override
    public DatasetDto getDatasetById(Integer datasetId) {
        Dataset dataset = datasetPersistencePort.findById(datasetId);
        return dataset != null ? datasetMapper.toDto(dataset) : null;
    }

    @Override
    public List<DatasetDto> searchDatasets(DatasetSearchCriteria criteria) {
        return datasetPersistencePort.findByCriteria(criteria).stream()
                .map(datasetMapper::toDto)
                .toList();
    }

    @Override
    public void createDataset(DatasetDto datasetDto) throws DatasetValidateException {
        Dataset dataset = datasetMapper.toDomain(datasetDto);
        dataset.setUpdatedBy(null);
        validateRules(dataset);
        LocalDateTime now = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
        dataset.setCreatedDttm(now);
        dataset.setUpdatedDttm(null);
        datasetPersistencePort.save(dataset);
    }

    private void validateRules(Dataset dataset) throws DatasetValidateException {
        if (datasetPersistencePort.existsById(dataset.getConnectionId())){
            throw new DatasetValidateException("La Connection con Id: " + dataset.getDatasetId() + " no existe.");
        }
    }

    @Override
    public void updateDataset(Integer datasetId, DatasetDto datasetDto) throws DatasetValidateException {
        Dataset dataset = datasetMapper.toDomain(datasetDto);
        dataset.setCreatedBy(null);
        if (!existsById(datasetId)) {
            throw new DatasetValidateException("El Dataset con id " + datasetId + " no existe.");
        }
        LocalDateTime now = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
        dataset.setUpdatedDttm(now);
        datasetPersistencePort.update(datasetId, dataset);
    }

    @Override
    public void deleteDataset(Integer datasetId) {
        datasetPersistencePort.deleteById(datasetId);
    }

    @Override
    public boolean existsById(Integer datasetId) {
        return datasetPersistencePort.existsById(datasetId);
    }
}

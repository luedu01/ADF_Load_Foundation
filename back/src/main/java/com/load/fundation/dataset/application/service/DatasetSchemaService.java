package com.load.fundation.dataset.application.service;

import com.load.fundation.dataset.application.dto.DatasetSchemaDto;
import com.load.fundation.dataset.application.dto.DatasetSchemaSearchCriteria;
import com.load.fundation.dataset.application.mapper.DatasetSchemaMapper;
import com.load.fundation.dataset.domain.model.DatasetSchema;
import com.load.fundation.dataset.domain.port.in.DatasetSchemaUseCase;
import com.load.fundation.dataset.domain.port.in.DatasetUseCase;
import com.load.fundation.dataset.domain.port.out.DatasetSchemaPersistencePort;
import com.load.fundation.dataset.exception.DatasetValidateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

/**
 * Servicio de aplicación para DatasetSchema que implementa el puerto de entrada.
 */
@Service
@RequiredArgsConstructor
public class DatasetSchemaService implements DatasetSchemaUseCase {

    private final DatasetSchemaPersistencePort datasetSchemaPersistencePort;
    private final DatasetSchemaMapper datasetSchemaMapper;
    private final DatasetUseCase datasetUseCase;

    @Override
    public List<DatasetSchemaDto> getAllSchemas() {
        List<DatasetSchema> schemas = datasetSchemaPersistencePort.findAll();
        return schemas.stream().map(datasetSchemaMapper::toDto).toList();
    }

    @Override
    public DatasetSchemaDto getSchemaById(Integer schemaId) {
        DatasetSchema schema = datasetSchemaPersistencePort.findById(schemaId);
        return schema != null ? datasetSchemaMapper.toDto(schema) : null;
    }

    @Override
    public void createSchema(DatasetSchemaDto datasetSchemaDto) throws DatasetValidateException {
        DatasetSchema schema = datasetSchemaMapper.toDomain(datasetSchemaDto);
        validateRules(schema);
        schema.setUpdatedBy(null);
        LocalDateTime now = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
        schema.setCreatedDttm(now);
        schema.setUpdatedDttm(null);
        datasetSchemaPersistencePort.save(schema);
    }

    private void validateRules(DatasetSchema schema) throws DatasetValidateException {
        if (!datasetUseCase.existsById(schema.getDatasetId())) {
            throw new DatasetValidateException("El Dataset con id " + schema.getDatasetId() + " no existe.");
        }
    }

    @Override
    public void updateSchema(Integer schemaId, DatasetSchemaDto datasetSchemaDto) throws DatasetValidateException {
        DatasetSchema schema = datasetSchemaMapper.toDomain(datasetSchemaDto);
        schema.setCreatedBy(null);
        if (!existsById(schemaId)) {
            throw new DatasetValidateException("El DatasetSchema con id " + schemaId + " no existe.");
        }
        LocalDateTime now = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
        schema.setUpdatedDttm(now);
        datasetSchemaPersistencePort.update(schemaId, schema);
    }

    @Override
    public void deleteSchema(Integer schemaId) {
        datasetSchemaPersistencePort.deleteById(schemaId);
    }

    @Override
    public boolean existsById(Integer schemaId) {
        return datasetSchemaPersistencePort.existsById(schemaId);
    }

    @Override
    public List<DatasetSchemaDto> findByCriteria(DatasetSchemaSearchCriteria criteria) {
        List<DatasetSchema> results = datasetSchemaPersistencePort.findByCriteria(criteria);
        return results.stream().map(datasetSchemaMapper::toDto).toList();
    }
}

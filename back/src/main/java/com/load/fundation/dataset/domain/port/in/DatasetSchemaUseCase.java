package com.load.fundation.dataset.domain.port.in;

import com.load.fundation.dataset.application.dto.DatasetSchemaDto;
import com.load.fundation.dataset.application.dto.DatasetSchemaSearchCriteria;
import com.load.fundation.dataset.exception.DatasetValidateException;

import java.util.List;

/**
 * Puerto de entrada para casos de uso de DatasetSchema
 */
public interface DatasetSchemaUseCase {
    List<DatasetSchemaDto> getAllSchemas();
    DatasetSchemaDto getSchemaById(Integer schemaId);
    void createSchema(DatasetSchemaDto datasetSchemaDto) throws DatasetValidateException;
    void updateSchema(Integer schemaId, DatasetSchemaDto datasetSchemaDto) throws DatasetValidateException;
    void deleteSchema(Integer schemaId);
    boolean existsById(Integer schemaId);

    List<DatasetSchemaDto> findByCriteria(DatasetSchemaSearchCriteria criteria);
}

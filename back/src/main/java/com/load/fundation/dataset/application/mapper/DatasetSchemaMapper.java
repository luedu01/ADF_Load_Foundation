package com.load.fundation.dataset.application.mapper;

import com.load.fundation.dataset.application.dto.DatasetSchemaDto;
import com.load.fundation.dataset.domain.model.DatasetSchema;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

/**
 * Mapper de MapStruct para conversión entre DatasetSchema y DatasetSchemaDto
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DatasetSchemaMapper {

    /**
     * Convierte una entidad DatasetSchema a DatasetSchemaDto
     * @param datasetSchema Entidad de dominio
     * @return DTO
     */
    DatasetSchemaDto toDto(DatasetSchema datasetSchema);

    /**
     * Convierte un DatasetSchemaDto a entidad DatasetSchema
     * @param datasetSchemaDto DTO
     * @return Entidad de dominio
     */
    DatasetSchema toDomain(DatasetSchemaDto datasetSchemaDto);
}

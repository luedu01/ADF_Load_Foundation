package com.load.fundation.dataset.application.mapper;

import com.load.fundation.dataset.application.dto.DatasetDto;
import com.load.fundation.dataset.domain.model.Dataset;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

/**
 * Mapper de MapStruct para conversión entre Dataset (entidad de dominio) y DatasetDto
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DatasetMapper {

    /**
     * Convierte una entidad Dataset a DatasetDto
     * @param dataset Entidad de dominio
     * @return DTO
     */
    DatasetDto toDto(Dataset dataset);

    /**
     * Convierte un DatasetDto a entidad Dataset
     * @param datasetDto DTO
     * @return Entidad de dominio
     */
    Dataset toDomain(DatasetDto datasetDto);
}

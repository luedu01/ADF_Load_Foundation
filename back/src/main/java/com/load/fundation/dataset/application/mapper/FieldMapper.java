package com.load.fundation.dataset.application.mapper;

import com.load.fundation.dataset.application.dto.FieldDto;
import com.load.fundation.dataset.domain.model.Field;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

/**
 * Mapper de MapStruct para conversión entre Field y FieldDto
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface FieldMapper {

    /**
     * Convierte una entidad Field a FieldDto
     * @param field Entidad de dominio
     * @return DTO
     */
    FieldDto toDto(Field field);

    /**
     * Convierte un FieldDto a entidad Field
     * @param fieldDto DTO
     * @return Entidad de dominio
     */
    Field toDomain(FieldDto fieldDto);
}

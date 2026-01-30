package com.load.fundation.dataset.application.mapper;

import com.load.fundation.dataset.application.dto.FieldDatatypeDto;
import com.load.fundation.dataset.domain.model.FieldDatatype;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

/**
 * Mapper de MapStruct para conversión entre FieldDatatype y FieldDatatypeDto
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface FieldDatatypeMapper {

    /**
     * Convierte una entidad FieldDatatype a FieldDatatypeDto
     * @param fieldDatatype Entidad de dominio
     * @return DTO
     */
    FieldDatatypeDto toDto(FieldDatatype fieldDatatype);

    /**
     * Convierte un FieldDatatypeDto a entidad FieldDatatype
     * @param fieldDatatypeDto DTO
     * @return Entidad de dominio
     */
    FieldDatatype toDomain(FieldDatatypeDto fieldDatatypeDto);
}

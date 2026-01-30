package com.load.fundation.dataset.application.mapper;

import com.load.fundation.dataset.application.dto.FieldSemanticDatatypeDto;
import com.load.fundation.dataset.domain.model.FieldSemanticDatatype;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

/**
 * Mapper de MapStruct para conversión entre FieldSemanticDatatype y FieldSemanticDatatypeDto
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface FieldSemanticDatatypeMapper {

    /**
     * Convierte una entidad FieldSemanticDatatype a FieldSemanticDatatypeDto
     * @param fieldSemanticDatatype Entidad de dominio
     * @return DTO
     */
    FieldSemanticDatatypeDto toDto(FieldSemanticDatatype fieldSemanticDatatype);

    /**
     * Convierte un FieldSemanticDatatypeDto a entidad FieldSemanticDatatype
     * @param fieldSemanticDatatypeDto DTO
     * @return Entidad de dominio
     */
    FieldSemanticDatatype toDomain(FieldSemanticDatatypeDto fieldSemanticDatatypeDto);
}

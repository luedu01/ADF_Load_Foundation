package com.load.fundation.dataset.application.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * DTO para transferencia de datos de FieldDatatype
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FieldDatatypeDto {
    private Integer fieldDatatypeId;

    @Size(max = 100, message = "El tamaño máximo de dataTypeName es 100 caracteres")
    private String dataTypeName;

    @Size(max = 250, message = "El tamaño máximo de dataTypeDescription es 250 caracteres")
    private String dataTypeDescription;

    private Integer createdBy;
    private Integer updatedBy;
}

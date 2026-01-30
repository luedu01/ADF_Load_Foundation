package com.load.fundation.dataset.application.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * DTO para transferencia de datos de FieldSemanticDatatype
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FieldSemanticDatatypeDto {

    private Integer fieldSemanticDatatypeId;
    private Integer fieldDatatypeId;

    @Size(max = 100, message = "El tamaño máximo de dataTypeName es 100 caracteres")
    private String dataTypeName;

    @Size(max = 250, message = "El tamaño máximo de datatypeDescription es 250 caracteres")
    private String datatypeDescription;

    private Integer createdBy;
    private Integer updatedBy;
}

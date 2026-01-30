package com.load.fundation.dataset.application.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * DTO para transferencia de datos de Field
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FieldDto {

    private Integer fieldId;

    private Integer schemaId;

    private Integer positionNum;

    @Size(max = 100, message = "El tamaño máximo de fieldName es 100 caracteres")
    private String fieldName;

    @Size(max = 250, message = "El tamaño máximo de fieldDescription es 250 caracteres")
    private String fieldDescription;

    private Integer fieldDatatypeId;

    private Integer length;

    private Integer precision;

    @Size(max = 1, message = "El tamaño máximo de isNullable es 1 carácter")
    private String isNullable;

    @Size(max = 1, message = "El tamaño máximo de isPrimaryKey es 1 carácter")
    private String isPrimaryKey;

    private Integer fieldSemanticDatatypeId;

    @Size(max = 5000, message = "El tamaño máximo de sourcePathExpression es 5000 caracteres")
    private String sourcePathExpression;

    private Integer createdBy;
    private Integer updatedBy;
}

package com.load.fundation.dataset.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * Entidad de dominio Field - Tabla SOURCE_FIELD
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Field {
    private Integer fieldId;
    private Integer schemaId;
    private Integer positionNum;
    private String fieldName;
    private String fieldDescription;
    private Integer fieldDatatypeId;
    private Integer length;
    private Integer precision;
    private String isNullable;
    private String isPrimaryKey;
    private Integer fieldSemanticDatatypeId;
    private String sourcePathExpression;
    private Integer createdBy;
    private LocalDateTime createdDttm;
    private Integer updatedBy;
    private LocalDateTime updatedDttm;
}

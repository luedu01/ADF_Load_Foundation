package com.load.fundation.dataset.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * Entidad de dominio FieldDatatype - Tabla SOURCE_FIELD_DATATYPE
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FieldDatatype {
    private Integer fieldSemanticDatatypeId;
    private Integer fieldDatatypeId;
    private String dataTypeName;
    private String dataTypeDescription;
    private Integer createdBy;
    private LocalDateTime createdDttm;
    private Integer updatedBy;
    private LocalDateTime updatedDttm;
}

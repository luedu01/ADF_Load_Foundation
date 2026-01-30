package com.load.fundation.dataset.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * Entidad de dominio DatasetSchema - Tabla SOURCE_DATASET_SCHEMA
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DatasetSchema {
    private Integer schemaId;
    private Integer datasetId;
    private Integer datasetSchemaVersionNum;
    private String isDraft;
    private String isCurrent;
    private LocalDateTime effectiveFromDttm;
    private LocalDateTime effectiveToDttm;
    private String notesTxt;
    private Integer createdBy;
    private LocalDateTime createdDttm;
    private Integer updatedBy;
    private LocalDateTime updatedDttm;
}

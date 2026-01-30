package com.load.fundation.dataset.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Entidad de dominio Dataset - Tabla SOURCE_DATASET
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dataset {
    private Integer datasetId;
    private Integer connectionId;
    private String datasetName;
    private String isActive;
    private String datasetDescription;
    private String datasetExtractionQueryDescription;
    private Map<String, Object> additionalInfoJson;
    private Integer createdBy;
    private LocalDateTime createdDttm;
    private Integer updatedBy;
    private LocalDateTime updatedDttm;
}

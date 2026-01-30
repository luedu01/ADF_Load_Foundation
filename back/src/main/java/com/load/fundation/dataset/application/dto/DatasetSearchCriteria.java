package com.load.fundation.dataset.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * DTO para criterios de búsqueda dinámica de Dataset
 * Todos los campos son opcionales para permitir búsquedas flexibles
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DatasetSearchCriteria {
    private Integer datasetId;
    private Integer connectionId;
    private String datasetName;
    private String isActive;
    private String datasetDescription;
    private String datasetExtractionQueryDescription;
    private String additionalInfoJson;
    private Integer createdBy;
    private LocalDateTime createdDttmFrom;
    private LocalDateTime createdDttmTo;
    private Integer updatedBy;
    private LocalDateTime updatedDttmFrom;
    private LocalDateTime updatedDttmTo;
}

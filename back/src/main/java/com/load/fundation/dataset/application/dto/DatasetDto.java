package com.load.fundation.dataset.application.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

/**
 * DTO para transferencia de datos de Dataset
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DatasetDto {
    private Integer datasetId;

    private Integer connectionId;

    @Size(max = 100, message = "El tamaño máximo de datasetName es 100 caracteres")
    private String datasetName;

    @Size(max = 1, message = "El tamaño máximo de isActive es 1 carácter")
    private String isActive;

    @Size(max = 500, message = "El tamaño máximo de datasetDescription es 500 caracteres")
    private String datasetDescription;

    @Size(max = 5000, message = "El tamaño máximo de datasetExtractionQueryDescription es 5000 caracteres")
    private String datasetExtractionQueryDescription;

    @NotNull(message = "El campo additionalInfoJson no puede ser nulo")
    @NotEmpty(message = "El campo additionalInfoJson no puede estar vacío")
    @Size(max = 32000, message = "El tamaño máximo de additionalInfoJson es 32000 caracteres")
    private Map<String, Object> additionalInfoJson;

    private Integer createdBy;
    private Integer updatedBy;
}

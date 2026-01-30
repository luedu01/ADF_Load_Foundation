package com.load.fundation.dataset.application.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * DTO para transferencia de datos de DatasetSchema
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DatasetSchemaDto {
    private Integer schemaId;

    private Integer datasetId;

    private Integer datasetSchemaVersionNum;

    @Size(max = 1, message = "El tamaño máximo de isDraft es 1 carácter")
    private String isDraft;

    @Size(max = 1, message = "El tamaño máximo de isCurrent es 1 carácter")
    private String isCurrent;

    private LocalDateTime effectiveFromDttm;
    private LocalDateTime effectiveToDttm;

    @Size(max = 5000, message = "El tamaño máximo de notesTxt es 5000 caracteres")
    private String notesTxt;

    private Integer createdBy;
    private Integer updatedBy;
}

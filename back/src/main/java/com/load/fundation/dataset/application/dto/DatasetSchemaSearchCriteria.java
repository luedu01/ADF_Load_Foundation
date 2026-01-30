package com.load.fundation.dataset.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DatasetSchemaSearchCriteria {
    private Integer schemaId;
    private Integer datasetId;
    private Integer datasetSchemaVersionNum;
    private String isDraft;
    private String isCurrent;
    private LocalDateTime effectiveFromDttmFrom;
    private LocalDateTime effectiveFromDttmTo;
    private String notesTxt;
    private Integer createdBy;
    private LocalDateTime createdDttmTo;
    private Integer updatedBy;
    private LocalDateTime updatedDttmTo;
}

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
public class FieldSearchCriteria {
    private Integer fieldId;
    private Integer schemaId;
    private Integer positionNum;
    private String fieldName;
    private String fieldDescription;
    private Integer fieldDatatypeId;
    private Integer fieldSemanticDatatypeId;
    private String isNullable;
    private String isPrimaryKey;
    private Integer createdBy;
    private LocalDateTime createdDttm;
    private Integer updatedBy;
    private LocalDateTime updatedDttm;
}

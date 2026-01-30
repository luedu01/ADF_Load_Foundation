package com.load.fundation.dataset.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FieldSemanticDatatypeSearchCriteria {
    private Integer fieldSemanticDatatypeId;
    private Integer fieldDatatypeId;
    private String dataTypeName;
    private String datatypeDescription;
}

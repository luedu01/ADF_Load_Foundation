package com.load.fundation.dataset.application.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FieldDatatypeSearchCriteria {
    private Integer fieldDatatypeId;
    private String dataTypeName;
    private String dataTypeDescription;
}

package com.load.fundation.connection.domain.model;

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
public class ConnectionSubtype {

    private Integer id;
    private Integer typeId;
    private String name;
    private Integer createdBy;
    private LocalDateTime createdDttm;
    private Integer updatedBy;
    private LocalDateTime updatedDttm;
}
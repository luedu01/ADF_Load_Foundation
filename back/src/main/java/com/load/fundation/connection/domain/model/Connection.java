package com.load.fundation.connection.domain.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Connection {
    private Integer id;
    private String  name;
    private String  description;

    private ConnectionType type;
    private ConnectionSubtype subtype;

    private String  serverName;
    private String  additionalInfo;

    private ConnectionCredential credential;
    private ConnectionToken token;

    private Integer groupId;
    private Integer createdBy;
    private LocalDateTime createdDttm;
    private Integer updatedBy;
    private LocalDateTime updatedDttm;

}


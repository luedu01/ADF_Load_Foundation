package com.load.fundation.connection.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class ConnectionDto {
    private Integer id;
    private String name;
    private String description;
    private Integer typeId;
    private String typeName;

    private Integer subtypeId;
    private String subtypeName;

    private String serverName;
    private String additionalInfo; //JSON

    private String credentialUserName;
    private Boolean hasCredential;

    private String tokenName;
    private Boolean tokenActive;

    private Integer groupId;
}

package com.load.fundation.connection.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class ConnectionToken {
    private Integer tokenId;
    private Integer connectionId;
    private String tokenName;
    private String tokenHash;
    private Boolean active;
}
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

public class ConnectionCredential {
        private Integer connectionId;
        private String userName;
        private String passwordHash; // Sin exposicion en DTO
}


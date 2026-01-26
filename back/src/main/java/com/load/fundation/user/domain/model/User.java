package com.load.fundation.user.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer userId;
    private String userName;
    private String emailDesc;
    private String fullName;
    private String isActive;
    private LocalDateTime lastLoginDttm;
    private Integer createdBy;
    private LocalDateTime createdDttm;
    private Integer updatedBy;
    private LocalDateTime updatedDttm;
}

package com.example.demo.user.api.dto;

import java.time.LocalDateTime;

public class UserDto {

    private Integer userId;
    private String userName;
    private String emailDesc;
    private String fullName;
    private String isActive;
    private LocalDateTime lastLoginDttm;
    private LocalDateTime createdDttm;
    private LocalDateTime updatedDttm;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailDesc() {
        return emailDesc;
    }

    public void setEmailDesc(String emailDesc) {
        this.emailDesc = emailDesc;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public LocalDateTime getLastLoginDttm() {
        return lastLoginDttm;
    }

    public void setLastLoginDttm(LocalDateTime lastLoginDttm) {
        this.lastLoginDttm = lastLoginDttm;
    }

    public LocalDateTime getCreatedDttm() {
        return createdDttm;
    }

    public void setCreatedDttm(LocalDateTime createdDttm) {
        this.createdDttm = createdDttm;
    }

    public LocalDateTime getUpdatedDttm() {
        return updatedDttm;
    }

    public void setUpdatedDttm(LocalDateTime updatedDttm) {
        this.updatedDttm = updatedDttm;
    }
}


package com.load.fundation.user.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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
public class UserDto {
    @NotNull(message = "userId no puede estar vacío")
    @Positive(message = "userId debe ser un número positivo")
    private Integer userId;

    @Size(max = 100, message = "El tamaño máximo de userName es 100 caracteres")
    private String userName;

    @Email(message = "emailDesc debe ser un correo válido")
    @Size(max = 200, message = "El tamaño máximo de emailDesc es 200 caracteres")
    private String emailDesc;

    @Size(max = 200, message = "El tamaño máximo de fullName es 200 caracteres")
    private String fullName;

    @Size(max = 1, message = "El tamaño máximo de isActive es 1 carácter")
    private String isActive;

    private LocalDateTime lastLoginDttm;
    private Integer createdBy;
    private Integer updatedBy;
}

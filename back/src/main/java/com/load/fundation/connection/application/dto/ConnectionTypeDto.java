package com.load.fundation.connection.application.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class ConnectionTypeDto {

    private Integer id;

    @NotNull(message = "El nombre del tipo de conexión no puede ser nulo")
    @NotEmpty(message = "El nombre del tipo de conexión no puede estar vacío")
    @Size(max = 100, message = "tamaño máximo del name es 100 caracteres")
    private String name;

    private Integer createdBy;
    private Integer updatedBy;
}
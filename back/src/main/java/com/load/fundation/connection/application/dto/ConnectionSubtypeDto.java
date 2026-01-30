package com.load.fundation.connection.application.dto;
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
public class ConnectionSubtypeDto {

    private Integer id;

    @NotNull(message = "typeId no puede ser nulo")
    private Integer typeId;

    @Size(max = 100, message = "El tamaño máximo de name es 100 caracteres")
    private String name;

    private Integer createdBy;
    private Integer updatedBy;
}
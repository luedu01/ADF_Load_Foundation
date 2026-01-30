package com.load.fundation.dataset.infrastructure.api;

import com.load.fundation.dataset.application.dto.FieldDatatypeDto;
import com.load.fundation.dataset.domain.port.in.FieldDatatypeUseCase;
import com.load.fundation.dataset.exception.DatasetValidateException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para FieldDatatype
 */
@RestController
@RequestMapping("/field-datatypes")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "FieldDatatype", description = "API para gestión de tipos de datos de campo")
@RequiredArgsConstructor
public class FieldDatatypeController {

    private final FieldDatatypeUseCase fieldDatatypeUseCase;

    @Operation(
            summary = "Obtener todos los tipos de datos de campo",
            description = "Retorna la lista completa de tipos de datos disponibles para los campos"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operación exitosa",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = FieldDatatypeDto.class))
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "No autorizado - Token inválido o expirado"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor"
            )
    })
    @GetMapping
    public ResponseEntity<List<FieldDatatypeDto>> getAllFieldDatatypes() {
        List<FieldDatatypeDto> fieldDatatypes = fieldDatatypeUseCase.getAllFieldDatatypes();
        return ResponseEntity.ok(fieldDatatypes);
    }

    @Operation(
            summary = "Obtener un tipo de dato por ID",
            description = "Retorna un tipo de dato específico según su identificador"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Tipo de dato encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = FieldDatatypeDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Tipo de dato no encontrado"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "No autorizado - Token inválido o expirado"
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<FieldDatatypeDto> getFieldDatatypeById(@PathVariable("id") Integer id) {
        FieldDatatypeDto fieldDatatype = fieldDatatypeUseCase.getFieldDatatypeById(id);
        if (fieldDatatype == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(fieldDatatype);
    }

    @Operation(
            summary = "Crear un nuevo tipo de dato",
            description = "Registra un nuevo tipo de dato para campos en el sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Tipo de dato creado exitosamente"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos de entrada inválidos"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "No autorizado - Token inválido o expirado"
            )
    })
    @PostMapping
    public ResponseEntity<Void> createFieldDatatype(@RequestBody FieldDatatypeDto fieldDatatypeDto) {
        // POST: only createdBy
        fieldDatatypeDto.setUpdatedBy(null);
        fieldDatatypeUseCase.createFieldDatatype(fieldDatatypeDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(
            summary = "Actualizar un tipo de dato existente",
            description = "Modifica los datos de un tipo de dato existente"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Tipo de dato actualizado exitosamente"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Tipo de dato no encontrado"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "No autorizado - Token inválido o expirado"
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateFieldDatatype(
            @PathVariable("id") Integer id,
            @RequestBody FieldDatatypeDto fieldDatatypeDto) throws DatasetValidateException {
        // PUT: only updatedBy
        fieldDatatypeDto.setCreatedBy(null);
        fieldDatatypeUseCase.updateFieldDatatype(id, fieldDatatypeDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(
            summary = "Eliminar un tipo de dato",
            description = "Elimina un tipo de dato del sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Tipo de dato eliminado exitosamente"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Tipo de dato no encontrado"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "No autorizado - Token inválido o expirado"
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFieldDatatype(@PathVariable("id") Integer id) {
        fieldDatatypeUseCase.deleteFieldDatatype(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

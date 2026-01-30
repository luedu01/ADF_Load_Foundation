package com.load.fundation.dataset.infrastructure.api;

import com.load.fundation.dataset.application.dto.FieldDto;
import com.load.fundation.dataset.domain.port.in.FieldUseCase;
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
 * Controlador REST para Field (SOURCE_FIELD)
 */
@RestController
@RequestMapping("/fields")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Field", description = "API para gestión de campos")
@RequiredArgsConstructor
public class FieldController {

    private final FieldUseCase fieldUseCase;

    /**
     * Obtener todos los campos (SOURCE_FIELD).
     *
     * @return lista de {@link FieldDto}
     */
    @Operation(summary = "Obtener todos los campos", description = "Retorna la lista completa de campos (SOURCE_FIELD)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = FieldDto.class))))
    })
    @GetMapping
    public ResponseEntity<List<FieldDto>> getAllFields() {
        List<FieldDto> fields = fieldUseCase.getAllFields();
        return ResponseEntity.ok(fields);
    }

    /**
     * Obtener un campo por su ID.
     *
     * @param id identificador del campo
     * @return {@link FieldDto} o 404 si no existe
     */
    @Operation(summary = "Obtener un campo por ID", description = "Retorna un campo específico según su identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Campo encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = FieldDto.class))),
            @ApiResponse(responseCode = "404", description = "Campo no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<FieldDto> getFieldById(@PathVariable("id") Integer id) {
        FieldDto field = fieldUseCase.getFieldById(id);
        if (field == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(field);
    }

    /**
     * Crear un nuevo campo en SOURCE_FIELD.
     *
     * @param fieldDto DTO con los datos del campo
     * @return 201 CREATED
     */
    @Operation(summary = "Crear un nuevo campo", description = "Registra un nuevo campo en la tabla SOURCE_FIELD")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Campo creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    @PostMapping
    public ResponseEntity<Void> createField(@RequestBody FieldDto fieldDto) throws DatasetValidateException {
        // POST: only createdBy should be considered
        fieldDto.setUpdatedBy(null);
        fieldUseCase.createField(fieldDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Actualizar un campo existente.
     *
     * @param id       identificador del campo
     * @param fieldDto DTO con los datos a actualizar
     * @return 204 NO CONTENT
     */
    @Operation(summary = "Actualizar un campo existente", description = "Modifica los datos de un campo existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Campo actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Campo no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateField(@PathVariable("id") Integer id, @RequestBody FieldDto fieldDto) throws DatasetValidateException {
        fieldDto.setCreatedBy(null);
        fieldUseCase.updateField(id, fieldDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Eliminar un campo por su ID.
     *
     * @param id identificador del campo
     * @return 204 NO CONTENT
     */
    @Operation(summary = "Eliminar un campo", description = "Elimina un campo de la tabla SOURCE_FIELD")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Campo eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Campo no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteField(@PathVariable("id") Integer id) {
        fieldUseCase.deleteField(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

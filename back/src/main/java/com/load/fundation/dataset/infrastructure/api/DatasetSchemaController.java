package com.load.fundation.dataset.infrastructure.api;

import com.load.fundation.dataset.application.dto.DatasetSchemaDto;
import com.load.fundation.dataset.domain.port.in.DatasetSchemaUseCase;
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

@RestController
@RequestMapping("/datasets/schemas")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "DatasetSchema", description = "API para gestión de DatasetSchema")
@RequiredArgsConstructor
public class DatasetSchemaController {

    private final DatasetSchemaUseCase datasetSchemaUseCase;

    @Operation(summary = "Obtener todos los schemas de datasets")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Operación exitosa",
            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = DatasetSchemaDto.class))))})
    @GetMapping
    public ResponseEntity<List<DatasetSchemaDto>> getAllSchemas() {
        List<DatasetSchemaDto> list = datasetSchemaUseCase.getAllSchemas();
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Obtener schema por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DatasetSchemaDto.class))),
            @ApiResponse(responseCode = "404", description = "No encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DatasetSchemaDto> getSchemaById(@PathVariable("id") Integer id) {
        DatasetSchemaDto dto = datasetSchemaUseCase.getSchemaById(id);
        if (dto == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Crear schema de dataset")
    @PostMapping
    public ResponseEntity<Void> createSchema(@RequestBody DatasetSchemaDto dto) throws DatasetValidateException {
        dto.setUpdatedBy(null);
        datasetSchemaUseCase.createSchema(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Actualizar schema de dataset")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateSchema(@PathVariable("id") Integer id, @RequestBody DatasetSchemaDto dto) throws DatasetValidateException {
        dto.setCreatedBy(null);
        datasetSchemaUseCase.updateSchema(id, dto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Eliminar schema de dataset")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchema(@PathVariable("id") Integer id) {
        datasetSchemaUseCase.deleteSchema(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

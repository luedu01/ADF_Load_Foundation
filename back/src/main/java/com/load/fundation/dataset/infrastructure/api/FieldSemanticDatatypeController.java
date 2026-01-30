package com.load.fundation.dataset.infrastructure.api;

import com.load.fundation.dataset.application.dto.FieldSemanticDatatypeDto;
import com.load.fundation.dataset.domain.port.in.FieldSemanticDatatypeUseCase;
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
@RequestMapping("/fields/semantics")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "FieldSemanticDatatype", description = "API para gestión de FieldSemanticDatatype")
@RequiredArgsConstructor
public class FieldSemanticDatatypeController {

    private final FieldSemanticDatatypeUseCase useCase;

    @Operation(summary = "Obtener todos los FieldSemanticDatatype")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Operación exitosa",
            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = FieldSemanticDatatypeDto.class))))})
    @GetMapping
    public ResponseEntity<List<FieldSemanticDatatypeDto>> getAll() {
        return ResponseEntity.ok(useCase.getAllFieldSemanticDatatypes());
    }

    @Operation(summary = "Obtener FieldSemanticDatatype por ID")
    @GetMapping("/{id}")
    public ResponseEntity<FieldSemanticDatatypeDto> getById(@PathVariable("id") Integer id) {
        FieldSemanticDatatypeDto dto = useCase.getFieldSemanticDatatypeById(id);
        if (dto == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Crear FieldSemanticDatatype")
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody FieldSemanticDatatypeDto dto) {
        // POST: only createdBy
        dto.setUpdatedBy(null);
        useCase.createFieldSemanticDatatype(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Actualizar FieldSemanticDatatype")
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Integer id, @RequestBody FieldSemanticDatatypeDto dto) throws DatasetValidateException {
        dto.setCreatedBy(null);
        useCase.updateFieldSemanticDatatype(id, dto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Eliminar FieldSemanticDatatype")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        useCase.deleteFieldSemanticDatatype(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

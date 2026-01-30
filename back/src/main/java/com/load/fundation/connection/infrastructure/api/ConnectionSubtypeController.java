package com.load.fundation.connection.infrastructure.api;

import com.load.fundation.connection.application.dto.ConnectionSubtypeDto;
import com.load.fundation.connection.domain.port.in.ConnectionSubtypeUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/connection-subtypes")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Connection Subtypes", description = "CRUD de subtipos de conexión")
@RequiredArgsConstructor
public class ConnectionSubtypeController {

    private final ConnectionSubtypeUseCase useCase;

    @GetMapping
    @Operation(summary = "Obtiene todos los subtipos de conexion o un registro por tipo de conexion")
    public ResponseEntity<List<ConnectionSubtypeDto>> getAll(
            @RequestParam(required = false) Integer typeId) {

        if (typeId != null) {
            return ResponseEntity.ok(useCase.getByTypeId(typeId));
        }
        return ResponseEntity.ok(useCase.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene un subtipo por identificador Subtipo")
    public ResponseEntity<ConnectionSubtypeDto> getById(@PathVariable Integer id) {
        ConnectionSubtypeDto dto = useCase.getById(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @Operation(summary = "Crea un subtipo de conexion")
    public ResponseEntity<Void> create(@RequestBody ConnectionSubtypeDto dto) {
        useCase.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza un subtipo de conexion")
    public ResponseEntity<Void> update(@PathVariable Integer id,
                                       @RequestBody ConnectionSubtypeDto dto) {
        useCase.update(id, dto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un subtipo de conexion")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        useCase.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
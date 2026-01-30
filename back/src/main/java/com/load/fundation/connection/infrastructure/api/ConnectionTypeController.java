package com.load.fundation.connection.infrastructure.api;

import com.load.fundation.connection.application.dto.ConnectionTypeDto;
import com.load.fundation.connection.domain.port.in.ConnectionTypeUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/connection-types")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Connection Types", description = "CRUD de tipos de conexión")
@RequiredArgsConstructor
public class ConnectionTypeController {

    private final ConnectionTypeUseCase useCase;

    @GetMapping
    @Operation(summary = "Obtiene una lista de tipos de conexion")
    public ResponseEntity<List<ConnectionTypeDto>> getAll() {
        return ResponseEntity.ok(useCase.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene un tipo de conexion por ID")
    public ResponseEntity<ConnectionTypeDto> getById(@PathVariable Integer id) {
        ConnectionTypeDto dto = useCase.getById(id);
        return (dto != null)
                ? ResponseEntity.ok(dto)
                : ResponseEntity.notFound().build();
    }

    @PostMapping
    @Operation(summary = "Actualiza un tipo de conexion")
    public ResponseEntity<Void> create(@RequestBody ConnectionTypeDto dto) {
        useCase.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza un tipo de conexion")
    public ResponseEntity<Void> update(@PathVariable Integer id,
                                       @RequestBody ConnectionTypeDto dto) {
        useCase.update(id, dto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un tipo de conexion")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        useCase.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
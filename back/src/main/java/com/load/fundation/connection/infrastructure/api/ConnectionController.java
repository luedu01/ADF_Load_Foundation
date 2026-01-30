package com.load.fundation.connection.infrastructure.api;

import com.load.fundation.connection.application.dto.ConnectionDto;
import com.load.fundation.connection.domain.port.in.ConnectionUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/connections")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Connection", description = "CRUD de conexión")
public class ConnectionController {

    private final ConnectionUseCase connectionUseCase;

    public ConnectionController(ConnectionUseCase connectionUseCase) {
        this.connectionUseCase = connectionUseCase;
    }

    //EXP -- Exposicion Obtiene las conexiones por grupo
    @Operation(summary = "Obtiene una lista de conexiones por Grupo")
    @GetMapping("/connectionGroup/{id}")
    public ResponseEntity<List<ConnectionDto>> getByGroup(@RequestParam Integer groupId) {
        List<ConnectionDto> connections = connectionUseCase.getConnectionsByGroup(groupId);
        return ResponseEntity.ok(connections);
    }

    //EXP -- Exposicion Obtiene las conexiones
    @Operation(summary = "Obtiene una lista de todas las conexiones")
    @GetMapping("/all")
    public ResponseEntity<List<ConnectionDto>> getAll() {
        List<ConnectionDto> connections = connectionUseCase.getAllConnections() ;
        return ResponseEntity.ok(connections);
    }

    // EXP -- Exposicion obtiene una conexin por ID.
    @Operation(summary = "Obtiene una conexion por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ConnectionDto> getById(@PathVariable Integer id) {
        ConnectionDto dto = connectionUseCase.getConnectionById(id);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Crea una conexion")
    //EXP -- Exposicion Crea una conexion.
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody ConnectionDto dto) {
        connectionUseCase.createConnection(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //EXP -- Actualiza una conexion.
    @Operation(summary = "Actualiza una conexion")
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id,
                                       @RequestBody ConnectionDto dto) {
        connectionUseCase.updateConnection(id, dto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
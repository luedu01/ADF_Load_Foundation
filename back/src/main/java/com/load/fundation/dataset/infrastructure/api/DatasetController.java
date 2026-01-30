package com.load.fundation.dataset.infrastructure.api;

import com.load.fundation.dataset.application.dto.DatasetDto;
import com.load.fundation.dataset.application.dto.DatasetSearchCriteria;
import com.load.fundation.dataset.domain.port.in.DatasetUseCase;
import com.load.fundation.dataset.exception.DatasetValidateException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para Dataset
 */
@RestController
@RequestMapping("/datasets")
@Tag(name = "DataSet", description = "API para gestión de DataSets")
@SecurityRequirement(name = "bearerAuth")
public class DatasetController {

    private final DatasetUseCase datasetUseCase;

    public DatasetController(DatasetUseCase datasetUseCase) {
        this.datasetUseCase = datasetUseCase;
    }

    @Operation(summary = "Obtener lista de datasets")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = DatasetDto.class))))
    })
    @GetMapping
    public List<DatasetDto> getDatasets() {
        return datasetUseCase.getDatasets();
    }

    @Operation(summary = "Buscar datasets por criterios dinámicos",
            description = "Permite buscar datasets filtrando por uno o más campos. " +
                    "Todos los criterios son opcionales. Los campos de texto usan búsqueda LIKE.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Búsqueda exitosa",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = DatasetDto.class))))
    })
    @PostMapping("/search")
    public ResponseEntity<List<DatasetDto>> searchDatasets(@RequestBody DatasetSearchCriteria criteria) {
        List<DatasetDto> results = datasetUseCase.searchDatasets(criteria);
        return ResponseEntity.ok(results);
    }


    @Operation(summary = "Obtener un dataset por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DatasetDto.class))),
            @ApiResponse(responseCode = "404", description = "Dataset no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DatasetDto> getDatasetById(@PathVariable("id") Integer id) {
        DatasetDto dataset = datasetUseCase.getDatasetById(id);
        if (dataset == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(dataset);
    }

    @Operation(summary = "Crear un dataset")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Dataset creado")
    })
    @PostMapping
    public ResponseEntity<Void> createDataset(@RequestBody DatasetDto dataset) throws DatasetValidateException {
        // POST: only createdBy
        dataset.setUpdatedBy(null);
        datasetUseCase.createDataset(dataset);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Actualizar un dataset existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Dataset actualizado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateDataset(@PathVariable("id") Integer id, @RequestBody DatasetDto dataset) throws DatasetValidateException {
        // PUT: only updatedBy
        dataset.setCreatedBy(null);
        datasetUseCase.updateDataset(id, dataset);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Eliminar un dataset")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Dataset eliminado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDataset(@PathVariable("id") Integer id) {
        datasetUseCase.deleteDataset(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

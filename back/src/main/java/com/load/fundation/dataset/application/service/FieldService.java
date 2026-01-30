package com.load.fundation.dataset.application.service;

import com.load.fundation.dataset.application.dto.FieldDto;
import com.load.fundation.dataset.application.dto.FieldSearchCriteria;
import com.load.fundation.dataset.application.mapper.FieldMapper;
import com.load.fundation.dataset.domain.model.Field;
import com.load.fundation.dataset.domain.port.in.DatasetSchemaUseCase;
import com.load.fundation.dataset.domain.port.in.FieldDatatypeUseCase;
import com.load.fundation.dataset.domain.port.in.FieldSemanticDatatypeUseCase;
import com.load.fundation.dataset.domain.port.in.FieldUseCase;
import com.load.fundation.dataset.domain.port.out.FieldPersistencePort;
import com.load.fundation.dataset.exception.DatasetValidateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

/**
 * Servicio de aplicación que implementa el puerto de entrada {@link FieldUseCase}.
 *
 * Encargado de orquestar las operaciones CRUD sobre la entidad Field (SOURCE_FIELD).
 * Usa {@link FieldPersistencePort} para persistencia y {@link FieldMapper} para
 * conversión entre entidad y DTO.
 */
@Service
@RequiredArgsConstructor
public class FieldService implements FieldUseCase {

    private final FieldPersistencePort fieldPersistencePort;
    private final FieldMapper fieldMapper;
    private final FieldDatatypeUseCase fieldDatatypeUseCase;
    private final FieldSemanticDatatypeUseCase fieldSemanticDatatypeUseCase;
    private final DatasetSchemaUseCase datasetSchemaUseCase;

    /**
     * Obtiene todos los campos registrados en la base de datos.
     *
     * @return lista de {@link FieldDto}. Si no existen registros, retorna una lista vacía.
     */
    @Override
    public List<FieldDto> getAllFields() {
        List<Field> fields = fieldPersistencePort.findAll();
        return fields.stream().map(fieldMapper::toDto).toList();
    }

    /**
     * Recupera un campo a partir de su identificador.
     *
     * @param fieldId identificador del campo a buscar
     * @return {@link FieldDto} con los datos del campo, o {@code null} si no se encuentra
     */
    @Override
    public FieldDto getFieldById(Integer fieldId) {
        Field field = fieldPersistencePort.findById(fieldId);
        return field != null ? fieldMapper.toDto(field) : null;
    }

    /**
     * Crea un nuevo registro de campo.
     *
     * Este método convierte el DTO recibido a la entidad de dominio y delega
     * la operación al puerto de persistencia.
     *
     * @param fieldDto DTO con los datos del campo a crear. Debe contener los
     *                 campos requeridos por la lógica de negocio.
     */
    @Override
    public void createField(FieldDto fieldDto) throws DatasetValidateException {
        Field field = fieldMapper.toDomain(fieldDto);
        validateRules(field);
        LocalDateTime now = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
        field.setCreatedDttm(now);
        field.setUpdatedDttm(null);
        field.setUpdatedBy(null);
        fieldPersistencePort.save(field);
    }

    /**
     * Valida las reglas de negocio para la creación de un Field.
     * @param field
     * @throws DatasetValidateException
     */
    private void validateRules(Field field) throws DatasetValidateException {
        if(!fieldDatatypeUseCase.existsById(field.getFieldDatatypeId())){
            throw new DatasetValidateException("El FieldDatatypeId no existe: " + field.getFieldDatatypeId());
        }
        if (!fieldSemanticDatatypeUseCase.existsById(field.getFieldSemanticDatatypeId())){
            throw new DatasetValidateException("El FieldSemanticDatatypeId no existe: " + field.getFieldSemanticDatatypeId());
        }
        if (!datasetSchemaUseCase.existsById(field.getSchemaId())){
            throw new DatasetValidateException("El DatasetSchemaId no existe: " + field.getSchemaId());
        }
    }

    /**
     * Actualiza un campo existente.
     *
     * Convierte el DTO a entidad y solicita al puerto de persistencia la
     * actualización del registro identificado por {@code fieldId}.
     *
     * @param fieldId  identificador del campo a actualizar
     * @param fieldDto DTO con los valores a actualizar
     */
    @Override
    public void updateField(Integer fieldId, FieldDto fieldDto) throws DatasetValidateException {
        Field field = fieldMapper.toDomain(fieldDto);
        field.setCreatedBy(null);
        if (!existsById(fieldId)) {
            throw new DatasetValidateException("El FieldId no existe: " + fieldId);
        }
        LocalDateTime now = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
        field.setUpdatedDttm(now);
        fieldPersistencePort.update(fieldId, field);
    }

    /**
     * Elimina un campo identificado por {@code fieldId}.
     *
     * @param fieldId identificador del campo a eliminar
     */
    @Override
    public void deleteField(Integer fieldId) {
        fieldPersistencePort.deleteById(fieldId);
    }

    @Override
    public List<FieldDto> findByCriteria(FieldSearchCriteria criteria) {
        List<Field> results = fieldPersistencePort.findByCriteria(criteria);
        return results.stream().map(fieldMapper::toDto).toList();
    }

    /**
     * Verifica si existe un campo con el identificador dado.
     *
     * @param fieldId identificador del campo a verificar
     * @return {@code true} si existe, {@code false} en caso contrario
     */
    public boolean existsById(Integer fieldId) {
        return fieldPersistencePort.existsById(fieldId);
    }
}

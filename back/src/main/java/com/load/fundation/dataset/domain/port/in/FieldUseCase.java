package com.load.fundation.dataset.domain.port.in;

import com.load.fundation.dataset.application.dto.FieldDto;
import com.load.fundation.dataset.application.dto.FieldSearchCriteria;
import com.load.fundation.dataset.exception.DatasetValidateException;

import java.util.List;

/**
 * Puerto de entrada para casos de uso de Field (operaciones CRUD sobre SOURCE_FIELD).
 */
public interface FieldUseCase {

    /**
     * Recupera todos los campos disponibles en la tabla SOURCE_FIELD.
     *
     * @return lista de {@link FieldDto} con todos los registros; lista vacía si no hay registros
     */
    List<FieldDto> getAllFields();

    /**
     * Recupera un campo por su identificador.
     *
     * @param fieldId identificador del campo
     * @return {@link FieldDto} correspondiente si existe; {@code null} si no se encuentra
     */
    FieldDto getFieldById(Integer fieldId);

    /**
     * Crea un nuevo registro de campo en la tabla SOURCE_FIELD.
     *
     * @param fieldDto datos del campo a crear
     *                 (los campos obligatorios deben estar presentes en el DTO)
     */
    void createField(FieldDto fieldDto) throws DatasetValidateException;

    /**
     * Actualiza un registro de campo existente.
     *
     * @param fieldId  identificador del campo a actualizar
     * @param fieldDto datos con los que se actualizará el registro
     */
    void updateField(Integer fieldId, FieldDto fieldDto) throws DatasetValidateException;

    /**
     * Elimina un registro de campo por su identificador.
     *
     * @param fieldId identificador del campo a eliminar
     */
    void deleteField(Integer fieldId);

    /**
     * Busca campos según criterios específicos.
     *
     * @param criteria criterios de búsqueda
     * @return lista de {@link FieldDto} que coinciden con los criterios; lista vacía si no hay coincidencias
     */
    List<FieldDto> findByCriteria(FieldSearchCriteria criteria);
}

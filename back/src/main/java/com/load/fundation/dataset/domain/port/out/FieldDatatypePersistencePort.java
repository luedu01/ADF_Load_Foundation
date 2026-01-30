package com.load.fundation.dataset.domain.port.out;

import com.load.fundation.dataset.domain.model.FieldDatatype;
import com.load.fundation.dataset.application.dto.FieldDatatypeSearchCriteria;

import java.util.List;

/**
 * Puerto de salida para persistencia de FieldDatatype
 */
public interface FieldDatatypePersistencePort {

    /**
     * Obtiene todos los tipos de datos de campo
     * @return Lista de tipos de datos
     */
    List<FieldDatatype> findAll();

    /**
     * Busca un tipo de dato por su ID
     * @param fieldDatatypeId ID del tipo de dato
     * @return FieldDatatype encontrado o null
     */
    FieldDatatype findById(Integer fieldDatatypeId);

    /**
     * Guarda un nuevo tipo de dato
     * @param fieldDatatype Tipo de dato a guardar
     */
    void save(FieldDatatype fieldDatatype);

    /**
     * Actualiza un tipo de dato existente
     * @param fieldDatatypeId ID del tipo de dato
     * @param fieldDatatype Datos actualizados
     */
    void update(Integer fieldDatatypeId, FieldDatatype fieldDatatype);

    /**
     * Elimina un tipo de dato por su ID
     * @param fieldDatatypeId ID del tipo de dato
     */
    void deleteById(Integer fieldDatatypeId);

    /**
     * Verifica si existe un tipo de dato por ID
     * @param fieldDatatypeId ID a verificar
     * @return true si existe, false en caso contrario
     */
    boolean existsById(Integer fieldDatatypeId);

    List<FieldDatatype> findByCriteria(FieldDatatypeSearchCriteria criteria);
}

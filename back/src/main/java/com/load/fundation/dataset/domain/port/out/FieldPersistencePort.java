package com.load.fundation.dataset.domain.port.out;

import com.load.fundation.dataset.domain.model.Field;
import com.load.fundation.dataset.application.dto.FieldSearchCriteria;

import java.util.List;

/**
 * Puerto de salida para persistencia de Field (SOURCE_FIELD)
 */
public interface FieldPersistencePort {

    List<Field> findAll();
    Field findById(Integer fieldId);
    void save(Field field);
    void update(Integer fieldId, Field field);
    void deleteById(Integer fieldId);
    List<Field> findByCriteria(FieldSearchCriteria criteria);
    boolean existsById(Integer fieldId);
}

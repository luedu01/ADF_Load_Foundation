package com.load.fundation.dataset.domain.port.out;

import com.load.fundation.dataset.domain.model.FieldSemanticDatatype;
import com.load.fundation.dataset.application.dto.FieldSemanticDatatypeSearchCriteria;

import java.util.List;

/**
 * Puerto de salida para persistencia de FieldSemanticDatatype
 */
public interface FieldSemanticDatatypePersistencePort {
    List<FieldSemanticDatatype> findAll();
    FieldSemanticDatatype findById(Integer id);
    void save(FieldSemanticDatatype entity);
    void update(Integer id, FieldSemanticDatatype entity);
    void deleteById(Integer id);
    boolean existsById(Integer id);

    List<FieldSemanticDatatype> findByCriteria(FieldSemanticDatatypeSearchCriteria criteria);
}

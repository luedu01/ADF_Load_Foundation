package com.load.fundation.dataset.domain.port.in;

import com.load.fundation.dataset.application.dto.FieldSemanticDatatypeDto;




















import com.load.fundation.dataset.application.dto.FieldSemanticDatatypeSearchCriteria;
import com.load.fundation.dataset.exception.DatasetValidateException;

import java.util.List;

/**
 * Puerto de entrada para casos de uso de FieldSemanticDatatype
 */
public interface FieldSemanticDatatypeUseCase {
    List<FieldSemanticDatatypeDto> getAllFieldSemanticDatatypes();
    FieldSemanticDatatypeDto getFieldSemanticDatatypeById(Integer id);
    void createFieldSemanticDatatype(FieldSemanticDatatypeDto dto);
    void updateFieldSemanticDatatype(Integer id, FieldSemanticDatatypeDto dto) throws DatasetValidateException;
    void deleteFieldSemanticDatatype(Integer id);
    boolean existsById(Integer id);

    List<FieldSemanticDatatypeDto> findByCriteria(FieldSemanticDatatypeSearchCriteria criteria);
}

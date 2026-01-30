package com.load.fundation.dataset.domain.port.in;

import com.load.fundation.dataset.application.dto.FieldDatatypeDto;
import com.load.fundation.dataset.application.dto.FieldDatatypeSearchCriteria;
import com.load.fundation.dataset.exception.DatasetValidateException;

import java.util.List;

/**
 * Puerto de entrada para casos de uso de FieldDatatype
 */
public interface FieldDatatypeUseCase {

    List<FieldDatatypeDto> getAllFieldDatatypes();

    FieldDatatypeDto getFieldDatatypeById(Integer fieldDatatypeId);

    void createFieldDatatype(FieldDatatypeDto fieldDatatypeDto);

    void updateFieldDatatype(Integer fieldDatatypeId, FieldDatatypeDto fieldDatatypeDto) throws DatasetValidateException;

    void deleteFieldDatatype(Integer fieldDatatypeId);

    boolean existsById(Integer fieldDatatypeId);

    List<FieldDatatypeDto> findByCriteria(FieldDatatypeSearchCriteria criteria);
}

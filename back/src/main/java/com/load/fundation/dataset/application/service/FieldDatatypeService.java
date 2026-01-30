package com.load.fundation.dataset.application.service;

import com.load.fundation.dataset.application.dto.FieldDatatypeDto;
import com.load.fundation.dataset.application.dto.FieldDatatypeSearchCriteria;
import com.load.fundation.dataset.application.mapper.FieldDatatypeMapper;
import com.load.fundation.dataset.domain.model.FieldDatatype;
import com.load.fundation.dataset.domain.port.out.FieldDatatypePersistencePort;
import com.load.fundation.dataset.exception.DatasetValidateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FieldDatatypeService implements com.load.fundation.dataset.domain.port.in.FieldDatatypeUseCase {

    private final FieldDatatypePersistencePort fieldDatatypePersistencePort;
    private final FieldDatatypeMapper fieldDatatypeMapper;

    @Override
    public List<FieldDatatypeDto> getAllFieldDatatypes() {
        List<FieldDatatype> fieldDatatypes = fieldDatatypePersistencePort.findAll();
        return fieldDatatypes.stream().map(fieldDatatypeMapper::toDto).toList();
    }

    @Override
    public FieldDatatypeDto getFieldDatatypeById(Integer fieldDatatypeId) {
        FieldDatatype fieldDatatype = fieldDatatypePersistencePort.findById(fieldDatatypeId);
        return fieldDatatype != null ? fieldDatatypeMapper.toDto(fieldDatatype) : null;
    }

    @Override
    public void createFieldDatatype(FieldDatatypeDto fieldDatatypeDto) {
        FieldDatatype fieldDatatype = fieldDatatypeMapper.toDomain(fieldDatatypeDto);
        fieldDatatype.setUpdatedBy(null);
        LocalDateTime now = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
        fieldDatatype.setCreatedDttm(now);
        fieldDatatype.setUpdatedDttm(null);
        fieldDatatypePersistencePort.save(fieldDatatype);
    }

    @Override
    public void updateFieldDatatype(Integer fieldDatatypeId, FieldDatatypeDto fieldDatatypeDto) throws DatasetValidateException {
        FieldDatatype fieldDatatype = fieldDatatypeMapper.toDomain(fieldDatatypeDto);
        fieldDatatype.setCreatedBy(null);
        if (!existsById(fieldDatatypeId)) {
            throw new DatasetValidateException("El FieldDatatype con id " + fieldDatatypeId + " no existe.");
        }
        LocalDateTime now = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
        fieldDatatype.setUpdatedDttm(now);
        fieldDatatypePersistencePort.update(fieldDatatypeId, fieldDatatype);
    }

    @Override
    public void deleteFieldDatatype(Integer fieldDatatypeId) {
        fieldDatatypePersistencePort.deleteById(fieldDatatypeId);
    }

    @Override
    public boolean existsById(Integer fieldDatatypeId) {
        return fieldDatatypePersistencePort.existsById(fieldDatatypeId);
    }

    @Override
    public List<FieldDatatypeDto> findByCriteria(FieldDatatypeSearchCriteria criteria) {
        List<FieldDatatype> results = fieldDatatypePersistencePort.findByCriteria(criteria);
        return results.stream().map(fieldDatatypeMapper::toDto).toList();
    }
}

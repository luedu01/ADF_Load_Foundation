package com.load.fundation.dataset.application.service;

import com.load.fundation.dataset.application.dto.FieldSemanticDatatypeDto;
import com.load.fundation.dataset.application.dto.FieldSemanticDatatypeSearchCriteria;
import com.load.fundation.dataset.application.mapper.FieldSemanticDatatypeMapper;
import com.load.fundation.dataset.domain.model.FieldSemanticDatatype;
import com.load.fundation.dataset.domain.port.in.FieldSemanticDatatypeUseCase;
import com.load.fundation.dataset.domain.port.out.FieldSemanticDatatypePersistencePort;
import com.load.fundation.dataset.exception.DatasetValidateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FieldSemanticDatatypeService implements FieldSemanticDatatypeUseCase {

    private final FieldSemanticDatatypePersistencePort persistencePort;
    private final FieldSemanticDatatypeMapper mapper;

    @Override
    public List<FieldSemanticDatatypeDto> getAllFieldSemanticDatatypes() {
        List<FieldSemanticDatatype> list = persistencePort.findAll();
        return list.stream().map(mapper::toDto).toList();
    }

    @Override
    public FieldSemanticDatatypeDto getFieldSemanticDatatypeById(Integer id) {
        FieldSemanticDatatype ent = persistencePort.findById(id);
        return ent != null ? mapper.toDto(ent) : null;
    }

    @Override
    public void createFieldSemanticDatatype(FieldSemanticDatatypeDto dto) {
        FieldSemanticDatatype ent = mapper.toDomain(dto);
        ent.setUpdatedBy(null);
        LocalDateTime now = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
        ent.setCreatedDttm(now);
        ent.setUpdatedDttm(null);
        persistencePort.save(ent);
    }

    @Override
    public void updateFieldSemanticDatatype(Integer id, FieldSemanticDatatypeDto dto) throws DatasetValidateException {
        FieldSemanticDatatype ent = mapper.toDomain(dto);
        ent.setCreatedBy(null);
        if (!existsById(id)) {
            throw new DatasetValidateException("El FieldSemanticDatatype con id " + id + " no existe.");
        }
        LocalDateTime now = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
        ent.setUpdatedDttm(now);
        persistencePort.update(id, ent);
    }

    @Override
    public void deleteFieldSemanticDatatype(Integer id) {
        persistencePort.deleteById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return persistencePort.existsById(id);
    }

    @Override
    public List<FieldSemanticDatatypeDto> findByCriteria(FieldSemanticDatatypeSearchCriteria criteria) {
        List<FieldSemanticDatatype> results = persistencePort.findByCriteria(criteria);
        return results.stream().map(mapper::toDto).toList();
    }
}

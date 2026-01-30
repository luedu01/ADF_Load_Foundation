package com.load.fundation.connection.application.service;

import com.load.fundation.connection.application.dto.ConnectionSubtypeDto;
import com.load.fundation.connection.application.mapper.ConnectionSubtypeMapper;
import com.load.fundation.connection.domain.model.ConnectionSubtype;
import com.load.fundation.connection.domain.port.in.ConnectionSubtypeUseCase;
import com.load.fundation.connection.domain.port.out.ConnectionSubtypePersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConnectionSubtypeService implements ConnectionSubtypeUseCase {

    private final ConnectionSubtypePersistencePort persistencePort;
    private final ConnectionSubtypeMapper mapper;

    @Override
    public List<ConnectionSubtypeDto> getAll() {
        return persistencePort.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public List<ConnectionSubtypeDto> getByTypeId(Integer typeId) {
        return persistencePort.findByTypeId(typeId).stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public ConnectionSubtypeDto getById(Integer id) {
        ConnectionSubtype subtype = persistencePort.findById(id);
        if (subtype == null) {
            return null; // o excepción
        }
        return mapper.toDto(subtype);
    }



    @Override
    public void create(ConnectionSubtypeDto dto) {
        ConnectionSubtype subtype = mapper.toEntity(dto);
        LocalDateTime now = LocalDateTime.now();
        subtype.setCreatedDttm(now);
        subtype.setUpdatedDttm(now);
        persistencePort.save(subtype);
    }

    @Override
    public void update(Integer id, ConnectionSubtypeDto dto) {
        ConnectionSubtype subtype = mapper.toEntity(dto);
        subtype.setId(id);
        subtype.setUpdatedDttm(LocalDateTime.now());
        persistencePort.update(id, subtype);
    }

    @Override
    public void delete(Integer id) {
        persistencePort.deleteById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return persistencePort.existsById(id);
    }
}
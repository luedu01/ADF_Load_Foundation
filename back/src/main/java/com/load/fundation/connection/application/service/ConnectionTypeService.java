package com.load.fundation.connection.application.service;


import com.load.fundation.connection.application.dto.ConnectionTypeDto;
import com.load.fundation.connection.application.mapper.ConnectionTypeMapper;
import com.load.fundation.connection.domain.model.ConnectionType;
import com.load.fundation.connection.domain.port.in.ConnectionTypeUseCase;
import com.load.fundation.connection.domain.port.out.ConnectionTypePersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConnectionTypeService implements ConnectionTypeUseCase {

    private final ConnectionTypePersistencePort persistencePort;
    private final ConnectionTypeMapper mapper;

    @Override
    public List<ConnectionTypeDto> getAll() {
        return persistencePort.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public ConnectionTypeDto getById(Integer id) {
        ConnectionType type = persistencePort.findById(id);
        if (type == null) {
            return null; // EXCEPCION (?)
        }
        return mapper.toDto(type);
    }

    @Override
    public void create(ConnectionTypeDto dto) {
        ConnectionType type = mapper.toDomain(dto);
        LocalDateTime now = LocalDateTime.now();
        type.setCreatedDttm(now);
        type.setUpdatedDttm(now);
        persistencePort.save(type);
    }

    @Override
    public void update(Integer id, ConnectionTypeDto dto) {
        ConnectionType type = mapper.toDomain(dto);
        type.setId(id);
        type.setUpdatedDttm(LocalDateTime.now());
        persistencePort.update(id, type);
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
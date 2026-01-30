package com.load.fundation.connection.application.service;

import com.load.fundation.connection.application.dto.ConnectionDto;
import com.load.fundation.connection.application.mapper.ConnectionMapper;
import com.load.fundation.connection.domain.model.Connection;
import com.load.fundation.connection.domain.port.in.ConnectionUseCase;
import com.load.fundation.connection.domain.port.out.ConnectionPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConnectionService  implements ConnectionUseCase{

    private final ConnectionPersistencePort persistencePort;
    private final ConnectionMapper mapper;


    @Override
    public List<ConnectionDto> getAllConnections() {
        List <Connection> connections = persistencePort.findAll();
        return mapper.toDtoList(connections);
    }

    @Override
    public ConnectionDto getConnectionById(Integer connectionId) {
        Connection connection = persistencePort.findById(connectionId);
        return mapper.toDto(connection);
    }

    @Override
    public List<ConnectionDto> getConnectionsByGroup(Integer groupId) {
        List<Connection> connections = persistencePort.findByGroupId(groupId);
        return mapper.toDtoList(connections);
    }

    @Override
    public void createConnection(ConnectionDto dto) {
        Connection connection = mapper.toEntity(dto);
        persistencePort.save(connection);
    }

    @Override
    public void updateConnection(Integer connectionId, ConnectionDto dto) {
        Connection connection = mapper.toEntity(dto);
        persistencePort.update(connectionId, connection);
    }
    
}

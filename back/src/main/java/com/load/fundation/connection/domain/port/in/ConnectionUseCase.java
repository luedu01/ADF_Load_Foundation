package com.load.fundation.connection.domain.port.in;
import com.load.fundation.connection.application.dto.ConnectionDto;

import java.util.List;

public interface ConnectionUseCase {


    List<ConnectionDto> getAllConnections();

    ConnectionDto getConnectionById(Integer connectionId);

    List<ConnectionDto> getConnectionsByGroup(Integer groupId);

    void createConnection(ConnectionDto connectionDto);

    void updateConnection(Integer connectionId, ConnectionDto connectionDto);
}

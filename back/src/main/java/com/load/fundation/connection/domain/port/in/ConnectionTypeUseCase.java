package com.load.fundation.connection.domain.port.in;
import com.load.fundation.connection.application.dto.ConnectionTypeDto;
import java.util.List;

public interface ConnectionTypeUseCase {

    List<ConnectionTypeDto> getAll();

    ConnectionTypeDto getById(Integer id);

    void create(ConnectionTypeDto dto);

    void update(Integer id, ConnectionTypeDto dto);

    void delete(Integer id);

    boolean existsById(Integer id);
}
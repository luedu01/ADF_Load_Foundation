package com.load.fundation.connection.domain.port.in;

import com.load.fundation.connection.application.dto.ConnectionSubtypeDto;

import java.util.List;

public interface ConnectionSubtypeUseCase {

    List<ConnectionSubtypeDto> getAll();

    List<ConnectionSubtypeDto> getByTypeId(Integer typeId);

    ConnectionSubtypeDto getById(Integer id);

    void create(ConnectionSubtypeDto dto);

    void update(Integer id, ConnectionSubtypeDto dto);

    void delete(Integer id);

    boolean existsById(Integer id);

}
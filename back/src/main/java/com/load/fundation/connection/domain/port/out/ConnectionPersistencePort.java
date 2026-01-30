package com.load.fundation.connection.domain.port.out;
import com.load.fundation.connection.domain.model.Connection;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConnectionPersistencePort {

    List<Connection> findAll();

    Connection findById(Integer connectionId);

    List<Connection> findByGroupId(Integer groupId);

    void save(Connection connection);

    void update(Integer connectionId, Connection connection);
}
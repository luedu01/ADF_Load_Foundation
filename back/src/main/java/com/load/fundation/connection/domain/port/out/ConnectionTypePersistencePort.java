package com.load.fundation.connection.domain.port.out;
import com.load.fundation.connection.domain.model.ConnectionType;

import java.util.List;

public interface ConnectionTypePersistencePort {

        List<ConnectionType> findAll();

        ConnectionType findById(Integer id);

        void save(ConnectionType type);

        void update(Integer id, ConnectionType type);

        void deleteById(Integer id);

        boolean existsById(Integer id);
}


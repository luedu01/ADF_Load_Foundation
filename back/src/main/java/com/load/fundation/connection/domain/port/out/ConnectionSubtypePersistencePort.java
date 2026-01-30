package com.load.fundation.connection.domain.port.out;

import com.load.fundation.connection.domain.model.ConnectionSubtype;
import java.util.List;

public interface ConnectionSubtypePersistencePort {

    List<ConnectionSubtype> findAll();

    List<ConnectionSubtype> findByTypeId(Integer typeId);

    ConnectionSubtype findById(Integer id);

    void save(ConnectionSubtype subtype);

    void update(Integer id, ConnectionSubtype subtype);

    void deleteById(Integer id);

    boolean existsById(Integer id);
}
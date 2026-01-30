package com.load.fundation.connection.application.mapper;


import com.load.fundation.connection.application.dto.ConnectionTypeDto;
import com.load.fundation.connection.domain.model.ConnectionType;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ConnectionTypeMapper {

    ConnectionTypeDto toDto(ConnectionType type);

    ConnectionType toDomain(ConnectionTypeDto dto);
}
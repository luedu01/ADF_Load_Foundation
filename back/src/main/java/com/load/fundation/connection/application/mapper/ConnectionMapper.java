package com.load.fundation.connection.application.mapper;

import com.load.fundation.connection.application.dto.ConnectionDto;
import com.load.fundation.connection.application.dto.ConnectionSubtypeDto;
import com.load.fundation.connection.application.dto.ConnectionTypeDto;
import com.load.fundation.connection.domain.model.Connection;
import com.load.fundation.connection.domain.model.ConnectionSubtype;
import com.load.fundation.connection.domain.model.ConnectionType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ConnectionMapper {

    @Mapping(source = "type.id",   target = "typeId")
    @Mapping(source = "type.name", target = "typeName")

    @Mapping(source = "subtype.id",   target = "subtypeId")
    @Mapping(source = "subtype.name", target = "subtypeName")

    @Mapping(source = "additionalInfo", target = "additionalInfo")

    @Mapping(source = "credential.userName", target = "credentialUserName")
    @Mapping(expression = "java(connection.getCredential() != null)", target = "hasCredential")

    @Mapping(source = "token.tokenName", target = "tokenName")
    @Mapping(source = "token.active",    target = "tokenActive")
    ConnectionDto toDto(Connection connection);

    Connection toEntity(ConnectionDto dto);

    List<ConnectionDto> toDtoList(List<Connection> connections);

}

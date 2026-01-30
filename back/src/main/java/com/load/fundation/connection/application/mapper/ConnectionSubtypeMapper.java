package com.load.fundation.connection.application.mapper;


import com.load.fundation.connection.application.dto.ConnectionSubtypeDto;
import com.load.fundation.connection.domain.model.ConnectionSubtype;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface ConnectionSubtypeMapper {


        // Dominio / DTO
        @Mapping(source = "typeId", target = "typeId")
        ConnectionSubtypeDto toDto(ConnectionSubtype subtype);

        // List<ConnectionSubtypeDto> toDtoList(List<ConnectionSubtype> subtypes);

        // DTO / Dominio
        @Mapping(source = "typeId", target = "typeId")
        ConnectionSubtype toEntity(ConnectionSubtypeDto dto);

        //List<ConnectionSubtype> toEntityList(List<ConnectionSubtypeDto> dtos);

}

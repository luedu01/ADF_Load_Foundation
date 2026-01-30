package com.load.fundation.user.application.mapper;

import com.load.fundation.user.application.dto.UserDto;
import com.load.fundation.user.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

/**
 * Mapper de MapStruct para conversión entre User (entidad de dominio) y UserDto
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    /**
     * Convierte una entidad User a UserDto
     * @param user Entidad de dominio
     * @return DTO
     */
    UserDto toDto(User user);

    /**
     * Convierte un UserDto a entidad User
     * @param userDto DTO
     * @return Entidad de dominio
     */
    User toDomain(UserDto userDto);
}

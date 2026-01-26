package com.load.fundation.user.domain.port.in;

import com.load.fundation.user.application.dto.UserDto;

import java.util.List;

public interface UserUseCase {
    List<UserDto> getUsers();
    UserDto getUserById(Integer userId);
    void createUser(UserDto userDto);
    void updateUser(Integer userId, UserDto userDto);
    void deleteUser(Integer userId);
}

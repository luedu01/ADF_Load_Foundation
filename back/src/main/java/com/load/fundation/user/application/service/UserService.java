package com.load.fundation.user.application.service;

import com.load.fundation.user.application.dto.UserDto;
import com.load.fundation.user.application.mapper.UserMapper;
import com.load.fundation.user.domain.model.User;
import com.load.fundation.user.domain.port.in.UserUseCase;
import com.load.fundation.user.domain.port.out.UserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserUseCase {

    private final UserPersistencePort userPersistencePort;
    private final UserMapper userMapper;

    @Override
    public List<UserDto> getUsers() {
        return userPersistencePort.findAll().stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = userPersistencePort.findById(userId);
        return user != null ? userMapper.toDto(user) : null;
    }

    @Override
    public void createUser(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        userPersistencePort.save(user);
    }

    @Override
    public void updateUser(Integer userId, UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        userPersistencePort.update(userId, user);
    }

    @Override
    public void deleteUser(Integer userId) {
        userPersistencePort.deleteById(userId);
    }
}

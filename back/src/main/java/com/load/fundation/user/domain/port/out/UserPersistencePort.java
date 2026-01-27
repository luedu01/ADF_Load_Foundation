package com.load.fundation.user.domain.port.out;

import com.load.fundation.user.domain.model.User;

import java.util.List;

public interface UserPersistencePort {
    List<User> findAll();
    User findById(Integer userId);
    void save(User user);
    void update(Integer userId, User user);
    void deleteById(Integer userId);
}

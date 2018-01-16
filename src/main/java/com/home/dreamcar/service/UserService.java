package com.home.dreamcar.service;

import com.home.dreamcar.model.User;

public interface UserService {

    User findUserByEmail(String email);

    User saveUser(User user, boolean active);

    User saveAdminUser(User user);

    User updateUser(User user);

    User findUserById(Long id);

    Iterable<User> findAll();
}

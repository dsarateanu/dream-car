package com.home.dreamcar.service;

import com.home.dreamcar.model.User;

public interface UserService {

    User findUserByEmail(String email);

    User saveUser(User user);

    User saveAdminUser(User user);

    User approveUser(User user);
}

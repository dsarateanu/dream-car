package com.home.dreamcar.service;

import com.home.dreamcar.model.User;

public interface UserService {
    public User findUserByEmail(String email);
    public User saveUser(User user);
    public User saveAdminUser(User user);
    public User approveUser(User user);
}

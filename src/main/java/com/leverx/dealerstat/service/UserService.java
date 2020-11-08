package com.leverx.dealerstat.service;

import com.leverx.dealerstat.entity.User;

import java.util.Optional;

public interface UserService {

    User saveUser(User user);

    void deleteUser(Long id);

    void updateUser(User user);

    Iterable<User> getUsers();

    User getUserById(Long id);

    String getHashByUserId(Long id);

    User getUserByEmail(String email);
}

package com.leverx.dealerstat.service;

import com.leverx.dealerstat.entity.User;

import java.util.Optional;

public interface UserService {

    boolean saveUser(User user);

    void deleteUser(Long id);

    void updateUser(User user);

    Iterable<User> getUsers();

    Optional<User> getUserById(Long id);

    User auth(String email, String password);

    String getHashByUserId(Long id);
}

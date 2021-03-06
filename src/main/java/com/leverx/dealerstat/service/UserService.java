package com.leverx.dealerstat.service;

import com.leverx.dealerstat.dto.AuthenticationRequestDTO;
import com.leverx.dealerstat.entity.User;

public interface UserService {

    User saveUser(User user);

    void updateUser(User user);

    Iterable<User> getUsers();

    User getUserById(Long id);

    User getUserByEmail(String email);

    User setNewPassword(AuthenticationRequestDTO authenticationRequestDTO);
}

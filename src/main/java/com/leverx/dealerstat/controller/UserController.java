package com.leverx.dealerstat.controller;

import com.leverx.dealerstat.dto.UserDTO;
import com.leverx.dealerstat.entity.User;
import com.leverx.dealerstat.service.UserService;
import com.leverx.dealerstat.serviceimpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/registration")
    public ResponseEntity<UserDTO> saveUser(@Valid @RequestBody User user) {
        User savedUser = userService.saveUser(user);

        if (savedUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        UserDTO result = UserDTO.fromUser(user);
        return  new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/user/users")
    public Iterable<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable(name = "id") Long id) {
        User user = userService.getUserById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        UserDTO result = UserDTO.fromUser(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping(value = "/user/deleteUser/{id}")
    public void deleteUser(@PathVariable(name = "id") Long id) {
        userService.deleteUser(id);
    }


}

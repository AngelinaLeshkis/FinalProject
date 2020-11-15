package com.leverx.dealerstat.controller;

import com.leverx.dealerstat.dto.UserDTO;
import com.leverx.dealerstat.entity.User;
import com.leverx.dealerstat.service.UserService;
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

    @PostMapping(value = "/registration/user")
    public ResponseEntity<UserDTO> saveUser(@Valid @RequestBody User user) {
        User savedUser = userService.saveUser(user);

        if (savedUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        UserDTO result = UserDTO.fromUser(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
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


}

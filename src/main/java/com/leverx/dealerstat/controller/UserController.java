package com.leverx.dealerstat.controller;

import com.leverx.dealerstat.dto.UserDTO;
import com.leverx.dealerstat.entity.User;
import com.leverx.dealerstat.serviceimpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/registration")
    public User saveUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        if (savedUser != null) {
            return savedUser;
        }
        return null;
    }

    @GetMapping(value = "/user/users")
    public Iterable<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable long id) {
        User user = userService.getUserById(id);

        if(user == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        UserDTO result = UserDTO.fromUser(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/user/userHash/{id}")
    public String getHashByUserId(@PathVariable long id) {
        return userService.getHashByUserId(id);
    }

    @DeleteMapping(value = "/user/deleteUser/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }


}

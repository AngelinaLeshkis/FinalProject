package com.leverx.dealerstat.controller;

import com.leverx.dealerstat.entity.User;
import com.leverx.dealerstat.service.UserService;
import com.leverx.dealerstat.serviceimpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/registration")
    public String saveUser(@RequestBody User user) {
        userService.saveUser(user);
        return "user";
    }

    @GetMapping(value = "")
    public Iterable<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id) {
        Optional<User> user = userService.getUserById(id);
        return user.isPresent() ? ResponseEntity.ok(user.get()) : ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/userHash/{id}")
    public String getHashByUserId(@PathVariable long id) {
        return userService.getHashByUserId(id);
    }

    @DeleteMapping(value = "/deleteUser/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }


}

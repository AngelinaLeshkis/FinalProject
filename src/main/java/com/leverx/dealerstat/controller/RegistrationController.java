package com.leverx.dealerstat.controller;

import com.leverx.dealerstat.serviceimpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    private UserServiceImpl userService;

    @Autowired
    public RegistrationController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/activate/{code}/{id}")
    public String activate(@PathVariable String code, @PathVariable Long id) {
        boolean isActivated = userService.activateUser(code, id);
        if (isActivated) {
            return  "User successfully activated";
        } else {
            return  "Activation code is not found!";
        }
    }
}

package com.leverx.dealerstat.controller;

import com.leverx.dealerstat.dto.AuthenticationRequestDTO;
import com.leverx.dealerstat.dto.ForgotPasswordRequestDTO;
import com.leverx.dealerstat.entity.User;
import com.leverx.dealerstat.serviceimpl.ActivationUserAccountServiceImpl;
import com.leverx.dealerstat.serviceimpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ForgotPasswordController {

    private UserServiceImpl userService;
    private ActivationUserAccountServiceImpl activationUserAccountService;

    @Autowired
    public ForgotPasswordController(UserServiceImpl userService,
                                    ActivationUserAccountServiceImpl activationUserAccountService) {
        this.userService = userService;
        this.activationUserAccountService = activationUserAccountService;
    }

    @PostMapping(value = "/auth/forgotPassword")
    public String sendEmailWithTokenToResetPassword(@RequestBody ForgotPasswordRequestDTO forgotPasswordRequestDTO) {
        User savedUser = userService.getUserByEmail(forgotPasswordRequestDTO.getEmail());

        if (savedUser == null) {
            return "Such user does not exist";
        }

        activationUserAccountService.createEmailWithTokenToResetPassword(forgotPasswordRequestDTO.getEmail());

        return "Ok";
    }

    @PostMapping(value = "/auth/reset")
    public String resetPassword(@RequestBody AuthenticationRequestDTO authenticationRequestDTO) {
        User userFromDB = userService.getUserByEmail(authenticationRequestDTO.getEmail());

        if (userFromDB.isEnabled()) {
           User savedUser = userService.setNewPassword(authenticationRequestDTO);
        }

        return "Ok";
    }

    @GetMapping("/auth/checkCode/{code}")
    public String activate(@PathVariable String code) {
        boolean isActivated = activationUserAccountService.activateUser(code);
        if (isActivated) {
            return  "User successfully activated";
        } else {
            return  "Activation code is not found!";
        }
    }
}

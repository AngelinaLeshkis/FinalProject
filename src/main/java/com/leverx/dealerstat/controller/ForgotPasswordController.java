package com.leverx.dealerstat.controller;

import com.leverx.dealerstat.dto.AuthenticationRequestDTO;
import com.leverx.dealerstat.dto.ForgotPasswordRequestDTO;
import com.leverx.dealerstat.dto.UserDTO;
import com.leverx.dealerstat.entity.User;
import com.leverx.dealerstat.service.ActivationUserAccountService;
import com.leverx.dealerstat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/auth")
public class ForgotPasswordController {

    private UserService userService;
    private ActivationUserAccountService activationUserAccountService;

    @Autowired
    public ForgotPasswordController(UserService userService,
                                    ActivationUserAccountService activationUserAccountService) {
        this.userService = userService;
        this.activationUserAccountService = activationUserAccountService;
    }

    @PostMapping(value = "/forgotPassword")
    public String sendEmailWithTokenToResetPassword(@Valid @RequestBody
                                                            ForgotPasswordRequestDTO forgotPasswordRequestDTO) {
        User savedUser = userService.getUserByEmail(forgotPasswordRequestDTO.getEmail());

        if (savedUser == null) {
            return "Such user does not exist";
        }

        activationUserAccountService.createEmailWithTokenToResetPassword(forgotPasswordRequestDTO.getEmail());

        return "Ok";
    }

    @PostMapping(value = "/reset")
    public ResponseEntity<UserDTO> resetPassword(@Valid @RequestBody
                                                         AuthenticationRequestDTO authenticationRequestDTO) {
        User userFromDB = userService.getUserByEmail(authenticationRequestDTO.getEmail());

        if (userFromDB.isEnabled()) {
            User savedUser = userService.setNewPassword(authenticationRequestDTO);
            UserDTO savedUserDTO = UserDTO.fromUser(savedUser);
            return new ResponseEntity<>(savedUserDTO, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @GetMapping("/checkCode/{code}")
    public String activate(@PathVariable(name = "code") String code) {
        boolean isActivated = activationUserAccountService.activateCode(code);
        if (isActivated) {
            return "Password can be changed";
        } else {
            return "Activation code is not found!";
        }
    }
}

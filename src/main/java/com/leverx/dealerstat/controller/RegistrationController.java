package com.leverx.dealerstat.controller;

import com.leverx.dealerstat.pojo.VerificationToken;
import com.leverx.dealerstat.serviceimpl.ActivationUserAccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    private ActivationUserAccountServiceImpl activationUserAccountService;

    @Autowired
    public RegistrationController(ActivationUserAccountServiceImpl activationUserAccountService) {
        this.activationUserAccountService = activationUserAccountService;
    }

    @GetMapping("/activate/{code}")
    public String activate(@PathVariable String code) {
        boolean isActivated = activationUserAccountService.activateUser(code);
        if (isActivated) {
            return  "User successfully activated";
        } else {
            return  "Activation code is not found!";
        }
    }

}

package com.leverx.dealerstat.controller;

import com.leverx.dealerstat.service.ActivationUserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    private ActivationUserAccountService activationUserAccountService;

    @Autowired
    public RegistrationController(ActivationUserAccountService activationUserAccountService) {
        this.activationUserAccountService = activationUserAccountService;
    }

    @GetMapping("/registration/activate/{code}")
    public String activate(@PathVariable(name = "code") String code) {
        boolean isActivated = activationUserAccountService.activateCode(code);
        if (isActivated) {
            return  "User successfully activated";
        } else {
            return  "Activation code is not found!";
        }
    }

}

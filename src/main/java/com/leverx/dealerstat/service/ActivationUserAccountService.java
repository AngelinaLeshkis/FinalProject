package com.leverx.dealerstat.service;

import com.leverx.dealerstat.entity.User;

public interface ActivationUserAccountService {

    void sendEmailToConfirmRegistration(String token, User user);

    String createVerificationTokenForUser(User user);

    boolean activateCode(String token);

    void sendEmailToConfirmPasswordReset(String token, User user);

    User createEmailWithTokenToResetPassword(String email);
}

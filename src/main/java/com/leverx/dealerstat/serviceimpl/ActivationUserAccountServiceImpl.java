package com.leverx.dealerstat.serviceimpl;

import com.leverx.dealerstat.SendEmailService;
import com.leverx.dealerstat.dto.AuthenticationRequestDTO;
import com.leverx.dealerstat.entity.User;
import com.leverx.dealerstat.pojo.VerificationToken;
import com.leverx.dealerstat.persistence.UserRepository;
import com.leverx.dealerstat.service.ActivationUserAccountService;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.UUID;

@Service
public class ActivationUserAccountServiceImpl implements ActivationUserAccountService {

    private RedisTemplate<Long, String> redisTemplate;
    private HashOperations hashOperations;
    private SendEmailService mailSender;
    private UserRepository userRepo;

    private static final Long expiredTime = 86400000L;

    public ActivationUserAccountServiceImpl(RedisTemplate<Long, String> redisTemplate, SendEmailService mailSender,
                                            UserRepository userRepo) {
        this.redisTemplate = redisTemplate;
        this.hashOperations =redisTemplate.opsForHash();
        this.mailSender = mailSender;
        this.userRepo = userRepo;
    }

    @Override
    public void sendEmailToConfirmRegistration(String token, User user) {
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Hello, %s %s! \n" +
                            "Welcome to DealerStat. Please, visit the next link to activate your account: " +
                            "http://localhost:8080/activate/%s",
                    user.getFirstName(),
                    user.getLastName(),
                    token

            );
            mailSender.sendEmail(user.getEmail(), message, "Activation code");
        }
    }

    @Override
    public String createVerificationTokenForUser(User user) {
        String activationCode = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken(user.getId(), new Date());
        hashOperations.put("USER", activationCode, verificationToken);
        return activationCode;
    }

    @Override
    public boolean activateUser(String token) {
        Date date = new Date();
        VerificationToken verificationToken = getVerificationTokenFromRedis(token);
        if ((date.getTime() - verificationToken.getTimeOfCreation().getTime()) > expiredTime) {
            return false;
        }

        User user = userRepo.findById(verificationToken.getId()).orElse(null);

        if (user == null) {
            return false;
        }

        user.setEnabled(true);
        userRepo.save(user);

        return true;
    }

    @Override
    public void sendEmailToConfirmPasswordReset(String token, User user) {
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Hello, %s %s! \n" +
                            "Please, visit the next link to confirm the password reset: " +
                            "http://localhost:8080/auth/checkCode/%s",
                    user.getFirstName(),
                    user.getLastName(),
                    token

            );
            mailSender.sendEmail(user.getEmail(), message, "Activation code");
        }
    }

    @Override
    public User createEmailWithTokenToResetPassword(String email) {
        User userFromDB = userRepo.findUserByEmail(email);

        if (userFromDB == null) {
            return null;
        }

        String token = createVerificationTokenForUser(userFromDB);
        sendEmailToConfirmPasswordReset(token, userFromDB);


        return userFromDB;
    }


    public VerificationToken getVerificationTokenFromRedis(String token) {
        return (VerificationToken) hashOperations.get("USER", token);
    }

}

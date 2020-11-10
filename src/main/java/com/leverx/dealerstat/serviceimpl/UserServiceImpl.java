package com.leverx.dealerstat.serviceimpl;

import com.leverx.dealerstat.SendEmailService;
import com.leverx.dealerstat.entity.Role;
import com.leverx.dealerstat.entity.User;
import com.leverx.dealerstat.persistence.UserRepository;
import com.leverx.dealerstat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepo;
    private RedisTemplate<Long, String> redisTemplate;
    private HashOperations hashOperations;
    private BCryptPasswordEncoder passwordEncoder;
    private SendEmailService mailSender;

    @Autowired
    public UserServiceImpl(UserRepository userRepo, RedisTemplate<Long, String> redisTemplate,
                           BCryptPasswordEncoder passwordEncoder, SendEmailService sendEmailService) {
        this.userRepo = userRepo;
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
        this.passwordEncoder = passwordEncoder;
        this.mailSender = sendEmailService;
    }

    @Override
    public User saveUser(User user) {
        User userFromDB = userRepo.findUserByEmail(user.getEmail());

        if (userFromDB != null) {
            return null;
        }

        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User registeredUser = userRepo.save(user);
        String activationCode = UUID.randomUUID().toString();
        hashOperations.put("USER", user.getId(), activationCode);

        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Hello, %s %s! \n" +
                            "Welcome to DealerStat. Please, visit next link to activate your account: http://localhost:8080/activate/%s/%d",
                    user.getFirstName(),
                    user.getLastName(),
                    hashOperations.get("USER", user.getId()),
                    user.getId()

            );
            mailSender.sendEmail(user.getEmail(), message, "Activation code");
        }

        return registeredUser;
    }

    @Override
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public void updateUser(User user) {
        userRepo.save(user);
    }

    @Override
    public Iterable<User> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUserById(Long id) {
        User result = userRepo.findById(id).orElse(null);
        return result;
    }

    @Override
    public String getHashByUserId(Long id) {
        return (String) hashOperations.get("USER", id);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepo.findUserByEmail(email);
    }

    @Override
    public boolean activateUser(String code, Long id) {
        User user = userRepo.findById(id).orElse(null);

        if (user == null) {
            return false;
        }

        user.setEnabled(true);
        code = null;
        userRepo.save(user);

        return true;
    }
}

package com.leverx.dealerstat.serviceimpl;

import com.leverx.dealerstat.dto.AuthenticationRequestDTO;
import com.leverx.dealerstat.entity.Role;
import com.leverx.dealerstat.entity.User;
import com.leverx.dealerstat.persistence.UserRepository;
import com.leverx.dealerstat.service.ActivationUserAccountService;
import com.leverx.dealerstat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;
    private ActivationUserAccountService activationUserAccountService;

    @Autowired
    public UserServiceImpl(UserRepository userRepo, PasswordEncoder passwordEncoder,
                           ActivationUserAccountService activationUserAccountService) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.activationUserAccountService = activationUserAccountService;
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
        String token = activationUserAccountService.createVerificationTokenForUser(registeredUser);
        activationUserAccountService.sendEmailToConfirmRegistration(token, registeredUser);

        return registeredUser;
    }

    @Override
    public User setNewPassword(AuthenticationRequestDTO authenticationRequestDTO) {
        User userFromDB = userRepo.findUserByEmail(authenticationRequestDTO.getEmail());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userFromDB.setPassword(passwordEncoder.encode(authenticationRequestDTO.getPassword()));
        userRepo.save(userFromDB);
        return userFromDB;
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
        return userRepo.findById(id).orElseThrow(() ->
                new RuntimeException("User not found with id = " + id));
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepo.findUserByEmail(email);
    }

}

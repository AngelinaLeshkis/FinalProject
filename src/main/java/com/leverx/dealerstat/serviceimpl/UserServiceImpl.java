package com.leverx.dealerstat.serviceimpl;

import com.leverx.dealerstat.dto.AuthenticationRequestDTO;
import com.leverx.dealerstat.entity.Role;
import com.leverx.dealerstat.entity.User;
import com.leverx.dealerstat.persistence.UserRepository;
import com.leverx.dealerstat.pojo.VerificationToken;
import com.leverx.dealerstat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepo;
    private BCryptPasswordEncoder passwordEncoder;
    private ActivationUserAccountServiceImpl activationUserAccountService;

    @Autowired
    public UserServiceImpl(UserRepository userRepo, BCryptPasswordEncoder passwordEncoder,
                           ActivationUserAccountServiceImpl activationUserAccountService) {
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
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepo.findUserByEmail(email);
    }

}

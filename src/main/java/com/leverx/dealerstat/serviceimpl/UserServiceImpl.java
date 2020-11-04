package com.leverx.dealerstat.serviceimpl;

import com.leverx.dealerstat.entity.User;
import com.leverx.dealerstat.persistence.UserRepository;
import com.leverx.dealerstat.service.UserService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepo;

    @Autowired
    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User saveUser(User user) {
        user.setEnabled(false);
        String randomCode = RandomString.make(10);
        return userRepo.save(user);
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
    public Optional<User> getUserById(Long id) {
        return userRepo.findById(id);
    }

    @Override
    public User auth(String email, String password) {
        return null;
    }
}

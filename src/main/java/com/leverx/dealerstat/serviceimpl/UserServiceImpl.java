package com.leverx.dealerstat.serviceimpl;

import com.leverx.dealerstat.entity.User;
import com.leverx.dealerstat.persistence.UserRepository;
import com.leverx.dealerstat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepo;
    private RedisTemplate<Long, String> redisTemplate;
    private HashOperations hashOperations;


    @Autowired
    public UserServiceImpl(UserRepository userRepo, RedisTemplate<Long, String> redisTemplate) {
        this.userRepo = userRepo;
        this.redisTemplate = redisTemplate;
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public boolean saveUser(User user) {
        User userFromDB = userRepo.findUserByEmail(user.getEmail());

        if (userFromDB != null) {
//            return "Such user have already existed!";
            return false;
        }
        userRepo.save(user);
        String randomCode = UUID.randomUUID().toString();
        hashOperations.put("USER", user.getId(), randomCode);
//        return "Registration completed successfully";
        return true;
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
    public String getHashByUserId(Long id) {
        return (String) hashOperations.get("USER", id);
    }

    @Override
    public User auth(String email, String password) {
        return null;
    }
}

package com.leverx.dealerstat.persistence;

import com.leverx.dealerstat.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByEmail(String email);
}

package com.tarun.springsecurity.Repository;

import com.tarun.springsecurity.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}

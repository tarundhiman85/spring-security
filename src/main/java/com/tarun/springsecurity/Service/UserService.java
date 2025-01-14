package com.tarun.springsecurity.Service;

import com.tarun.springsecurity.Models.User;
import com.tarun.springsecurity.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    public User saveUser(User user)
    {
        return userRepo.save(user);
    }
}

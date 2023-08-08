package com.bank.backend.service;


import com.bank.backend.data.UserRepository;
import com.bank.backend.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {
    @Autowired
    private UserRepository userRepository;

    public Users save(Users users){
        return userRepository.save(users);
    }


    public Users getUsersByUserId(String id) {
        return userRepository.getUsersByUserId(id);


    }

    public List<Users> getAllUsers(){
        return userRepository.findAll();
    }
}

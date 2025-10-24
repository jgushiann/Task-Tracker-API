package com.example.TaskTrackerAPI.service;

import com.example.TaskTrackerAPI.model.User;
import com.example.TaskTrackerAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUserById(int id) throws Exception{
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User getUserByUsername(String username) throws Exception{
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User getUserByEmail(String email) throws Exception{
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void deleteUserById(int id) throws Exception{
        if(userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }else{
            throw new RuntimeException("User does not exist");
        }
    }

    public void deleteByUsername(String username) throws Exception{
        if(userRepository.existsByUsername(username)) {
            userRepository.deleteByUsername(username);
        }else{
            throw new RuntimeException("User does not exist");
        }
    }

    public void deleteByEmail(String email) throws Exception{
        if(userRepository.existsByEmail(email)) {
            userRepository.deleteByEmail(email);
        }else{
            throw new RuntimeException("User does not exist");
        }
    }
}

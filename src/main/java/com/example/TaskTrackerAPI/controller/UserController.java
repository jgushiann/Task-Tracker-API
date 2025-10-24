package com.example.TaskTrackerAPI.controller;

import com.example.TaskTrackerAPI.service.UserService;
import com.example.TaskTrackerAPI.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/api/user/{id}")
    public User findById(@PathVariable int id) throws Exception {
        return userService.getUserById(id);
    }

    @GetMapping("api/user/{email}")
    public User findByEmail(@PathVariable String email) throws Exception {
        return userService.getUserByEmail(email);
    }

    @GetMapping("api/user/{username}")
    public User findByUsername(@PathVariable String username) throws Exception {
        return userService.getUserByUsername(username);
    }


}

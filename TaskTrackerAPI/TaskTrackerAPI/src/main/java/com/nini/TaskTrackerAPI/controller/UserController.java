package com.nini.TaskTrackerAPI.controller;

import com.nini.TaskTrackerAPI.model.Task;
import com.nini.TaskTrackerAPI.model.User;
import com.nini.TaskTrackerAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getUsers(@RequestParam(required = false) String firstname,
                               @RequestParam(required = false) String lastname,
                               @RequestParam(required = false) String username,
                               @RequestParam(required = false) String email,
                               @RequestParam(required = false) Long id) throws Exception
    {
        return userService.searchUser(firstname, lastname, username, email, id);
    }

    @PostMapping
    public void  addUser(@RequestBody User user) throws Exception {
        userService.createUser(user);
    }

    @GetMapping("/{user_id}")
    public User getUser(@PathVariable Long user_id) throws Exception {
        return userService.searchUserByUserId(user_id);
    }

    @PutMapping("/{user_id}")
    public void updateUser(@RequestBody User updatedUser,@PathVariable Long user_id) throws Exception {
        userService.updateUser(user_id, updatedUser);
    }

    @GetMapping("/{user_id}/tasks")
    public List<Task> getTasksByUserId(@PathVariable Long user_id) throws Exception
    {
        return userService.getTasksByUserId(user_id);
    }

    @DeleteMapping("/{user_id}")
    public void deleteUsers(@PathVariable Long user_id) throws Exception {
        userService.deleteUsers(user_id);
    }
}

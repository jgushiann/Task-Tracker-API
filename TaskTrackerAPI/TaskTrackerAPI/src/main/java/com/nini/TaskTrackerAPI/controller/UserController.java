package com.nini.TaskTrackerAPI.controller;

import com.nini.TaskTrackerAPI.dto.TaskResponseDTO;
import com.nini.TaskTrackerAPI.dto.UserRequestDTO;
import com.nini.TaskTrackerAPI.dto.UserResponseDTO;
import com.nini.TaskTrackerAPI.model.Task;
import com.nini.TaskTrackerAPI.model.User;
import com.nini.TaskTrackerAPI.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserResponseDTO> getUsers(@RequestParam(required = false) String firstname,
                                          @RequestParam(required = false) String lastname,
                                          @RequestParam(required = false) String username,
                                          @RequestParam(required = false) String email,
                                          @RequestParam(required = false) Long id) throws Exception
    {
        List<User> users = userService.searchUser(firstname, lastname, username, email, id);
        return users.stream()
                .map(user -> new UserResponseDTO(
                        user.getFirstName(),
                        user.getLastName(),
                        user.getEmail(),
                        user.getUsername()
                )).toList();
    }

    @PostMapping
    public void addUser(@RequestBody @Valid UserRequestDTO userDTO) throws Exception {
        userService.createUser(userDTO);
    }

    @GetMapping("/{user_id}")
    public UserResponseDTO getUser(@PathVariable Long user_id) throws Exception {
        User user = userService.searchUserByUserId(user_id);
        return new UserResponseDTO(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getUsername()
        );
    }

    @PutMapping("/{user_id}")
    public void updateUser(@RequestBody @Valid UserRequestDTO updatedUserDTO,@PathVariable Long user_id) throws Exception {
        userService.updateUser(user_id, updatedUserDTO);
    }

    @GetMapping("/{user_id}/tasks")
    public List<TaskResponseDTO> getTasksByUserId(@PathVariable Long user_id) throws Exception
    {
        List<Task> tasks = userService.getTasksByUserId(user_id);
        return tasks.stream()
                .map(task -> new TaskResponseDTO(
                        task.getTitle(),
                        task.getDescription(),
                        task.getAssignedUser().getUserId(),
                        task.getPriority(),
                        task.getCategory(),
                        task.getStatus(),
                        task.getDueDate()
                )).toList();
    }

    @DeleteMapping("/{user_id}")
    public void deleteUsers(@PathVariable Long user_id) throws Exception {
        userService.deleteUsers(user_id);
    }
}

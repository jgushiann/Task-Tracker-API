package com.nini.TaskTrackerAPI.controller;

import com.nini.TaskTrackerAPI.dto.TaskResponseDTO;
import com.nini.TaskTrackerAPI.dto.UserRequestDTO;
import com.nini.TaskTrackerAPI.dto.UserResponseDTO;
import com.nini.TaskTrackerAPI.exception.IncorrectCredentialsException;
import com.nini.TaskTrackerAPI.mapper.UserMapper;
import com.nini.TaskTrackerAPI.model.User;
import com.nini.TaskTrackerAPI.security.JwtUtil;
import com.nini.TaskTrackerAPI.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;


    @Operation(summary = "Register a user")
    @PostMapping("/auth/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserRequestDTO userRequestDTO){
        UserResponseDTO user = userService.createUser(userRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @Operation(summary = "Login a user")
    @PostMapping("/auth/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody UserRequestDTO userRequestDTO) throws Exception {
        User user = (User) userService.loadUserByUsername(userRequestDTO.getUsername());

        if(passwordEncoder.matches(userRequestDTO.getPassword(), user.getPassword())){
            String token = jwtUtil.generateToken(userRequestDTO.getUsername());
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            UserResponseDTO userResponseDTO = userMapper.toDto(user);
            userResponseDTO.setToken(token);
            return ResponseEntity.status(HttpStatus.OK).body(userResponseDTO);
        }else{
            throw new IncorrectCredentialsException("Invalid username or password");
        }
    }


    @Operation(summary = "Get all/specific user from DB")
    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDTO>> getUsers(@RequestParam(required = false) String firstname,
                                          @RequestParam(required = false) String lastname,
                                          @RequestParam(required = false) String username,
                                          @RequestParam(required = false) String email,
                                          @RequestParam(required = false) Long id){
        List<UserResponseDTO> users = userService.searchUser(firstname, lastname, username, email, id);
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @Operation(summary = "Get a user by its ID")
    @GetMapping("/users/{user_id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable Long user_id) {
        User user = userService.searchUserByUserId(user_id);
        return ResponseEntity.status(HttpStatus.OK).body(userMapper.toDto(user));
    }

    @Operation(summary = "Update an existing user")
    @PutMapping("/users/{user_id}")
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody @Valid UserRequestDTO updatedUserDTO,@PathVariable Long user_id){
        UserResponseDTO updated = userService.updateUser(user_id, updatedUserDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    @Operation(summary = "Get all the tasks related to a specific user")
    @GetMapping("/users/{user_id}/tasks")
    public ResponseEntity<List<TaskResponseDTO>> getTasksByUserId(@PathVariable Long user_id){
        List<TaskResponseDTO> tasks = userService.getTasksByUserId(user_id);
        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }

    @Operation(summary = "Delete an existing user")
    @DeleteMapping("/users/{user_id}")
    public ResponseEntity<Void> deleteUsers(@PathVariable Long user_id){
        userService.deleteUsers(user_id);
        return ResponseEntity.noContent().build();
    }
}

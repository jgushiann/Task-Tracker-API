package com.nini.TaskTrackerAPI.controller;

import com.nini.TaskTrackerAPI.dto.TaskResponseDTO;
import com.nini.TaskTrackerAPI.dto.UserRequestDTO;
import com.nini.TaskTrackerAPI.dto.UserResponseDTO;
import com.nini.TaskTrackerAPI.mapper.UserMapper;
import com.nini.TaskTrackerAPI.model.User;
import com.nini.TaskTrackerAPI.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserRequestDTO userRequestDTO){
        UserResponseDTO user = userService.createUser(userRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody UserRequestDTO userRequestDTO) throws Exception {
        String username = userRequestDTO.getUsername();
        String password = passwordEncoder.encode(userRequestDTO.getPassword());

        if(userService.loadUserByUsername(username).getPassword().equals(password)){
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        }else{
            throw new Exception("Incorrect username or password");
        }
        return null;
    }


    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getUsers(@RequestParam(required = false) String firstname,
                                          @RequestParam(required = false) String lastname,
                                          @RequestParam(required = false) String username,
                                          @RequestParam(required = false) String email,
                                          @RequestParam(required = false) Long id){
        List<UserResponseDTO> users = userService.searchUser(firstname, lastname, username, email, id);
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> addUser(@RequestBody @Valid UserRequestDTO userDTO) {
        UserResponseDTO userDto = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable Long user_id) {
        User user = userService.searchUserByUserId(user_id);
        return ResponseEntity.status(HttpStatus.OK).body(userMapper.toDto(user));
    }

    @PutMapping("/{user_id}")
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody @Valid UserRequestDTO updatedUserDTO,@PathVariable Long user_id){
        UserResponseDTO updated = userService.updateUser(user_id, updatedUserDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    @GetMapping("/{user_id}/tasks")
    public ResponseEntity<List<TaskResponseDTO>> getTasksByUserId(@PathVariable Long user_id){
        List<TaskResponseDTO> tasks = userService.getTasksByUserId(user_id);
        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<Void> deleteUsers(@PathVariable Long user_id){
        userService.deleteUsers(user_id);
        return ResponseEntity.noContent().build();
    }
}

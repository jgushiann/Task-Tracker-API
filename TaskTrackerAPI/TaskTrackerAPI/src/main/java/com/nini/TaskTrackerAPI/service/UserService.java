package com.nini.TaskTrackerAPI.service;

import com.nini.TaskTrackerAPI.dto.UserRequestDTO;
import com.nini.TaskTrackerAPI.model.Task;
import com.nini.TaskTrackerAPI.model.User;
import com.nini.TaskTrackerAPI.repository.TaskRepository;
import com.nini.TaskTrackerAPI.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    public List<User> searchUser(String firstname, String lastname,String username, String email, Long id) throws Exception {
        if (firstname != null) return getUserByFirstNameContaining(firstname);
        if (lastname != null) return getUserByLastNameContaining(lastname);
        if (username != null) return getUserByUsernameContaining(username);
        if (email != null) return getUserByEmailContaining(email);
        if (id != null) return List.of(getUserById(id));

        return getAll();
    }

    public User searchUserByUserId(Long user_id){
        return userRepository.findById(user_id).orElse(null);
    }

    @Transactional
    public void updateUser(Long user_id, UserRequestDTO updatedUserDTO) throws Exception {
        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setFirstName(updatedUserDTO.getFirstName());
        user.setLastName(updatedUserDTO.getLastName());
        user.setUsername(updatedUserDTO.getUsername());
        user.setEmail(updatedUserDTO.getEmail());
        user.setPassword(updatedUserDTO.getPassword());

        userRepository.save(user);
    }

    @Transactional
    public void deleteUsers(Long id) throws Exception {
        if(id != null){
            deleteUserById(id);
        }else{
            throw new Exception("User does not exist");
        }
    }

    public List<Task> getTasksByUserId(Long user_id) throws Exception {
        Optional<User> user = userRepository.findByUserId(user_id);
        return taskRepository.findByAssignedUser(user.get());
    }

    @Transactional
    public void createUser(UserRequestDTO userDTO) throws Exception {
        if(!userRepository.existsByUsername(userDTO.getUsername())){
            User user = new User();
            List<Task> tasks = new ArrayList<>();
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setUsername(userDTO.getUsername());
            user.setEmail(userDTO.getEmail());
            user.setPassword(userDTO.getPassword());
            user.setTasks(tasks);
            userRepository.save(user);
        }else{
            throw new Exception("User already exists");
        }
    }

    private List<User> getAll(){
        return userRepository.findAll();
    }

    private User getUserById(Long id) throws Exception {
        return userRepository.findByUserId(id)
                .orElseThrow(() -> new Exception("User not found with id" + id));
    }

    private List<User> getUserByFirstNameContaining(String firstName){
        return userRepository.findByFirstNameContaining(firstName);
    }

    private List<User> getUserByLastNameContaining(String lastName){
        return userRepository.findByLastNameContaining(lastName);
    }

    private List<User> getUserByEmailContaining(String email){
        return userRepository.findByEmailContaining(email);
    }

    private List<User> getUserByUsernameContaining(String username){
        return userRepository.findByUsernameContaining(username);
    }

    private void deleteUserById(Long id) throws Exception {
        if(userRepository.existsByUserId(id)){
            userRepository.deleteByUserId(id);
        }else{
            throw new Exception("User not found with id" + id);
        }
    }
}

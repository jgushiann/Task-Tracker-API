package com.nini.TaskTrackerAPI.service;

import com.nini.TaskTrackerAPI.model.Task;
import com.nini.TaskTrackerAPI.model.User;
import com.nini.TaskTrackerAPI.repository.TaskRepository;
import com.nini.TaskTrackerAPI.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    public List<User> searchUser(String firstname, String lastname,String username, String email, Long id) throws Exception {
        if(firstname != null){
            return getUserByFirstNameContaining(firstname);
        }else if(lastname != null){
            return getUserByLastNameContaining(lastname);
        }else if(username != null){
            return getUserByUsernameContaining(username);
        }else if(email != null){
            return getUserByEmailContaining(email);
        }else if(id != null){
            return List.of(getUserById(id));
        }else{
            return getAll();
        }
    }

    public User searchUserByUserId(Long user_id){
        return userRepository.findById(user_id).orElse(null);
    }

    @Transactional
    public void updateUser(Long user_id, User updatedUser){
        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new RuntimeException("User not found"));


        if(updatedUser.getFirstName() != null){
            user.setFirstName(updatedUser.getFirstName());
        }

        if(updatedUser.getLastName() != null){
            user.setLastName(updatedUser.getLastName());
        }

        if(updatedUser.getUsername() != null){
            user.setUsername(updatedUser.getUsername());
        }

        if(updatedUser.getEmail() != null){
            user.setEmail(updatedUser.getEmail());
        }

        if(updatedUser.getPassword() != null){
            user.setPassword(updatedUser.getPassword());
        }

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
    public void createUser(User user) throws Exception {
        if(!userRepository.existsByUsername(user.getUsername())){
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

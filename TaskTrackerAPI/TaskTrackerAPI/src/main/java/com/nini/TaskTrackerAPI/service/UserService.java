package com.nini.TaskTrackerAPI.service;

import com.nini.TaskTrackerAPI.model.Task;
import com.nini.TaskTrackerAPI.model.User;
import com.nini.TaskTrackerAPI.repository.TaskRepository;
import com.nini.TaskTrackerAPI.repository.UserRepository;
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

    public void deleteUsers(String firstname, String lastname, String username, String email, Long id) throws Exception {
        if(firstname != null){
            deleteUserByFirstName(firstname);
        }else if(lastname != null){
            deleteUserByLastName(lastname);
        }else if(username != null){
            deleteUserByUsername(username);
        }else if(email != null){
            deleteUserByEmail(email);
        }else if(id != null){
            deleteUserById(id);
        }
    }

    public List<Task> getTasksByUserId(Long user_id) throws Exception {
        Optional<User> user = userRepository.findByUser_id(user_id);
        return taskRepository.findByAssignedUser(user.get());
    }

    private List<User> getAll(){
        return userRepository.findAll();
    }

    private User getUserById(Long id) throws Exception {
        return userRepository.findByUser_id(id)
                .orElseThrow(() -> new Exception("User not found with id" + id));
    }

    private List<User> getUserByFirstNameContaining(String firstName){
        return userRepository.findByFirst_nameContaining(firstName);
    }

    private List<User> getUserByLastNameContaining(String lastName){
        return userRepository.findByLast_nameContaining(lastName);
    }

    private List<User> getUserByEmailContaining(String email){
        return userRepository.findByEmailContaining(email);
    }

    private List<User> getUserByUsernameContaining(String username){
        return userRepository.findByUsernameContaining(username);
    }

    private void deleteUserById(Long id) throws Exception {
        if(userRepository.existsByUser_id(id)){
            userRepository.deleteByUser_id(id);
        }else{
            throw new Exception("User not found with id" + id);
        }
    }

    private void deleteUserByFirstName(String firstName) throws Exception {
        if(userRepository.existsByFirst_name(firstName)){
            userRepository.deleteByFirst_name(firstName);
        }else{
            throw new Exception("User not found with first name " + firstName);
        }
    }

    private void deleteUserByLastName(String lastName) throws Exception {
        if(userRepository.existsByLast_name(lastName)){
            userRepository.deleteByLast_name(lastName);
        }else{
            throw new Exception("User not found with last name " + lastName);
        }
    }

    private void deleteUserByEmail(String email) throws Exception {
        if(userRepository.existsByEmail(email)){
            userRepository.deleteByEmail(email);
        }else{
            throw new Exception("User not found with email " + email);
        }
    }

    private void deleteUserByUsername(String username) throws Exception {
        if(userRepository.existsByUsername(username)){
            userRepository.deleteByUsername(username);
        }else{
            throw new Exception("User not found with username " + username);
        }
    }
}

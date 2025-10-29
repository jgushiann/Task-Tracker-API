package com.nini.TaskTrackerAPI.service;

import com.nini.TaskTrackerAPI.model.*;
import com.nini.TaskTrackerAPI.repository.TaskRepository;
import com.nini.TaskTrackerAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Task> searchTasks(String title, String description, Long id, Priority priority, Status status, Category category, LocalDate dueDate, Long user_id){
        if(title != null){
            return getTasksByTitleContaining(title);
        }else if(description != null){
            return getTasksByDescriptionContaining(description);
        }else if(id != null){
            return List.of(getTaskById(id));
        }else if(priority != null){
            return getTasksByPriority(priority);
        }else if(status != null){
            return getTasksByStatus(status);
        }else if(category != null){
            return getTasksByCategory(category);
        }else if(dueDate != null){
            return getTasksByDueDate(dueDate);
        }else if(user_id != null){
            return getTasksByAssignedUser(userRepository.findByUserId(user_id).get());
        }
        return getAll();
    }

    public List<Task> getAll(){
        return taskRepository.findAll();
    }

    public void deleteTasks(String title, String description, Long id, Priority priority, Status status, Category category, LocalDate dueDate, Long user_id) throws Exception {
        if(title != null){
            deleteTaskByTitleContaining(title);
        }else if(description != null){
            deleteTaskByDescriptionContaining(description);
        }else if(id != null){
            deleteTaskById(id);
        }else if(priority != null){
            deleteTaskByPriority(priority);
        }else if(status != null){
            deleteTaskByStatus(status);
        }else if(category != null){
            deleteTaskByCategory(category);
        }else if(dueDate != null){
            deleteTaskByDueDate(dueDate);
        }else if(user_id != null){
            deleteTaskByAssignedUser(userRepository.findByUserId(user_id).get());
        }
    }

    private Task getTaskById(Long id) {
        return taskRepository.findByTaskId(id).
                orElseThrow(() -> new RuntimeException("Task not found"));
    }

    private List<Task> getTasksByPriority(Priority priority) {
        return taskRepository.findByPriority(priority);
    }

    private List<Task> getTasksByCategory(Category category){
        return taskRepository.findByCategory(category);
    }

    private List<Task> getTasksByStatus(Status status){
        return taskRepository.findByStatus(status);
    }

    private List<Task> getTasksByDueDate(LocalDate dueDate){
        return taskRepository.findByDueDate(dueDate);
    }

    private List<Task> getTasksByAssignedUser(User assignedUser){
        return taskRepository.findByAssignedUser(assignedUser);
    }

    private List<Task> getTasksByTitleContaining(String title){
        return taskRepository.findByTitleContaining(title);
    }

    private List<Task> getTasksByDescriptionContaining(String description){
        return taskRepository.findByDescriptionContaining(description);
    }

    private void deleteTaskById(Long id) throws Exception {
        if(taskRepository.existsByTaskId(id)){
            taskRepository.deleteByTaskId(id);
        }else{
            throw new RuntimeException("Task does not exist");
        }
    }

    private void deleteTaskByTitleContaining(String title) throws Exception {
        if(taskRepository.existsByTitleContaining(title)){
            taskRepository.deleteByTitleContaining(title);
        }else{
            throw new RuntimeException("Task does not exist");
        }
    }

    private void deleteTaskByDescriptionContaining(String description) throws Exception {
        if(taskRepository.existsByDescriptionContaining(description)){
            taskRepository.deleteByDescriptionContaining(description);
        }else{
            throw new RuntimeException("Task does not exist");
        }
    }

    private void deleteTaskByPriority(Priority priority) throws Exception {
        if(taskRepository.existsByPriority(priority)){
            taskRepository.deleteByPriority(priority);
        }else{
            throw new RuntimeException("Task does not exist");
        }
    }

    private void deleteTaskByCategory(Category category) throws Exception {
        if(taskRepository.existsByCategory(category)){
            taskRepository.deleteByCategory(category);
        }else{
            throw new RuntimeException("Task does not exist");
        }
    }

    private void deleteTaskByStatus(Status status) throws Exception {
        if(taskRepository.existsByStatus(status)){
            taskRepository.deleteByStatus(status);
        }else{
            throw new RuntimeException("Task does not exist");
        }
    }

    private void deleteTaskByDueDate(LocalDate dueDate) throws Exception {
        if(taskRepository.existsByDueDate(dueDate)){
            taskRepository.deleteByDueDate(dueDate);
        }else{
            throw new RuntimeException("Task does not exist");
        }
    }

    private void deleteTaskByAssignedUser(User assignedUser) throws Exception {
        if(taskRepository.existsByAssignedUser(assignedUser)){
            taskRepository.deleteByAssignedUser(assignedUser);
        }else{
            throw new RuntimeException("Task does not exist");
        }
    }
}

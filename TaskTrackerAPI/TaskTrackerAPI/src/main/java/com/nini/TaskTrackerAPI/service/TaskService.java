package com.nini.TaskTrackerAPI.service;

import com.nini.TaskTrackerAPI.model.*;
import com.nini.TaskTrackerAPI.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task getTaskById(Long id) {
        return taskRepository.findByTask_id(id).
                orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public Task getTaskByTitle(String title) {
        return taskRepository.findByTitle(title).
                orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public Task getTaskByDescription(String description) {
        return taskRepository.findByDescription(description).
                orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public List<Task> getTasksByPriority(Priority priority) {
        return taskRepository.findByPriority(priority);
    }

    public List<Task> getTasksByCategory(Category category){
        return taskRepository.findByCategory(category);
    }

    public List<Task> getTasksByStatus(Status status){
        return taskRepository.findByStatus(status);
    }

    public List<Task> getTasksByDueDate(LocalDate dueDate){
        return taskRepository.findByDueDate(dueDate);
    }

    public List<Task> getTasksByAssignedUser(User assignedUser){
        return taskRepository.findByAssignedUser(assignedUser);
    }

    public List<Task> getTasksByTitleContaining(String title){
        return taskRepository.findByTitleContaining(title);
    }

    public List<Task> getTasksByDescriptionContaining(String description){
        return taskRepository.findByDescriptionContaining(description);
    }

    public void deleteTaskById(Long id) throws Exception {
        if(taskRepository.existsByTask_id(id)){
            taskRepository.deleteByTask_id(id);
        }else{
            throw new RuntimeException("Task does not exist");
        }
    }

    public void deleteTaskByTitleContaining(String title) throws Exception {
        if(taskRepository.existsByTitleContaining(title)){
            taskRepository.deleteByTitleContaining(title);
        }else{
            throw new RuntimeException("Task does not exist");
        }
    }

    public void deleteTaskByDescriptionContaining(String description) throws Exception {
        if(taskRepository.existsByDescriptionContaining(description)){
            taskRepository.deleteByDescriptionContaining(description);
        }else{
            throw new RuntimeException("Task does not exist");
        }
    }

    public void deleteTaskByPriority(Priority priority) throws Exception {
        if(taskRepository.existsByPriority(priority)){
            taskRepository.deleteByPriority(priority);
        }else{
            throw new RuntimeException("Task does not exist");
        }
    }

    public void deleteTaskByCategory(Category category) throws Exception {
        if(taskRepository.existsByCategory(category)){
            taskRepository.deleteByCategory(category);
        }else{
            throw new RuntimeException("Task does not exist");
        }
    }

    public void  deleteTaskByStatus(Status status) throws Exception {
        if(taskRepository.existsByStatus(status)){
            taskRepository.deleteByStatus(status);
        }else{
            throw new RuntimeException("Task does not exist");
        }
    }

    public void deleteTaskByDueDate(LocalDate dueDate) throws Exception {
        if(taskRepository.existsByDueDate(dueDate)){
            taskRepository.deleteByDueDate(dueDate);
        }else{
            throw new RuntimeException("Task does not exist");
        }
    }

    public void deleteTaskByAssignedUser(User assignedUser) throws Exception {
        if(taskRepository.existsByAssignedUser(assignedUser)){
            taskRepository.deleteByAssignedUser(assignedUser);
        }else{
            throw new RuntimeException("Task does not exist");
        }
    }
}

package com.nini.TaskTrackerAPI.service;

import com.nini.TaskTrackerAPI.model.*;
import com.nini.TaskTrackerAPI.repository.TaskRepository;
import com.nini.TaskTrackerAPI.repository.UserRepository;
import jakarta.transaction.Transactional;
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
    @Autowired
    private UserService userService;

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

    public void createTask(Task task){
        if(task.getAssignedUser() == null){
            throw new IllegalArgumentException("Assigned User cannot be null");
        }else{
           User assignedUser = userService.searchUserByUserId(task.getAssignedUser().getUserId());
           if(assignedUser != null){
               task.setAssignedUser(assignedUser);
               taskRepository.save(task);
           }else throw new IllegalArgumentException("Assigned User not found");
        }
    }

    public Task searchTaskById(Long task_id){
        return taskRepository.findByTaskId(task_id)
                .orElseThrow(() -> new RuntimeException("No task found"));
    }

    public void updateTask(Task updatedTask, Long task_id){
        Task existingTask = taskRepository.findByTaskId(task_id)
                .orElseThrow(() -> new RuntimeException("No task found"));

        if(updatedTask.getTitle() != null){
            existingTask.setTitle(updatedTask.getTitle());
        }

        if(updatedTask.getDescription() != null){
            existingTask.setDescription(updatedTask.getDescription());
        }

        if(updatedTask.getPriority() != null){
            existingTask.setPriority(updatedTask.getPriority());
        }

        if(updatedTask.getCategory() != null){
            existingTask.setCategory(updatedTask.getCategory());
        }

        if(updatedTask.getDueDate() != null){
            existingTask.setDueDate(updatedTask.getDueDate());
        }

        if(updatedTask.getAssignedUser() != null){
            existingTask.setAssignedUser(updatedTask.getAssignedUser());
        }

        if(updatedTask.getStatus() != null){
            existingTask.setStatus(updatedTask.getStatus());
        }
        taskRepository.save(existingTask);
    }

    @Transactional
    public void deleteTask(Long task_id) throws Exception {
        if(taskRepository.existsByTaskId(task_id)){
            deleteTaskById(task_id);
        }else{
            throw new Exception("No task found");
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

    public List<Task> getTasksByAssignedUser(User assignedUser){
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

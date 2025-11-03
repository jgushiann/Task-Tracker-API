package com.nini.TaskTrackerAPI.service;

import com.nini.TaskTrackerAPI.dto.TaskRequestDTO;
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
        if (title != null) return getTasksByTitleContaining(title);
        if (description != null) return getTasksByDescriptionContaining(description);
        if (id != null) return List.of(getTaskById(id));
        if (priority != null) return getTasksByPriority(priority);
        if (status != null) return getTasksByStatus(status);
        if (category != null) return getTasksByCategory(category);
        if (dueDate != null) return getTasksByDueDate(dueDate);
        if (user_id != null) return getTasksByAssignedUser(userRepository.findByUserId(user_id).orElse(null));
        return getAll();
    }

    public List<Task> getAll(){
        return taskRepository.findAll();
    }

    @Transactional
    public void createTask(TaskRequestDTO taskDTO) throws Exception{
        Task task = new Task();
        task.setTitle(taskDTO.getTaskTitle());
        task.setDescription(taskDTO.getTaskDescription());
        task.setStatus(taskDTO.getTaskStatus());
        task.setPriority(taskDTO.getTaskPriority());
        task.setDueDate(taskDTO.getTaskDueDate());
        task.setCategory(taskDTO.getTaskCategory());
        task.setAssignedUser(userService.searchUserByUserId(taskDTO.getTaskAssignedUserId()));

        taskRepository.save(task);
    }

    public Task searchTaskById(Long task_id){
        return taskRepository.findByTaskId(task_id)
                .orElseThrow(() -> new RuntimeException("No task found"));
    }

    @Transactional
    public void updateTask(TaskRequestDTO updatedTaskDTO, Long task_id){
        Task existingTask = taskRepository.findByTaskId(task_id)
                .orElseThrow(() -> new RuntimeException("No task found"));

        existingTask.setTitle(updatedTaskDTO.getTaskTitle());
        existingTask.setDescription(updatedTaskDTO.getTaskDescription());
        existingTask.setPriority(updatedTaskDTO.getTaskPriority());
        existingTask.setCategory(updatedTaskDTO.getTaskCategory());
        existingTask.setDueDate(updatedTaskDTO.getTaskDueDate());
        existingTask.setAssignedUser(userService.searchUserByUserId(updatedTaskDTO.getTaskAssignedUserId()));
        existingTask.setStatus(updatedTaskDTO.getTaskStatus());

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
}

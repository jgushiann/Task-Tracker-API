package com.nini.TaskTrackerAPI.service;

import com.nini.TaskTrackerAPI.dto.TaskRequestDTO;
import com.nini.TaskTrackerAPI.dto.TaskResponseDTO;
import com.nini.TaskTrackerAPI.exception.AlreadyExistsException;
import com.nini.TaskTrackerAPI.exception.NotFoundException;
import com.nini.TaskTrackerAPI.mapper.TaskMapper;
import com.nini.TaskTrackerAPI.model.*;
import com.nini.TaskTrackerAPI.repository.TaskRepository;
import com.nini.TaskTrackerAPI.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TaskService {

    private final TaskMapper taskMapper;

    private final TaskRepository taskRepository;

    private final UserRepository userRepository;

    public List<TaskResponseDTO> searchTasks(String title, String description, Long id, Priority priority, Status status, Category category, LocalDate dueDate, Long user_id){
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

    public List<TaskResponseDTO> getAll(){
        return taskRepository.findAll().stream().map(taskMapper::toDto).toList();
    }

    @Transactional
    public TaskResponseDTO createTask(TaskRequestDTO taskDto){
        if(!taskRepository.findByTitleContaining(taskDto.getTitle()).isEmpty()){
            throw new AlreadyExistsException("Title already exists");
        }
        Task task = taskMapper.toEntity(taskDto);
        taskRepository.save(task);
        return taskMapper.toDto(task);
    }

    public TaskResponseDTO searchTaskById(Long task_id){
        Task task = taskRepository.findByTaskId(task_id)
                .orElseThrow(() -> new RuntimeException("No task found"));
        return taskMapper.toDto(task);
    }

    @Transactional
    public TaskResponseDTO updateTask(TaskRequestDTO updatedTaskDTO, Long task_id){
        Task existingTask = taskRepository.findByTaskId(task_id)
                .orElseThrow(() -> new NotFoundException("No task found"));

        Task task = taskMapper.updateTask(updatedTaskDTO, existingTask);
        taskRepository.save(task);
        return taskMapper.toDto(task);
    }

    @Transactional
    public void deleteTask(Long id) {
        Task task =  taskRepository.findByTaskId(id).orElseThrow(() -> new NotFoundException("No task found"));
        taskRepository.deleteByTaskId(id);
    }

    private TaskResponseDTO getTaskById(Long id) {
        Task task = taskRepository.findByTaskId(id).
                orElseThrow(() -> new NotFoundException("Task not found"));
        return taskMapper.toDto(task);
    }

    private List<TaskResponseDTO> getTasksByPriority(Priority priority) {
        return taskRepository.findByPriority(priority).stream().map(taskMapper::toDto).toList();
    }

    private List<TaskResponseDTO> getTasksByCategory(Category category){
        return taskRepository.findByCategory(category).stream().map(taskMapper::toDto).toList();
    }

    private List<TaskResponseDTO> getTasksByStatus(Status status){
        return taskRepository.findByStatus(status).stream().map(taskMapper::toDto).toList();
    }

    private List<TaskResponseDTO> getTasksByDueDate(LocalDate dueDate){
        return taskRepository.findByDueDate(dueDate).stream().map(taskMapper::toDto).toList();
    }

    public List<TaskResponseDTO> getTasksByAssignedUser(User assignedUser){
        return taskRepository.findByAssignedUser(assignedUser).stream().map(taskMapper::toDto).toList();
    }

    private List<TaskResponseDTO> getTasksByTitleContaining(String title){
        return taskRepository.findByTitleContaining(title).stream().map(taskMapper::toDto).toList();
    }

    private List<TaskResponseDTO> getTasksByDescriptionContaining(String description){
        return taskRepository.findByDescriptionContaining(description).stream().map(taskMapper::toDto).toList();
    }

}

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
        if(title == null && description == null && id == null && priority == null && status == null && category == null && dueDate == null && user_id == null){
            return getAll();
        }
        return taskRepository.searchTasks(title,description,id, priority, status, category, dueDate, user_id).stream().map(taskMapper::toDto).toList();
    }

    public List<TaskResponseDTO> getAll(){
        return taskRepository.findAll()
                .stream()
                .map(taskMapper::toDto)
                .toList();
    }

    @Transactional
    public TaskResponseDTO createTask(TaskRequestDTO taskDto){
        if(!taskRepository.findByTitle(taskDto.getTitle()).isEmpty()){
            throw new AlreadyExistsException("Title already exists");
        }
        Task task = taskMapper.toEntity(taskDto);
        taskRepository.save(task);
        return taskMapper.toDto(task);
    }

    public TaskResponseDTO searchTaskById(Long task_id){
        Task task = taskRepository.findByTaskId(task_id)
                .orElseThrow(() -> new NotFoundException("No task found"));
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
        if(!taskRepository.existsById(id)){
            throw new NotFoundException("No task found");
        }
        taskRepository.deleteByTaskId(id);
    }

    public List<TaskResponseDTO> getTasksByAssignedUserId(Long id){
        return taskRepository.findByAssignedUser(userRepository.findById(id).orElseThrow(() -> new NotFoundException("User does not exist"))).stream().map(taskMapper::toDto).toList();
    }

}

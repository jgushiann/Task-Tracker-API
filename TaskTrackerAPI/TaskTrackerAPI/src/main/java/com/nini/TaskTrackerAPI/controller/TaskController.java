package com.nini.TaskTrackerAPI.controller;

import com.nini.TaskTrackerAPI.dto.TaskRequestDTO;
import com.nini.TaskTrackerAPI.dto.TaskResponseDTO;
import com.nini.TaskTrackerAPI.mapper.TaskMapper;
import com.nini.TaskTrackerAPI.mapper.UserMapper;
import com.nini.TaskTrackerAPI.model.*;
import com.nini.TaskTrackerAPI.service.TaskService;
import com.nini.TaskTrackerAPI.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    private final UserService userService;

    private final TaskMapper taskMapper;

    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> searchTasks(@RequestParam(required = false) String title,
                                                            @RequestParam(required = false) String description,
                                                            @RequestParam(required = false) Long id,
                                                            @RequestParam(required = false) Priority priority,
                                                            @RequestParam(required = false) Status status,
                                                            @RequestParam(required = false) Category category,
                                                            @RequestParam(required = false) LocalDate dueDate,
                                                            @RequestParam(required = false) Long assignedUserId){
        List<TaskResponseDTO> taskDTOs = taskService.searchTasks(title, description, id, priority, status, category, dueDate, assignedUserId);
        return ResponseEntity.status(HttpStatus.OK).body(taskDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTask(@PathVariable("id") Long id){
        TaskResponseDTO taskDTO = taskService.searchTaskById(id);
        return ResponseEntity.status(HttpStatus.OK).body(taskDTO);
    }

    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(@RequestBody @Valid Task task){
        TaskResponseDTO createdTask = taskService.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> updateTask(@RequestBody @Valid TaskRequestDTO updatedTaskDTO, @PathVariable Long id){
        TaskResponseDTO updatedTask = taskService.updateTask(updatedTaskDTO, id);
        return ResponseEntity.status(HttpStatus.OK).body(updatedTask);
    }

    @GetMapping("/user/{user_id}")
    public List<TaskResponseDTO> getTasksForUser(@PathVariable Long user_id) throws Exception{
        User user = userService.searchUserByUserId(user_id);
        return taskService.getTasksByAssignedUser(user);
    }

    @DeleteMapping("/{task_id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long task_id) throws Exception {
        taskService.deleteTask(task_id);
        return ResponseEntity.noContent().build();
    }
}

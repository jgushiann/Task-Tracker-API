package com.nini.TaskTrackerAPI.controller;

import com.nini.TaskTrackerAPI.dto.TaskRequestDTO;
import com.nini.TaskTrackerAPI.dto.TaskResponseDTO;
import com.nini.TaskTrackerAPI.model.*;
import com.nini.TaskTrackerAPI.service.TaskService;
import com.nini.TaskTrackerAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;

    @GetMapping
    public List<TaskResponseDTO> searchTasks(@RequestParam(required = false) String title,
                                  @RequestParam(required = false) String description,
                                  @RequestParam(required = false) Long id,
                                  @RequestParam(required = false) Priority priority,
                                  @RequestParam(required = false) Status status,
                                  @RequestParam(required = false) Category category,
                                  @RequestParam(required = false) LocalDate dueDate,
                                  @RequestParam(required = false) Long user_id) throws Exception{
        List<Task> tasks = taskService.searchTasks(title, description, id, priority, status, category,dueDate,user_id);

        return tasks.stream()
                .map(task -> new TaskResponseDTO(
                        task.getTitle(),
                        task.getDescription(),
                        task.getAssignedUser().getUserId(),
                        task.getPriority(),
                        task.getCategory(),
                        task.getStatus(),
                        task.getDueDate()
                ))
                .toList();
    }

    @GetMapping("/{task_id}")
    public TaskResponseDTO getTask(@PathVariable("task_id") Long task_id) throws Exception{
        Task task = taskService.searchTaskById(task_id);
        return new TaskResponseDTO(
                task.getTitle(),
                task.getDescription(),
                task.getAssignedUser().getUserId(),
                task.getPriority(),
                task.getCategory(),
                task.getStatus(),
                task.getDueDate()
        );
    }

    @PostMapping
    public void createTask(@RequestBody TaskRequestDTO taskDTO) throws Exception{
        taskService.createTask(taskDTO);
    }

    @PutMapping("/{task_id}")
    public void updateTask(@RequestBody TaskRequestDTO updatedTaskDTO, @PathVariable Long task_id) throws Exception{
        taskService.updateTask(updatedTaskDTO, task_id);
    }

    @GetMapping("/user/{user_id}")
    public List<TaskResponseDTO> getTasksForUser(@PathVariable Long user_id) throws Exception{
        User user = userService.searchUserByUserId(user_id);
        List<Task> tasks = taskService.getTasksByAssignedUser(user);
        return tasks.stream()
                .map(task -> new TaskResponseDTO(
                        task.getTitle(),
                        task.getDescription(),
                        task.getAssignedUser().getUserId(),
                        task.getPriority(),
                        task.getCategory(),
                        task.getStatus(),
                        task.getDueDate()
                )).toList();
    }

    @DeleteMapping("/{task_id}")
    public void deleteTask(@PathVariable Long task_id) throws Exception{
        taskService.deleteTask(task_id);
    }
}

package com.nini.TaskTrackerAPI.Service;

import com.nini.TaskTrackerAPI.dto.TaskRequestDTO;
import com.nini.TaskTrackerAPI.dto.TaskResponseDTO;
import com.nini.TaskTrackerAPI.mapper.TaskMapper;
import com.nini.TaskTrackerAPI.model.Task;
import com.nini.TaskTrackerAPI.model.User;
import com.nini.TaskTrackerAPI.repository.TaskRepository;
import com.nini.TaskTrackerAPI.repository.UserRepository;
import com.nini.TaskTrackerAPI.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {
    @Mock
    private TaskRepository taskRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private TaskMapper taskMapper;
    @InjectMocks
    private TaskService taskService;

    @Test
    void shouldSearchTasks() {
        String title = "Fix bug";

        Task task = new Task();
        task.setTitle(title);

        TaskResponseDTO dto = new TaskResponseDTO();
        dto.setTitle(title);

        when(taskRepository.searchTasks(
                eq(title), eq(null), eq(null),
                eq(null), eq(null), eq(null),
                eq(null), eq(null)
        )).thenReturn(List.of(task));

        when(taskMapper.toDto(task)).thenReturn(dto);

        List<TaskResponseDTO> result = taskService.searchTasks(
                title, null, null, null, null, null, null, null
        );

        assertEquals(1, result.size());
        assertEquals(title, result.get(0).getTitle());

        verify(taskRepository).searchTasks(
                title, null, null, null, null, null, null, null
        );
        verify(taskMapper).toDto(task);
    }


    @Test
    void shouldGetAllTasks() {
        Task task1 = new Task();
        Task task2 = new Task();

        TaskResponseDTO dto1 = new TaskResponseDTO();
        TaskResponseDTO dto2 = new TaskResponseDTO();

        when(taskRepository.findAll()).thenReturn(List.of(task1, task2));
        when(taskMapper.toDto(task1)).thenReturn(dto1);
        when(taskMapper.toDto(task2)).thenReturn(dto2);

        List<TaskResponseDTO> result = taskService.getAll();

        assertEquals(2, result.size());
        verify(taskRepository).findAll();
        verify(taskMapper).toDto(task1);
        verify(taskMapper).toDto(task2);
    }

    @Test
    void shouldCreateTask() {
        TaskRequestDTO request = new TaskRequestDTO();
        request.setTitle("New Task");
        request.setAssignedUserId(1L);

        User assignedUser = new User();
        assignedUser.setUserId(1L);

        Task mappedTask = new Task();
        mappedTask.setTitle("New Task");

        Task savedTask = new Task();
        savedTask.setTitle("New Task");
        savedTask.setAssignedUser(assignedUser);

        TaskResponseDTO responseDTO = new TaskResponseDTO();
        responseDTO.setTitle("New Task");

        when(taskRepository.findByTitle("New Task")).thenReturn(List.of());
        when(taskMapper.toEntity(request)).thenReturn(mappedTask);
        when(userRepository.findById(1L)).thenReturn(Optional.of(assignedUser));
        when(taskRepository.save(mappedTask)).thenReturn(savedTask);
        when(taskMapper.toDto(any(Task.class))).thenReturn(responseDTO);

        TaskResponseDTO result = taskService.createTask(request);

        assertEquals("New Task", result.getTitle());
        verify(taskRepository).save(mappedTask);
    }

    @Test
    void shouldFindTaskById() {
        Long taskId = 5L;

        Task task = new Task();
        task.setTaskId(taskId);
        task.setTitle("New Task");

        TaskResponseDTO dto = new TaskResponseDTO();
        dto.setTitle("New Task");

        when(taskRepository.findByTaskId(taskId))
                .thenReturn(Optional.of(task));

        when(taskMapper.toDto(task))
                .thenReturn(dto);

        TaskResponseDTO result = taskService.searchTaskById(taskId);

        assertEquals(task.getTitle(), result.getTitle());
        verify(taskRepository).findByTaskId(taskId);
    }

    @Test
    void shouldUpdateTask() {
        Long taskId = 1L;

        TaskRequestDTO request = new TaskRequestDTO();
        request.setTitle("Updated title");

        Task existingTask = new Task();
        existingTask.setTaskId(taskId);
        existingTask.setTitle("Old title");

        Task updatedTask = new Task();
        updatedTask.setTaskId(taskId);
        updatedTask.setTitle("Updated title");

        TaskResponseDTO responseDTO = new TaskResponseDTO();
        responseDTO.setTitle("Updated title");

        when(taskRepository.findByTaskId(taskId))
                .thenReturn(Optional.of(existingTask));

        when(taskMapper.updateTask(request, existingTask))
                .thenReturn(updatedTask);

        when(taskRepository.save(updatedTask))
                .thenReturn(updatedTask);

        when(taskMapper.toDto(any(Task.class)))
                .thenReturn(responseDTO);

        TaskResponseDTO result = taskService.updateTask(request, taskId);

        assertEquals("Updated title", result.getTitle());
        verify(taskRepository).findByTaskId(taskId);
        verify(taskMapper).updateTask(request, existingTask);
        verify(taskRepository).save(updatedTask);
        verify(taskMapper).toDto(updatedTask);
    }



}
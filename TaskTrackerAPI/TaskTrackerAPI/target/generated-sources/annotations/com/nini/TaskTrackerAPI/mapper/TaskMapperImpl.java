package com.nini.TaskTrackerAPI.mapper;

import com.nini.TaskTrackerAPI.dto.TaskRequestDTO;
import com.nini.TaskTrackerAPI.dto.TaskResponseDTO;
import com.nini.TaskTrackerAPI.model.Task;
import com.nini.TaskTrackerAPI.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-04T13:04:21+0400",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.16 (Eclipse Adoptium)"
)
@Component
public class TaskMapperImpl implements TaskMapper {

    @Override
    public TaskResponseDTO toDto(Task entity) {
        if ( entity == null ) {
            return null;
        }

        TaskResponseDTO taskResponseDTO = new TaskResponseDTO();

        taskResponseDTO.setAssignedUser( entityAssignedUserUserId( entity ) );
        taskResponseDTO.setTitle( entity.getTitle() );
        taskResponseDTO.setDescription( entity.getDescription() );
        taskResponseDTO.setPriority( entity.getPriority() );
        taskResponseDTO.setCategory( entity.getCategory() );
        taskResponseDTO.setStatus( entity.getStatus() );
        taskResponseDTO.setDueDate( entity.getDueDate() );

        return taskResponseDTO;
    }

    @Override
    public Task updateTask(TaskRequestDTO updatedTaskDTO, Task entity) {
        if ( updatedTaskDTO == null ) {
            return entity;
        }

        entity.setTitle( updatedTaskDTO.getTitle() );
        entity.setDescription( updatedTaskDTO.getDescription() );
        entity.setPriority( updatedTaskDTO.getPriority() );
        entity.setCategory( updatedTaskDTO.getCategory() );
        entity.setStatus( updatedTaskDTO.getStatus() );
        entity.setDueDate( updatedTaskDTO.getDueDate() );

        return entity;
    }

    @Override
    public Task toEntity(TaskRequestDTO updatedTaskDTO) {
        if ( updatedTaskDTO == null ) {
            return null;
        }

        Task task = new Task();

        task.setTitle( updatedTaskDTO.getTitle() );
        task.setDescription( updatedTaskDTO.getDescription() );
        task.setPriority( updatedTaskDTO.getPriority() );
        task.setCategory( updatedTaskDTO.getCategory() );
        task.setStatus( updatedTaskDTO.getStatus() );
        task.setDueDate( updatedTaskDTO.getDueDate() );

        return task;
    }

    private Long entityAssignedUserUserId(Task task) {
        User assignedUser = task.getAssignedUser();
        if ( assignedUser == null ) {
            return null;
        }
        return assignedUser.getUserId();
    }
}

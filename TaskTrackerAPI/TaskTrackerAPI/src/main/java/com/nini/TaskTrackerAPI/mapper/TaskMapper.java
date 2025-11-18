package com.nini.TaskTrackerAPI.mapper;

import com.nini.TaskTrackerAPI.dto.TaskRequestDTO;
import com.nini.TaskTrackerAPI.dto.TaskResponseDTO;
import com.nini.TaskTrackerAPI.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface TaskMapper {
    @Mapping(source = "assignedUser.userId", target = "assignedUser")
    TaskResponseDTO toDto(Task entity);
    Task updateTask(TaskRequestDTO updatedTaskDTO, @MappingTarget Task entity);
}

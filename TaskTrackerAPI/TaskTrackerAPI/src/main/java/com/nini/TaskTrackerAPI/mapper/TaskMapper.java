package com.nini.TaskTrackerAPI.mapper;

import com.nini.TaskTrackerAPI.dto.TaskResponseDTO;
import com.nini.TaskTrackerAPI.model.Task;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TaskMapper {
    TaskResponseDTO toDto(Task entity);
    Task toEntity(TaskResponseDTO dto);
}

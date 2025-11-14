package com.nini.TaskTrackerAPI.mapper;

import com.nini.TaskTrackerAPI.dto.UserResponseDTO;
import com.nini.TaskTrackerAPI.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    UserResponseDTO toDto(User entity);
    User toEntity(UserResponseDTO dto);
}

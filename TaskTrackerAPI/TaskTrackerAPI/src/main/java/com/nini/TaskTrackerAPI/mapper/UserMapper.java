package com.nini.TaskTrackerAPI.mapper;

import com.nini.TaskTrackerAPI.dto.UserRequestDTO;
import com.nini.TaskTrackerAPI.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    UserRequestDTO toDto(User entity);
    User toEntity(UserRequestDTO dto);
}

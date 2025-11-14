package com.nini.TaskTrackerAPI.mapper;

import com.nini.TaskTrackerAPI.dto.UserResponseDTO;
import com.nini.TaskTrackerAPI.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDTO toDto(User entity);
    //User toEntity(UserResponseDTO dto);
}

package com.nini.TaskTrackerAPI.mapper;

import com.nini.TaskTrackerAPI.dto.UserRequestDTO;
import com.nini.TaskTrackerAPI.dto.UserResponseDTO;
import com.nini.TaskTrackerAPI.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDTO toDto(User entity);
    User toEntity(UserRequestDTO dto);
    User updateEntity(UserRequestDTO dto, @MappingTarget User entity);
}

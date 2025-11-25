package com.nini.TaskTrackerAPI.mapper;

import com.nini.TaskTrackerAPI.dto.UserRequestDTO;
import com.nini.TaskTrackerAPI.dto.UserResponseDTO;
import com.nini.TaskTrackerAPI.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-25T11:13:56+0400",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.16 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponseDTO toDto(User entity) {
        if ( entity == null ) {
            return null;
        }

        UserResponseDTO userResponseDTO = new UserResponseDTO();

        userResponseDTO.setFirstName( entity.getFirstName() );
        userResponseDTO.setLastName( entity.getLastName() );
        userResponseDTO.setEmail( entity.getEmail() );
        userResponseDTO.setUsername( entity.getUsername() );

        return userResponseDTO;
    }

    @Override
    public User toEntity(UserRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setFirstName( dto.getFirstName() );
        user.setLastName( dto.getLastName() );
        user.setEmail( dto.getEmail() );
        user.setUsername( dto.getUsername() );
        user.setPassword( dto.getPassword() );

        return user;
    }

    @Override
    public User updateEntity(UserRequestDTO dto, User entity) {
        if ( dto == null ) {
            return entity;
        }

        entity.setFirstName( dto.getFirstName() );
        entity.setLastName( dto.getLastName() );
        entity.setEmail( dto.getEmail() );
        entity.setUsername( dto.getUsername() );
        entity.setPassword( dto.getPassword() );

        return entity;
    }
}

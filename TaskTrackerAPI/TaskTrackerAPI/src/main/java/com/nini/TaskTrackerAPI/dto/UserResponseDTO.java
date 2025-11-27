package com.nini.TaskTrackerAPI.dto;

import com.nini.TaskTrackerAPI.model.Role;
import lombok.Data;

@Data
public class UserResponseDTO{
    Role role;
    String firstName;
    String lastName;
    String email;
    String username;
}

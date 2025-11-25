package com.nini.TaskTrackerAPI.dto;

import lombok.Data;

@Data
public class UserResponseDTO{
    String token;
    String firstName;
    String lastName;
    String email;
    String username;
}

package com.nini.TaskTrackerAPI.dto;

public record UserResponseDTO(
        String firstName,
        String lastName,
        String email,
        String username
) {
}

package com.nini.TaskTrackerAPI.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
    @NotBlank(message = "First name of the User must be provided")
    @Size(min = 5, max = 100, message = "First name must be between 5 and 100 characters")
    private String firstName;

    @NotBlank(message = "Last name of the User must be provided")
    @Size(min = 5, max = 100, message = "Last name must be between 5 and 100 characters")
    private String lastName;

    @Email
    private String email;

    @NotBlank(message = "Username is required")
    @Size(min = 5, max = 20, message = "Username must be between 5 and 20 characters")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
}

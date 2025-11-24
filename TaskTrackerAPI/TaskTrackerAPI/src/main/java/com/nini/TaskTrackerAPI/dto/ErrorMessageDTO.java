package com.nini.TaskTrackerAPI.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ErrorMessageDTO {
    String message;
    HttpStatus status;
}

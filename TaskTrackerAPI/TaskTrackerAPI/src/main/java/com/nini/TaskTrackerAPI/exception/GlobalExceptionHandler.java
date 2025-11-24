package com.nini.TaskTrackerAPI.exception;

import com.nini.TaskTrackerAPI.dto.ErrorMessageDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessageDTO> handleNotFound(Exception ex) {
        ErrorMessageDTO errorDTO = ErrorMessageDTO.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ErrorMessageDTO> handleAlreadyExists(Exception ex) {
        ErrorMessageDTO errorDTO = ErrorMessageDTO.builder()
                .status(HttpStatus.CONFLICT)
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDTO);
    }
}

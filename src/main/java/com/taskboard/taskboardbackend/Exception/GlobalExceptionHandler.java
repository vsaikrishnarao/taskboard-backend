package com.taskboard.taskboardbackend.Exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleConflict(UserAlreadyExistsException ex){
        return ResponseEntity.status(409).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<String> handleAuth(InvalidCredentialsException ex){
        return ResponseEntity.status(401).body(ex.getMessage());
    }
}

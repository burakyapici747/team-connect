package com.teamconnect.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.security.access.AccessDeniedException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProfileNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProfileNotFoundException(ProfileNotFoundException ex) {
        return new ResponseEntity<>(
            new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage()),
            HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex) {
        return new ResponseEntity<>(
            new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage()),
            HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        return new ResponseEntity<>(
            new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage()),
            HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException ex) {
        return new ResponseEntity<>(
            new ErrorResponse(HttpStatus.FORBIDDEN.value(), ex.getMessage()),
            HttpStatus.FORBIDDEN
        );
    }

    @ExceptionHandler(UnauthorizedAccessException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedAccessException(UnauthorizedAccessException ex) {
        return new ResponseEntity<>(
            new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), ex.getMessage()),
            HttpStatus.UNAUTHORIZED
        );
    }

    record ErrorResponse(int status, String message) {}
} 
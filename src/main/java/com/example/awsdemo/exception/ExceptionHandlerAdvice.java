package com.example.awsdemo.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<?> handleEmailAlreadyExistsException(HttpServletRequest request, EmailAlreadyExistsException ex) {
        log.error("Email already exists {}", request);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getResponseBody(ex));
    }

    @ExceptionHandler(UserNameAlreadyExistsException.class)
    public ResponseEntity<?> handleUserNameAlreadyExistsException(HttpServletRequest request, UserNameAlreadyExistsException ex) {
        log.error("UserName already exists {}", request);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getResponseBody(ex));
    }

    @ExceptionHandler(UserNotAuthorisedException.class)
    public ResponseEntity<?> handleUserNotAuthorisedException(HttpServletRequest request, UserNotAuthorisedException ex) {
        log.error("User is not authorised {}", request);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(getResponseBody(ex));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(HttpServletRequest request, UserNotFoundException ex) {
        log.error("User not found {}", request);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getResponseBody(ex));
    }

    @ExceptionHandler(PasswordNotMatchException.class)
    public ResponseEntity<?> handlePasswordNotMatchException(HttpServletRequest request, PasswordNotMatchException ex) {
        log.error("Password not match {}", request);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(getResponseBody(ex));
    }

    @ExceptionHandler(QueueAlreadyExistsException.class)
    public ResponseEntity<?> handleQueueAlreadyExistsException(HttpServletRequest request, QueueAlreadyExistsException ex) {
        log.error("Queue Already exists {}", request);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getResponseBody(ex));
    }

    @ExceptionHandler(QueueNotFoundException.class)
    public ResponseEntity<?> handleQueueNotFoundException(HttpServletRequest request, QueueNotFoundException ex) {
        log.error("Queue not found {}", request);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getResponseBody(ex));
    }

    private Object getResponseBody(CustomException ex) {
        return Map.of(
                "errorCode", ex.getErrorCode(),
                "message", ex.getMessage()
        );
    }
}

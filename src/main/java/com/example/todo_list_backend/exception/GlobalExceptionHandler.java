package com.example.todo_list_backend.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.todo_list_backend.dto.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

  // @RestControllerAdvice -> it has methods that globally handle exceptions

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
    String message = ex.getMessage();
    ApiResponse apiResponse = new ApiResponse(message, false);
    return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex) {
    Map<String, String> response = new HashMap<>();
    // lambda function
    ex.getAllErrors().forEach(error -> {
      String fieldName = ((FieldError) error).getField();
      String message = error.getDefaultMessage();
      response.put(fieldName, message);
    });
    return new ResponseEntity<Map<String, String>>(response, HttpStatus.BAD_REQUEST);
  }
}

package com.example.todo_list_backend.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

  @ExceptionHandler(TokenExpiredException.class)
  public ResponseEntity<ApiResponse> handleTokenExpiredException(TokenExpiredException ex) {
    ApiResponse apiResponse = new ApiResponse(ex.getMessage(), false);
    return new ResponseEntity<>(apiResponse, HttpStatus.UNAUTHORIZED);
  }
}

package com.example.todo_list_backend.exception;

public class TokenExpiredException extends RuntimeException {
  public TokenExpiredException(String message) {
    super(message);
  }
}

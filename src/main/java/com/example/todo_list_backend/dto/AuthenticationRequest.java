package com.example.todo_list_backend.dto;

import lombok.Data;

@Data
public class AuthenticationRequest {
  private String email;
  private String password;
}

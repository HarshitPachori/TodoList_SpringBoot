package com.example.todo_list_backend.services;

import com.example.todo_list_backend.dto.SignUpRequest;
import com.example.todo_list_backend.dto.UserDto;

public interface AuthService {

  UserDto createUser(SignUpRequest signUpRequest);
  
}

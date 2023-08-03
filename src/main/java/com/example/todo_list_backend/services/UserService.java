package com.example.todo_list_backend.services;

import com.example.todo_list_backend.dto.UserDto;

public interface UserService {
  UserDto createUser(UserDto userDto);

  UserDto getUserById(Integer userId);
}

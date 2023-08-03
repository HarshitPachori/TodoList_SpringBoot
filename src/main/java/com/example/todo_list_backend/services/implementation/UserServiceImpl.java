package com.example.todo_list_backend.services.implementation;

import org.springframework.stereotype.Service;

import com.example.todo_list_backend.dto.UserDto;
import com.example.todo_list_backend.services.UserService;

@Service
public class UserServiceImpl implements UserService {

  @Override
  public UserDto createUser(UserDto userDto) {

    throw new UnsupportedOperationException("Unimplemented method 'createUser'");
  }

  @Override
  public UserDto getUserById(Integer userId) {

    throw new UnsupportedOperationException("Unimplemented method 'getUserById'");
  }
}

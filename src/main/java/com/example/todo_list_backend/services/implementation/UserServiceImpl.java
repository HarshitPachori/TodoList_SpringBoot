package com.example.todo_list_backend.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todo_list_backend.dto.UserDto;
import com.example.todo_list_backend.models.User;
import com.example.todo_list_backend.repositories.UserRepository;
import com.example.todo_list_backend.services.UserService;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDto createUser(UserDto userDto) {

    throw new UnsupportedOperationException("Unimplemented method 'createUser'");
  }

  @Override
  public UserDto getUserById(Integer userId) {

    throw new UnsupportedOperationException("Unimplemented method 'getUserById'");
  }

  @Override
  public Long getUserIdByUsername(String sessionUsername) {
    User user = userRepository.findFirstByEmail(sessionUsername);
    return user.getUserID();
  }
}

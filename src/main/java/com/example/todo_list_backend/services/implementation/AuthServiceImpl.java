package com.example.todo_list_backend.services.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.todo_list_backend.dto.SignUpRequest;
import com.example.todo_list_backend.dto.UserDto;
import com.example.todo_list_backend.models.User;
import com.example.todo_list_backend.repositories.UserRepository;
import com.example.todo_list_backend.services.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ModelMapper modelMapper;

  @Override
  public UserDto createUser(SignUpRequest signUpRequest) {
    User user = modelMapper.map(signUpRequest, User.class);
    user.setPassword(new BCryptPasswordEncoder().encode(signUpRequest.getPassword()));
    User createdUser = userRepository.save(user);
    UserDto userDto = modelMapper.map(createdUser, UserDto.class);
    return userDto;
  }
}

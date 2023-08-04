package com.example.todo_list_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo_list_backend.dto.CustomMessage;
import com.example.todo_list_backend.dto.SignUpRequest;
import com.example.todo_list_backend.dto.UserDto;
import com.example.todo_list_backend.models.User;
import com.example.todo_list_backend.repositories.UserRepository;
import com.example.todo_list_backend.services.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class SignUpController {

  @Autowired
  private AuthService authService;

  @Autowired
  private UserRepository userRepository;

  @PostMapping("/register")
  public ResponseEntity<?> createUser(@Valid @RequestBody SignUpRequest signUpRequest) {
    User user = userRepository.findFirstByEmail(signUpRequest.getEmail());
    if (user == null) {
      UserDto createdUser = authService.createUser(signUpRequest);
      if (createdUser == null) {
        return new ResponseEntity<>(new CustomMessage("User not created, try again later"), HttpStatus.BAD_REQUEST);
      }
      return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
    return new ResponseEntity<>(new CustomMessage("User already exists"), HttpStatus.BAD_REQUEST);
  }
}

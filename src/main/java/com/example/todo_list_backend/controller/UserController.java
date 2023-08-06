package com.example.todo_list_backend.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo_list_backend.dto.ApiResponse;
import com.example.todo_list_backend.dto.AuthenticationRequest;
import com.example.todo_list_backend.dto.AuthenticationResponse;
import com.example.todo_list_backend.dto.CustomMessage;
import com.example.todo_list_backend.dto.SignUpRequest;
import com.example.todo_list_backend.dto.TodoListDto;
import com.example.todo_list_backend.dto.UserDto;
import com.example.todo_list_backend.models.User;
import com.example.todo_list_backend.repositories.UserRepository;
import com.example.todo_list_backend.services.AuthService;
import com.example.todo_list_backend.services.implementation.UserDetailServiceImpl;
import com.example.todo_list_backend.util.JwtUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Tag(name = "User API", description = "This API contains different User operation endpoints.")
@RestController
@RequestMapping("/api")
public class UserController {

  @Autowired
  private AuthService authService;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserDetailServiceImpl userDetailsService;

  @Autowired
  private JwtUtil jwtUtil;

  @Operation(summary = "Register a User")
  @ApiResponses(value = {
      @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Registered user", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = SignUpRequest.class)) }) })
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
    return new ResponseEntity<>(new ApiResponse("User already exists", false), HttpStatus.BAD_REQUEST);
  }

  @Operation(summary = "Authenticate a user")
  @ApiResponses(value = {
      @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "User authenticated", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = TodoListDto.class)) }),
      @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "User not found", content = @Content) })
  @PostMapping("/login")
  public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest,
      HttpServletResponse response)
      throws BadCredentialsException, IOException, DisabledException, UsernameNotFoundException {
    // disabled exception when there is no user in database with that email password
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
          authenticationRequest.getPassword()));
    } catch (BadCredentialsException e) {
      throw new BadCredentialsException("Incorrect Username or Password");
    } catch (DisabledException e) {
      response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found, register user first");
      return null;
    }
    final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
    final String jwtToken = jwtUtil.generateToken(userDetails.getUsername());
    return new AuthenticationResponse(jwtToken);
  }
}

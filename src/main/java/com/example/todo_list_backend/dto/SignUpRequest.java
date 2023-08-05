package com.example.todo_list_backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignUpRequest {

  @NotEmpty(message = "username can't be empty")
  private String username;

  @NotEmpty(message = "password can't be empty")
  @Size(min = 8, message = "password must be of atleast 8 chracters")
  @Pattern(regexp = "^(?=.*[A-Z]).{8,}$", message = "must contain one Uppercase letter")
  @Pattern(regexp = "^(?=.*[@#$%^&+=]).{8,}$", message = "must contain one Special Character")
  @Pattern(regexp = "^(?=\\S+$).{8,}$", message = "no whitespace allowed")
  private String password;

  @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Email address is not valid")
  @NotEmpty(message = "email can't be empty")
  private String email;

}

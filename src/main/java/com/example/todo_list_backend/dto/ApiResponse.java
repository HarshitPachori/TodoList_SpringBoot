package com.example.todo_list_backend.dto;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class ApiResponse {
  private String messsage;
  private boolean isSuccess;
}

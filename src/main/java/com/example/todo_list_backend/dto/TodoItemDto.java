package com.example.todo_list_backend.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class TodoItemDto {
  private long todoItemId;

  @NotEmpty(message = "Please add Title")
  private String title;

  @NotEmpty(message = "Please add Description")
  private String description;

  private boolean completed;
  
  private LocalDate dueDate;
}

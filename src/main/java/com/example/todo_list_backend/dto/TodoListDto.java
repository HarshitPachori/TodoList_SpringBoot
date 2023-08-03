package com.example.todo_list_backend.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class TodoListDto {
  private long todoListId;
  
  @NotEmpty(message = "Please add TodoList Name")
  private String todoListName;
}

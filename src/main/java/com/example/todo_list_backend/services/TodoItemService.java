package com.example.todo_list_backend.services;

import java.util.List;

import com.example.todo_list_backend.dto.TodoItemDto;

public interface TodoItemService {
  TodoItemDto createTodoItem(TodoItemDto todoItemDto, Long todoListId);

  TodoItemDto updateTodoItem(TodoItemDto todoItemDto, Long todoItemId);

  TodoItemDto getTodoItemById(Long todoItemId);

  List<TodoItemDto> getAllTodoItemByTodoListId(Long todoListId);

  void deleteTodoItemById(Long todoItemId);

  TodoItemDto markAsComplete(Long todoItemId, boolean complete);

  boolean isUserAuthorizedForTodoList(Long todoItemId, String username);

}

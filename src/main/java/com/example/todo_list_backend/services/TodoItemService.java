package com.example.todo_list_backend.services;

import java.util.List;

import com.example.todo_list_backend.dto.TodoItemDto;

public interface TodoItemService {
  TodoItemDto createTodoItem(TodoItemDto todoItemDto);

  TodoItemDto updateTodoItem(TodoItemDto todoItemDto, Integer todoItemId);

  TodoItemDto getTodoItemById(Integer todoItemId);

  List<TodoItemDto> getAllTodoItemByTodoListId(Integer todoListId);

  void deleteTodoItemById(Integer todoItemId);
}

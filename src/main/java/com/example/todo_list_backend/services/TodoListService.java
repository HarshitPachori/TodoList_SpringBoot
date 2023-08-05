package com.example.todo_list_backend.services;

import java.util.List;

import com.example.todo_list_backend.dto.TodoListDto;

public interface TodoListService {
  TodoListDto createTodoList(TodoListDto todoListDto,Long userId);

  TodoListDto updateTodoList(TodoListDto todoListDto, Long todoListId);

  TodoListDto getTodoListById(Long todoListId);

  List<TodoListDto> getAllTodoListByUser(Long userId);

  void deleteTodoListById(Long todoListId);
}

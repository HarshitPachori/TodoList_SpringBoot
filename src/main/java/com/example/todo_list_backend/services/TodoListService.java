package com.example.todo_list_backend.services;

import java.util.List;

import com.example.todo_list_backend.dto.TodoListDto;

public interface TodoListService {
  TodoListDto createTodoList(TodoListDto todoListDto);

  TodoListDto updateTodoList(TodoListDto todoListDto, Integer todoListId);

  TodoListDto getTodoListById(Integer todoListId);

  List<TodoListDto> getAllTodoListByUser(Integer userId);

  void deleteTodoListById(Integer todoListId);
}

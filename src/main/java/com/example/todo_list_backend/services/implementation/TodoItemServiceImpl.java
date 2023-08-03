package com.example.todo_list_backend.services.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.todo_list_backend.dto.TodoItemDto;
import com.example.todo_list_backend.services.TodoItemService;

@Service
public class TodoItemServiceImpl implements TodoItemService {

  @Override
  public TodoItemDto createTodoItem(TodoItemDto todoItemDto) {

    throw new UnsupportedOperationException("Unimplemented method 'createTodoItem'");
  }

  @Override
  public TodoItemDto updateTodoItem(TodoItemDto todoItemDto, Integer todoItemId) {

    throw new UnsupportedOperationException("Unimplemented method 'updateTodoItem'");
  }

  @Override
  public TodoItemDto getTodoItemById(Integer todoItemId) {

    throw new UnsupportedOperationException("Unimplemented method 'getTodoItemById'");
  }

  @Override
  public List<TodoItemDto> getAllTodoItemByTodoListId(Integer todoListId) {

    throw new UnsupportedOperationException("Unimplemented method 'getAllTodoItemByTodoListId'");
  }

  @Override
  public void deleteTodoItemById(Integer todoItemId) {

    throw new UnsupportedOperationException("Unimplemented method 'deleteTodoItemById'");
  }

}

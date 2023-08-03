package com.example.todo_list_backend.services.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.todo_list_backend.dto.TodoListDto;
import com.example.todo_list_backend.services.TodoListService;

@Service
public class TodoListServiceImpl implements TodoListService{

  @Override
  public TodoListDto createTodoList(TodoListDto todoListDto) {
    
    throw new UnsupportedOperationException("Unimplemented method 'createTodoList'");
  }

  @Override
  public TodoListDto updateTodoList(TodoListDto todoListDto, Integer todoListId) {
    
    throw new UnsupportedOperationException("Unimplemented method 'updateTodoList'");
  }

  @Override
  public TodoListDto getTodoListById(Integer todoListId) {
    
    throw new UnsupportedOperationException("Unimplemented method 'getTodoListById'");
  }

  @Override
  public List<TodoListDto> getAllTodoListByUser(Integer userId) {
    
    throw new UnsupportedOperationException("Unimplemented method 'getAllTodoListByUser'");
  }

  @Override
  public void deleteTodoListById(Integer todoListId) {
    
    throw new UnsupportedOperationException("Unimplemented method 'deleteTodoListById'");
  }
  
}

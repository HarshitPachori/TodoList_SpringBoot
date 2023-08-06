package com.example.todo_list_backend.services.implementation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todo_list_backend.dto.TodoItemDto;
import com.example.todo_list_backend.exception.ResourceNotFoundException;
import com.example.todo_list_backend.models.TodoItem;
import com.example.todo_list_backend.models.TodoList;
import com.example.todo_list_backend.repositories.TodoItemRepository;
import com.example.todo_list_backend.repositories.TodoListRepository;
import com.example.todo_list_backend.repositories.UserRepository;
import com.example.todo_list_backend.services.TodoItemService;

@Service
public class TodoItemServiceImpl implements TodoItemService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private TodoListRepository todoListRepository;

  @Autowired
  private TodoItemRepository todoItemRepository;

  @Override
  public TodoItemDto createTodoItem(TodoItemDto todoItemDto, Long todoListId) {
    TodoList todoList = todoListRepository.findById(todoListId)
        .orElseThrow(() -> new ResourceNotFoundException("TodoList", "todoListId", todoListId));
    TodoItem todoItem = modelMapper.map(todoItemDto, TodoItem.class);
    todoItem.setCompleted(false);
    todoItem.setDueDate(LocalDate.now());
    todoItem.setTodoList(todoList);
    TodoItem save = todoItemRepository.save(todoItem);
    return modelMapper.map(save, TodoItemDto.class);
  }



  @Override
  public TodoItemDto updateTodoItem(TodoItemDto todoItemDto, Long todoItemId) {
    TodoItem todoItem = todoItemRepository.findById(todoItemId)
        .orElseThrow(() -> new ResourceNotFoundException("TodoItem", "todoItemId", todoItemId));
    todoItem.setTitle(todoItemDto.getTitle());
    todoItem.setDescription(todoItemDto.getDescription());
    todoItem.setDueDate(todoItemDto.getDueDate());
    todoItem.setCompleted(todoItemDto.isCompleted());
    TodoItem updatedTodoItem = todoItemRepository.save(todoItem);
    return modelMapper.map(updatedTodoItem, TodoItemDto.class);
  }

  @Override
  public TodoItemDto getTodoItemById(Long todoItemId) {
    TodoItem todoItem = todoItemRepository.findById(todoItemId)
        .orElseThrow(() -> new ResourceNotFoundException("TodoItem", "todoItemId", todoItemId));
    return modelMapper.map(todoItem, TodoItemDto.class);
  }

  @Override
  public List<TodoItemDto> getAllTodoItemByTodoListId(Long todoListId) {
    TodoList todoList = todoListRepository.findById(todoListId)
        .orElseThrow(() -> new ResourceNotFoundException("TodoList", "todoListId", todoListId));
    List<TodoItem> todoItems = todoItemRepository.findByTodoList(todoList);
    List<TodoItemDto> todoItemsDtos = todoItems.stream().map((todoItem) -> modelMapper.map(todoItem, TodoItemDto.class))
        .collect(Collectors.toList());
    return todoItemsDtos;
  }

  @Override
  public void deleteTodoItemById(Long todoItemId) {
    TodoItem todoItem = todoItemRepository.findById(todoItemId)
        .orElseThrow(() -> new ResourceNotFoundException("TodoItem", "todoItemId", todoItemId));
    todoItemRepository.delete(todoItem);
  }

  @Override
  public TodoItemDto markAsComplete(Long todoItemId, boolean complete) {
    TodoItem todoItem = todoItemRepository.findById(todoItemId)
        .orElseThrow(() -> new ResourceNotFoundException("TodoItem", "todoItemId", todoItemId));
    todoItem.setCompleted(complete);
    TodoItem saveTodoItem = todoItemRepository.save(todoItem);
    return this.modelMapper.map(saveTodoItem, TodoItemDto.class);
  }

  @Override
  public boolean isUserAuthorizedForTodoList(Long todoListId, String username) {
    TodoList todoList = todoListRepository.findById(todoListId)
        .orElseThrow(() -> new ResourceNotFoundException("TodoList", "todoListId", todoListId));
    if (todoList != null && todoList.getUser().getEmail().equals(username)) {
      return true;
    } else {
      return false;
    }
  }

}

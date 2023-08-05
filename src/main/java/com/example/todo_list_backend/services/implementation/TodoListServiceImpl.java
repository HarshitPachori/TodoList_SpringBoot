package com.example.todo_list_backend.services.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todo_list_backend.dto.TodoListDto;
import com.example.todo_list_backend.exception.ResourceNotFoundException;
import com.example.todo_list_backend.models.TodoList;
import com.example.todo_list_backend.models.User;
import com.example.todo_list_backend.repositories.TodoListRepository;
import com.example.todo_list_backend.repositories.UserRepository;
import com.example.todo_list_backend.services.TodoListService;

@Service
public class TodoListServiceImpl implements TodoListService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private TodoListRepository todoListRepository;

  @Override
  public TodoListDto createTodoList(TodoListDto todoListDto, Long userId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
    TodoList todoList = modelMapper.map(todoListDto, TodoList.class);
    todoList.setUser(user);
    TodoList createTodoList = todoListRepository.save(todoList);
    return modelMapper.map(createTodoList, TodoListDto.class);
  }

  @Override
  public TodoListDto updateTodoList(TodoListDto todoListDto, Long todoListId) {
    TodoList todoList = todoListRepository.findById(todoListId)
        .orElseThrow(() -> new ResourceNotFoundException("TodoList", "todoListId", todoListId));
    todoList.setTodoListName(todoListDto.getTodoListName());
    TodoList updatedTodoList = todoListRepository.save(todoList);
    return modelMapper.map(updatedTodoList, TodoListDto.class);
  }

  @Override
  public TodoListDto getTodoListById(Long todoListId) {
    TodoList todoList = todoListRepository.findById(todoListId)
        .orElseThrow(() -> new ResourceNotFoundException("TodoList", "todoListId", todoListId));
    return modelMapper.map(todoList, TodoListDto.class);
  }

  @Override
  public List<TodoListDto> getAllTodoListByUser(Long userId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
    List<TodoList> todoLists = todoListRepository.findByUser(user);
    List<TodoListDto> todoListDtos = todoLists.stream().map((todoList) -> modelMapper.map(todoList, TodoListDto.class))
        .collect(Collectors.toList());
    return todoListDtos;
  }

  @Override
  public void deleteTodoListById(Long todoListId) {
    TodoList todoList = todoListRepository.findById(todoListId)
        .orElseThrow(() -> new ResourceNotFoundException("TodoList", "todoListId", todoListId));
    todoListRepository.delete(todoList);
  }

}

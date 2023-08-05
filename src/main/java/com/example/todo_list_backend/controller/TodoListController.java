
package com.example.todo_list_backend.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo_list_backend.dto.ApiResponse;
import com.example.todo_list_backend.dto.TodoListDto;
import com.example.todo_list_backend.services.TodoListService;
import com.example.todo_list_backend.services.UserService;

@RestController
@RequestMapping("/api")
public class TodoListController {

  @Autowired
  private TodoListService todoListService;

  @Autowired
  private UserService userService;

  // POST create todolist
  @PostMapping("/users/{userId}/todolist")
  public ResponseEntity<?> createTodoList(@RequestBody TodoListDto todoListDto,
      @PathVariable("userId") Long userId, Principal principal) {
    String sessionUsername = principal.getName();
    if (!userId.equals(userService.getUserIdByUsername(sessionUsername))) {
      return new ResponseEntity<>("You are not Authorized to do this ...", HttpStatus.FORBIDDEN);
    }
    TodoListDto createTodoList = todoListService.createTodoList(todoListDto, userId);
    return new ResponseEntity<TodoListDto>(createTodoList, HttpStatus.CREATED);
  }

  // GET get todolist by id
  @GetMapping("/users/todolist/{todoListId}")
  public ResponseEntity<TodoListDto> getTodoListById(@PathVariable("todoListId") Long todoListId) {
    TodoListDto todoList = todoListService.getTodoListById(todoListId);
    return new ResponseEntity<TodoListDto>(todoList, HttpStatus.OK);
  }

  // GET get todoList by user
  @GetMapping("/users/{userId}/todolist")
  public ResponseEntity<List<TodoListDto>> getAllTodoListByUser(@PathVariable("userId") long userId) {
    List<TodoListDto> allTodoListByUser = todoListService.getAllTodoListByUser(userId);
    return new ResponseEntity<List<TodoListDto>>(allTodoListByUser, HttpStatus.OK);
  }

  // DELETE delete a todoList
  @DeleteMapping("/todolist/{todoListId}")
  public ResponseEntity<ApiResponse> deleteTodoList(@PathVariable("todoListId") Long todoListId) {
    todoListService.deleteTodoListById(todoListId);
    return new ResponseEntity<ApiResponse>(new ApiResponse("TodoList deleted Successfully", true), HttpStatus.OK);
  }
}

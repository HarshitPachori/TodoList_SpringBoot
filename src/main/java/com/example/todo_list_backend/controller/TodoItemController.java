package com.example.todo_list_backend.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo_list_backend.dto.ApiResponse;
import com.example.todo_list_backend.dto.TodoItemDto;
import com.example.todo_list_backend.services.TodoItemService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/api/users/todolist")
public class TodoItemController {

  @Autowired
  private TodoItemService todoItemService;

  @PostMapping("/{todoListId}/todoitem")
  public ResponseEntity<?> createTodoItem(@PathVariable("todoListId") Long todoListId,
      @RequestBody TodoItemDto todoItemDto,
      Principal principal) {
    if (!todoItemService.isUserAuthorizedForTodoList(todoListId,
        principal.getName())) {
      return new ResponseEntity<>(new ApiResponse("User not Authorized for that TodoList", false),
          HttpStatus.FORBIDDEN);
    }
    TodoItemDto createTodoItem = todoItemService.createTodoItem(todoItemDto, todoListId);
    return new ResponseEntity<>(createTodoItem, HttpStatus.CREATED);
  }

  @Operation(summary = "Get TodoItem by Id")
  @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", content = {
      @Content(schema = @Schema(implementation = TodoItemDto.class), mediaType = "application/json")
  })
  @GetMapping("/{todoListId}/todoitem/{todoItemId}")
  public ResponseEntity<?> getTodoItembyId(
      @PathVariable("todoListId") Long todoListId,
      @PathVariable("todoItemId") Long todoItemId,
      Principal principal) {
    // Check if the authenticated user owns the specified todo list
    if (!todoItemService.isUserAuthorizedForTodoList(todoListId,
        principal.getName())) {
      return new ResponseEntity<>(new ApiResponse("User not Authorized for that TodoList", false),
          HttpStatus.FORBIDDEN);
    }
    TodoItemDto todoItem = todoItemService.getTodoItemById(todoItemId);

    return new ResponseEntity<>(todoItem, HttpStatus.OK);
  }

  @GetMapping("/{todoListId}/todoitem")
  public ResponseEntity<?> getAllTodoItemsForTodoList(
      @PathVariable("todoListId") Long todoListId,
      Principal principal) {
    // Check if the authenticated user owns the specified todo list
    if (!todoItemService.isUserAuthorizedForTodoList(todoListId,
        principal.getName())) {
      return new ResponseEntity<>(new ApiResponse("User not Authorized for that TodoList", false),
          HttpStatus.FORBIDDEN);
    }
    List<TodoItemDto> todoItems = todoItemService.getAllTodoItemByTodoListId(todoListId);
    return new ResponseEntity<>(todoItems, HttpStatus.OK);
  }

  @PutMapping("/{todoListId}/todoitem/{todoItemId}")
  public ResponseEntity<?> updateTodoItem(
      @PathVariable("todoListId") Long todoListId,
      @PathVariable("todoItemId") Long todoItemId,
      @RequestBody TodoItemDto todoItemUpdate,
      Principal principal) {
    // Check if the authenticated user owns the specified todo list
    if (!todoItemService.isUserAuthorizedForTodoList(todoListId,
        principal.getName())) {
      return new ResponseEntity<>(new ApiResponse("User not Authorized for that TodoList", false),
          HttpStatus.FORBIDDEN);
    }
    TodoItemDto updatedTodoItem = todoItemService.updateTodoItem(todoItemUpdate,
        todoItemId);
    return new ResponseEntity<>(updatedTodoItem, HttpStatus.OK);
  }

  @PatchMapping("/{todoListId}/todoitem/{todoItemId}")
  public ResponseEntity<?> markTodoItemAsCompleted(
      @PathVariable("todoListId") Long todoListId,
      @PathVariable("todoItemId") Long todoItemId,
      @RequestParam("complete") Boolean complete,
      Principal principal) {
    // Check if the authenticated user owns the specified todo list
    if (!todoItemService.isUserAuthorizedForTodoList(todoListId,
        principal.getName())) {
      return new ResponseEntity<>(new ApiResponse("User not Authorized for that TodoList", false),
          HttpStatus.FORBIDDEN);
    }
    TodoItemDto markedTodoItem = todoItemService.markAsComplete(todoItemId,
        complete);
    return new ResponseEntity<>(markedTodoItem, HttpStatus.OK);
  }

  @DeleteMapping("/{todoListId}/todoitem/{todoItemId}")
  public ResponseEntity<?> deleteTodoItem(
      @PathVariable("todoListId") Long todoListId,
      @PathVariable("todoItemId") Long todoItemId,
      Principal principal) {
    // Check if the authenticated user owns the specified todo list
    if (!todoItemService.isUserAuthorizedForTodoList(todoListId,
        principal.getName())) {
      return new ResponseEntity<>(new ApiResponse("User not Authorized for that TodoList", false),
          HttpStatus.FORBIDDEN);
    }
    todoItemService.deleteTodoItemById(todoItemId);
    return new ResponseEntity<>(new ApiResponse("TodoItem deleted successfully",
        true), HttpStatus.OK);

  }
}

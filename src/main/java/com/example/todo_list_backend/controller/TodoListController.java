
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
import com.example.todo_list_backend.dto.TodoItemDto;
import com.example.todo_list_backend.dto.TodoListDto;
import com.example.todo_list_backend.services.TodoListService;
import com.example.todo_list_backend.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;



@Tag(name = "TodoList API", description = "This API contains different todolist operation endpoints.")
@RestController
@RequestMapping("/api")
public class TodoListController {

  @Autowired
  private TodoListService todoListService;

  @Autowired
  private UserService userService;

  // POST create todolist
    @Operation(summary = "Create a todoList by a specific User")
  @ApiResponses(value = {
      @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Created a TodoList", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = TodoListDto.class)) }),
      @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized user", content = @Content),
      @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "User not found", content = @Content) })
  @PostMapping("/users/{userId}/todolist")
  public ResponseEntity<?> createTodoList(@RequestBody TodoListDto todoListDto,
      @PathVariable("userId") Long userId, Principal principal) {
    String sessionUsername = principal.getName();
    if (!userId.equals(userService.getUserIdByUsername(sessionUsername))) {
      return new ResponseEntity<>(new ApiResponse("User not Authorized for that TodoList", false),HttpStatus.FORBIDDEN);
    }
    TodoListDto createTodoList = todoListService.createTodoList(todoListDto, userId);
    return new ResponseEntity<TodoListDto>(createTodoList, HttpStatus.CREATED);
  }

  // GET get todolist by id
  @Operation(summary = "Get a todoList by todoListId")
  @ApiResponses(value = {
      @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Get a TodoList", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = TodoItemDto.class)) }),
      @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized user", content = @Content),
      @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "TodoList not found", content = @Content) })
  @GetMapping("/users/todolist/{todoListId}")
  public ResponseEntity<TodoListDto> getTodoListById(@PathVariable("todoListId") Long todoListId) {
    TodoListDto todoList = todoListService.getTodoListById(todoListId);
    return new ResponseEntity<TodoListDto>(todoList, HttpStatus.OK);
  }

  // GET get todoList by user
  @Operation(summary = "Get all todoList by specific User")
  @ApiResponses(value = {
      @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Get all TodoList by specific user", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = TodoItemDto.class)) }),
      @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized user", content = @Content),
      @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "User not found", content = @Content) })
  @GetMapping("/users/{userId}/todolist")
  public ResponseEntity<List<TodoListDto>> getAllTodoListByUser(@PathVariable("userId") long userId) {
    List<TodoListDto> allTodoListByUser = todoListService.getAllTodoListByUser(userId);
    return new ResponseEntity<List<TodoListDto>>(allTodoListByUser, HttpStatus.OK);
  }

  // DELETE delete a todoList
  @Operation(summary = "Delete a todoList by todoListId")
  @ApiResponses(value = {
      @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Deleted a TodoList", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = TodoItemDto.class)) }),
      @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized user", content = @Content),
      @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "TodoList not found", content = @Content) })
  @DeleteMapping("/todolist/{todoListId}")
  public ResponseEntity<ApiResponse> deleteTodoList(@PathVariable("todoListId") Long todoListId) {
    todoListService.deleteTodoListById(todoListId);
    return new ResponseEntity<ApiResponse>(new ApiResponse("TodoList deleted Successfully", true), HttpStatus.OK);
  }
}

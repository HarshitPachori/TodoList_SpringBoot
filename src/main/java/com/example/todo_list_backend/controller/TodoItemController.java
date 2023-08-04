package com.example.todo_list_backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo_list_backend.dto.CustomMessage;

@RestController
@RequestMapping("/api/v1")
public class TodoItemController {

  @GetMapping("/hello")
  public ResponseEntity<?> hello() {
    return new ResponseEntity<>(new CustomMessage("Hello World"), HttpStatus.OK);
  }
}

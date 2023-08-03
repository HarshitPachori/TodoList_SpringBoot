package com.example.todo_list_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todo_list_backend.models.TodoList;

public interface TodoListRepository extends JpaRepository<TodoList,Long>{
  
}

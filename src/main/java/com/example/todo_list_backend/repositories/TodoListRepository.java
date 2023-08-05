package com.example.todo_list_backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todo_list_backend.models.TodoList;
import com.example.todo_list_backend.models.User;

public interface TodoListRepository extends JpaRepository<TodoList, Long> {

  List<TodoList> findByUser(User user);

}

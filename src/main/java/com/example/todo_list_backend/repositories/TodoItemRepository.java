package com.example.todo_list_backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todo_list_backend.models.TodoItem;
import com.example.todo_list_backend.models.TodoList;

public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {
List<TodoItem> findByTodoList(TodoList todoList);
}

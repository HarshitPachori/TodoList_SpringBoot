package com.example.todo_list_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todo_list_backend.models.TodoItem;

public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {

}

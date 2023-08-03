package com.example.todo_list_backend.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "todo_item")
@Data
public class TodoItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long todoItemID;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String description;

  private boolean completed;

  private LocalDate dueDate;

  @ManyToOne
  private TodoList todoList;

}

package com.example.todo_list_backend.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "todo_list")
@Data
public class TodoList {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long todoListId;

  @Column(nullable = false)
  private String todoListName;

  @ManyToOne
  private User user;

  @OneToMany(mappedBy = "todoList", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<TodoItem> todoItem;
}

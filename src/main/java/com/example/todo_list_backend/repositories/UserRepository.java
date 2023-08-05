package com.example.todo_list_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todo_list_backend.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

  User findFirstByEmail(String email);


}

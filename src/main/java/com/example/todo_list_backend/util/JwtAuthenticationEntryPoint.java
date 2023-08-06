package com.example.todo_list_backend.util;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
      throws IOException, ServletException {

    // PrintWriter writer = response.getWriter();
    // response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    // writer.println("Access Denied !! " + authException.getMessage());

    response.setContentType("application/json");
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

    PrintWriter writer = response.getWriter();

    writer.println("{\"error\":\"Access Denied !! " + authException.getMessage() + "\"}");
  }

}

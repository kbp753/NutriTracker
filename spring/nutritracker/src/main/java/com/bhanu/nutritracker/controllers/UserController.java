package com.bhanu.nutritracker.controllers;

import com.bhanu.nutritracker.dto.LoginRequest;
import com.bhanu.nutritracker.entity.User;
import com.bhanu.nutritracker.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @GetMapping("/{username}")
    public ResponseEntity<Long> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.findByUsername(username).getUserId());
    }

    @GetMapping("/getCredentials")
    public ResponseEntity<String> getCredentials() {
        return ResponseEntity.ok(userService.getLoggedInUserId());
    }

//    @PostMapping("/login")
//    public void login(@RequestBody LoginRequest loginRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        request.login(loginRequest.getUsername(), loginRequest.getPassword());
//        response.sendRedirect("/home");
//    }
}


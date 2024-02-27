package com.example.blogbackend.Controller;

import com.example.blogbackend.Implementation.CustomUserDetailsService;
import com.example.blogbackend.Modal.User;
import com.example.blogbackend.Repository.UserRepository;
import com.example.blogbackend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.Arrays;
@RestController
@CrossOrigin(origins = "*") // Replace with your frontend URL
public class UserRestController {
    private BCryptPasswordEncoder passwordEncoder;

    private UserService userService;
private final UserRepository customUserDetailsService;
    @Autowired
    public UserRestController(BCryptPasswordEncoder passwordEncoder, UserService userService, UserRepository customUserDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.customUserDetailsService = customUserDetailsService;
    }

    @PostMapping(value = "/login",consumes = "application/json")
    public ResponseEntity<String> loginUser( @RequestBody User user) {
        // Retrieve user from the database based on provided email
        User existingUser = customUserDetailsService.findFirstByEmail(user.getEmail());
        System.out.println(existingUser);
        System.out.println(existingUser);
        // Check if user exists and password matches
        if (existingUser != null && passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
            // Authentication successful
            return ResponseEntity.ok("User authenticated successfully.");
        } else {
            // Authentication failed
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password.");
        }
    }


    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        try {
            userService.saveUser(user);
            return ResponseEntity.ok("User registered successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to register user: " + e.getMessage());
        }
    }
    @GetMapping("/logout")
    public ResponseEntity<String> registerLogout() {
        try {
            return ResponseEntity.ok("User Logout successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to register user: " + e.getMessage());
        }
    }
}

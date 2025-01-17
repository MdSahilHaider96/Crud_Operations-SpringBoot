package com.example.crudOperations.Controller;

import com.example.crudOperations.Modal.User;
import com.example.crudOperations.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// @RestController is a convenience annotation that combines @Controller and @ResponseBody
// It automatically converts the response objects to JSON format
@RestController
@RequestMapping("/api/users") // Base URL for all user-related endpoints
public class UserController {
    // Autowiring the UserService to interact with the business logic
@Autowired
    public UserService userService;
    // Endpoint to create a new user
    @PostMapping
    @RequestMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        // Call the service to save the user and return the created user as the response
        User createdUser = userService.create(user);
        // Return the created user with HTTP status 201 (Created)
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
    // Endpoint to get a list of all users
    @GetMapping
    @RequestMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        // Get all users from the service and return them
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Endpoint to get a specific user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        // Retrieve user by ID from the service
        Optional<User> user = userService.finddById(id);
        // Return 200 OK if user is found, 404 if not found
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    // Endpoint to update an existing user's details
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User userDetails) {
        // Call the service to update the user and return the updated user
        User updatedUser = userService.updateUser(id, userDetails);
        // Return 200 OK if updated, 404 if not found
        return updatedUser != null ? ResponseEntity.ok(updatedUser) : ResponseEntity.notFound().build();
    }
    // Endpoint to delete a user by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        // Call the service to delete the user
        return userService.deleteUser(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}


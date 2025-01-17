package com.example.crudOperations.Service;

import com.example.crudOperations.Modal.User;
import com.example.crudOperations.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
@Autowired
// Autowiring the UserRepository to interact with the database
    private UserRepo userRepo;
    // Method to create a new user
    public User create(User user){
        return userRepo.save(user);
    }
    // Method to fetch all users from the database
    public List<User> getAllUsers() {
        // Return a list of all users
        return userRepo.findAll();
    }
    // Method to fetch a user by their ID
    public Optional<User> finddById (int id) {
        // Return an Optional of User, which can be empty if the user is not found
        return userRepo.findById(id);
    }
    // Method to update a user's details by ID
    public User updateUser(int id, User userDetails) {
        // Check if a user with the given ID exists
        Optional<User> userOptional = userRepo.findById(id);
        if (userOptional.isPresent()) {
            // Get the existing user
            User user = userOptional.get();
            // Update the fields with new values
            user.setFirstName(userDetails.getFirstName());
            user.setLastName(userDetails.getLastName());
            user.setEmail(userDetails.getEmail());
            // Save the updated user
            return userRepo.save(user);
        }
        return null; // Return null if the user was not found
    }
    // Method to delete a user by their ID
    public boolean deleteUser(int id) {
        // Check if the user exists
        Optional<User> userOptional = userRepo.findById(id);
        if (userOptional.isPresent()) {
            // Delete the user from the database
            userRepo.delete(userOptional.get());
            return true; // Return true if the deletion was successful
        }
        return false; // Return false if the user was not found
    }
}

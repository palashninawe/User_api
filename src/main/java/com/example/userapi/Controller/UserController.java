package com.example.userapi.Controller;

import java.util.Optional;

import jakarta.validation.Valid;
import com.example.userapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.userapi.Entity.User;
import com.example.userapi.Service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	 @Autowired
	    private UserService userService;
	 
	 @Autowired
	    private UserRepository userRepository;
	 
	 @GetMapping("/{id}")
	    public ResponseEntity<User> getUserById(@PathVariable Long id) {
	        Optional<User> user = userRepository.findById(id);

	        return user.map(ResponseEntity::ok)
	                   .orElseGet(() -> ResponseEntity.notFound().build());
	    }

	    @PostMapping("/register")
	    public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {

	        if (userService.isUsernameTaken(user.getUsername())) {
	            return ResponseEntity.badRequest().body("Username is already taken");
	        }

	        if (userService.isEmailTaken(user.getEmail())) {
	            return ResponseEntity.badRequest().body("Email is already registered");
	        }

	        User savedUser = userService.saveUser(user);

	        return ResponseEntity.ok("User registered successfully with ID: " + savedUser.getId());
	    }
}

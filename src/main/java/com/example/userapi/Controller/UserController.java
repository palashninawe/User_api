package com.example.userapi.Controller;

//import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.userapi.Entity.User;
import com.example.userapi.Service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	 @Autowired
	    private UserService userService;

	    @PostMapping("/register")
	    public ResponseEntity<?> registerUser(@Validated @RequestBody User user) {

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

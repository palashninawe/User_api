package com.example.userapi.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.userapi.Service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	
	@Autowired
    private AuthService authService;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
	    String email = loginRequest.get("email");
	    String password = loginRequest.get("password");

	    String token = authService.login(email, password);

	    if (token != null) {
	        return ResponseEntity.ok(Map.of("token", token));
	    } else {
	        return ResponseEntity.status(401).body("Invalid credentials");
	    }
	}
}

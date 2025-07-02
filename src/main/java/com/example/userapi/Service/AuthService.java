package com.example.userapi.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.userapi.Config.JwtUtil;
import com.example.userapi.Entity.User;
import com.example.userapi.repository.UserStoredProcedureRepository;

@Service
public class AuthService {

	 	@Autowired
	    private UserStoredProcedureRepository userStoredProcedureRepository;

	    @Autowired
	    private PasswordEncoder passwordEncoder;
	    
	    @Autowired
	    private JwtUtil jwtUtil;

	    public String login(String email, String password) {
	        User user = userStoredProcedureRepository.getUserByEmail(email); // or userRepository.findByEmail

	        if (user == null) {
	            System.out.println("❌ User not found");
	            return null;
	        }

	        boolean match = passwordEncoder.matches(password, user.getPassword());

	        System.out.println("👉 Password match result: " + match);
	        System.out.println("👉 Provided: " + password);
	        System.out.println("👉 Stored: " + user.getPassword());

	        if (match) {
	            return jwtUtil.generateToken(email);
	        } else {
	            return null; // 🔴 This causes "Invalid credentials"
	        }
	    }

	   
}

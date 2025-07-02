package com.example.userapi.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.userapi.Entity.User;
import com.example.userapi.repository.UserRepository;


@Service
public class UserService {

	 @Autowired
	    private UserRepository userRepository;
	 
	 @Autowired
	 private PasswordEncoder passwordEncoder;

	 public User saveUser(User user) {
	     user.setPassword(passwordEncoder.encode(user.getPassword())); // ✅ hash password
	     return userRepository.save(user);
	 }

	    public boolean isUsernameTaken(String username) {
	        return userRepository.findByUsername(username).isPresent();
	    }

	    public boolean isEmailTaken(String email) {
	        return userRepository.findByEmail(email).isPresent();
	    }

	    public User saveUser1(User user) {
	        return userRepository.save(user);
	    }
}

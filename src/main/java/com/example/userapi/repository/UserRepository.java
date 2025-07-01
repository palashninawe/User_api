package com.example.userapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.userapi.Entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

	 Optional<User> findByUsername(String username);
	    Optional<User> findByEmail(String email);
}

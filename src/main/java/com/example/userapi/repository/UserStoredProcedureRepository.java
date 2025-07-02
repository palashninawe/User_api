package com.example.userapi.repository;

import org.springframework.stereotype.Repository;

import com.example.userapi.Entity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;

@Repository
public class UserStoredProcedureRepository {

	 @PersistenceContext
	    private EntityManager entityManager;

	    public User getUserByEmail(String email) {
	        StoredProcedureQuery query = entityManager
	                .createStoredProcedureQuery("GetUserByEmail", User.class)
	                .registerStoredProcedureParameter("userEmail", String.class, ParameterMode.IN)
	                .setParameter("userEmail", email);

	        try {
	            return (User) query.getSingleResult();
	        } catch (Exception e) {
	            return null;
	        }
	    }
}

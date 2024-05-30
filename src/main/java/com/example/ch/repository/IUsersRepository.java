package com.example.ch.repository;

import com.example.ch.model.Users;

public interface IUsersRepository {

	void addAcount (Users addAcount);
	
	int countUserIdRegistered(String userId);
	
	int countEmailRegistered(String email);
	
	Users findByUserId(String userId); 
	
	Users findByEmail(String email);
}

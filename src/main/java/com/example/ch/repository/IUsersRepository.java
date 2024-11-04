package com.example.ch.repository;

import java.util.List;

import com.example.ch.model.Users;

public interface IUsersRepository {

	void addAcount(Users addAcount);
	
	int countUserIdRegistered(String userId);
	
	int countEmailRegistered(String email);
	
	Users findByUserId(String userId); 
	
	Users findByEmail(String email);
	
	List<Users> getUserNameListToUserInfoNull(List<String> userIdList);
	
	String getUserName(String userId);
	
	void updateAcount(Users updateAcount, String userId);

}
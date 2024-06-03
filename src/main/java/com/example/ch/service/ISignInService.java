package com.example.ch.service;

import com.example.ch.model.Users;
import com.example.ch.response.SignInResponse;

public interface ISignInService {
	
	SignInResponse signIn(Users signInAcount);
	
	boolean existingCheck(String userId,String email);
	
}

package com.example.ch.service;

import com.example.ch.model.Users;
import com.example.ch.response.SignUpResponse;

public interface ISignUpService {

	SignUpResponse addAcount(Users addcAcount);
	
	boolean registeredCheck(String userId,String email);
	
}

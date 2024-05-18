package com.example.ch.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ch.model.Users;
import com.example.ch.repository.IUsersRepository;
import com.example.ch.response.SignUpResponse;
@Service
public class SignUpService implements ISignUpService {
	@Autowired
	private IUsersRepository iUsersRepository;
	@Autowired
	SignUpResponse signUpResponse = new SignUpResponse();
	// アカウント追加機能
	@Override
	public SignUpResponse addAcount(Users addAcount) {
		System.out.println("SignUpResponse.addAcount()呼び出し");
		Date CreatTime = new Date();
		addAcount.setId(1);
		addAcount.setCreatedAt(CreatTime);
			if(iUsersRepository.addAcount(addAcount)) {
				signUpResponse = addResponse(0,0,null);
			}else {
				signUpResponse = addResponse(0,1,null);
			}
		return  signUpResponse;
	}
	
	// 処理結果追加
	public SignUpResponse addResponse(int processResult,int httpStatusCd,String errMessage) {
		System.out.println("SignUpResponse.addResponse()呼び出し");
		signUpResponse.setProcessResult(processResult);
		signUpResponse.setHttpStatusCd(httpStatusCd);
		signUpResponse.setErrMessage(errMessage);
		return signUpResponse;
	}
		
}

package com.example.ch.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ch.common.ChUtil;
import com.example.ch.model.Users;
import com.example.ch.repository.IUsersRepository;
import com.example.ch.response.SignUpResponse;
@Service
public class SignUpService implements ISignUpService {
	@Autowired
	private IUsersRepository iUsersRepository;
	@Autowired
	SignUpResponse signUpResponse = new SignUpResponse();
	@Autowired
	ChUtil chUtil;
	
	// アカウント追加機能
	@Override
	public SignUpResponse addAcount(Users addAcount) {
		System.out.println("SignUpResponse.addAcount()呼び出し");
		Date CreatTime = new Date();
		addAcount.setCreatedAt(CreatTime);
		try {
			if(registeredCheck(addAcount.getUserId(),addAcount.getEmail())) {
				iUsersRepository.addAcount(addAcount);
				signUpResponse = addResponse(chUtil.SUCCESS,0,null);
			}else {
				signUpResponse = addResponse(chUtil.FAILURE_2,0,chUtil.ERROR_REGISTERED);
			}
		}catch(Exception e){
			System.out.println(e);
			signUpResponse = addResponse(chUtil.FAILURE_1,0,chUtil.ERROR);
		}
		return  signUpResponse;
	}
	
	// 登録済みチェック
	public boolean registeredCheck(String userId,String email){
		boolean result = false;
		int countUserId = iUsersRepository.countUserIdRegistered(userId);
		int countEmail = iUsersRepository.countEmailRegistered(email);
		if(countUserId == 0 && countEmail == 0) {
			result =  true;
		}
		return result;
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

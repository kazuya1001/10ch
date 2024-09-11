package com.example.ch.service;

import java.security.MessageDigest;
import java.util.Date;
import java.util.HexFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ch.common.ChUtil;
import com.example.ch.model.Users;
import com.example.ch.repository.IUserRepository;
import com.example.ch.response.SignUpResponse;
@Service
public class SignUpService implements ISignUpService {
	@Autowired
	private IUserRepository iUserRepository;
	@Autowired
	SignUpResponse signUpResponse = new SignUpResponse();
	@Autowired
	ChUtil chUtil;
	
	// アカウント追加機能
	@Override
	public SignUpResponse addAcount(Users addAcount) {
		System.out.println("SignUpResponse.addAcount()呼び出し");
		String userId = addAcount.getUserId();
		String email = addAcount.getEmail();
		String pass = addAcount.getPasswordHash();
		try {
			// 登録済みチェック
			if (registeredCheck(userId,email)) {
				System.out.println("登録済みチェックOK");
				Date CreatTime = new Date();
				addAcount.setUpdateAt(CreatTime);
				// パスワードハッシュ化
				MessageDigest sha256 = MessageDigest.getInstance("sha-256");
				byte[] sha256Byte = sha256.digest(pass.getBytes());
				HexFormat hex = HexFormat.of().withLowerCase();
				String hashPass = hex.formatHex(sha256Byte);
				addAcount.setPasswordHash(hashPass);
				// アカウント追加処理
				iUserRepository.addAcount(addAcount);
				signUpResponse = addResponse(chUtil.SUCCESS,0,null);
			} else {
				System.out.println("登録済みチェックNG");
				signUpResponse = addResponse(chUtil.FAILURE_2,0,chUtil.ERROR_REGISTERED);
			}
		} catch(Exception e){
			System.out.println(e);
			signUpResponse = addResponse(chUtil.FAILURE_1,0,chUtil.ERROR_DB);
		}
		return  signUpResponse;
	}
	
	// 登録済みチェック
	@Override
	public boolean registeredCheck(String userId,String email) {
		System.out.println("SignUpResponse.registeredCheck()呼び出し");
		boolean result = false;
		int countUserId = iUserRepository.countUserIdRegistered(userId);
		int countEmail = iUserRepository.countEmailRegistered(email);
		if(countUserId == 0 && countEmail == 0) {
			result =  true;
		}
		return result;
	}
	
	// 処理結果追加
	private SignUpResponse addResponse(int processResult,int httpStatusCd,String errMessage) {
		System.out.println("SignUpResponse.addResponse()呼び出し");
		signUpResponse.setProcessResult(processResult);
		signUpResponse.setHttpStatusCd(httpStatusCd);
		signUpResponse.setErrMessage(errMessage);
		return signUpResponse;
	}
}

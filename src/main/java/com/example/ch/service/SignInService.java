package com.example.ch.service;

import java.security.MessageDigest;
import java.util.HexFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ch.common.ChUtil;
import com.example.ch.model.Users;
import com.example.ch.repository.IUsersRepository;
import com.example.ch.response.SignInResponse;

@Service
public class SignInService implements ISignInService {
	@Autowired
	private IUsersRepository iUsersRepository;
	@Autowired
	SignInResponse signInResponse = new SignInResponse();
	@Autowired
	ChUtil chUtil;
	
	// サインイン機能
	@Override
	public SignInResponse signIn(Users signInUser) {
		System.out.println("SignUpResponse.signIn()呼び出し");
		Users dbUser = new Users();
		String userId = signInUser.getUserId();
		String pass = signInUser.getPasswordHash();
		String email = signInUser.getEmail();
		try {
			//　ユーザID、email存在チェック
			if (existingCheck(userId,email)) {
				System.out.println("存在チェックOK");
				// 入力された情報を元にユーザ情報を取得
				if (!(userId.isEmpty())) {
					System.out.println("ユーザIDで登録確認");
					dbUser = iUsersRepository.findByUserId(userId);
				} else if(!(email.isEmpty())) {
					System.out.println("emailで登録確認");
					dbUser = iUsersRepository.findByEmail(email);
				}
				// パスワードハッシュ化
				MessageDigest sha256 = MessageDigest.getInstance("sha-256");
				byte[] sha256Byte = sha256.digest(pass.getBytes());
				HexFormat hex = HexFormat.of().withLowerCase();
				String hashPass = hex.formatHex(sha256Byte);
				String dbPass = dbUser.getPasswordHash();
				
				// パスワードチェック
				if (hashPass.equals(dbPass)){
					System.out.println("パスワードチェックOK");
					signInResponse = addResponse(chUtil.SUCCESS,0,null,dbUser);
				} else {
					System.out.println("パスワードチェックNG");
					signInResponse = addResponse(chUtil.FAILURE_2,0,chUtil.ERROR_PASS_WRONG,dbUser);
				}
			} else {
				System.out.println("存在チェックNG");
				signInResponse = addResponse(chUtil.FAILURE_2,0,chUtil.ERROR_NOT_EXISTS,dbUser);
			}
		} catch (Exception e) {
			System.out.println(e);
			signInResponse = addResponse(chUtil.FAILURE_1,0,chUtil.ERROR_DB,dbUser);
		}
		return signInResponse;
	}
	
	// 存在チェック
	private boolean existingCheck(String userId,String email){
		System.out.println("SignUpResponse.existingCheck()呼び出し");
		boolean result = true;
		int countUserId = iUsersRepository.countUserIdRegistered(userId);
		int countEmail = iUsersRepository.countEmailRegistered(email);
		if (countUserId == 0 && countEmail == 0) {
			result =  false;
		}
		return result;
	}
	
	// 処理結果追加
	private SignInResponse addResponse(int processResult,int httpStatusCd,String errMessage,Users signInedUser) {
		System.out.println("SignUpResponse.addResponse()呼び出し");
		signInResponse.setProcessResult(processResult);
		signInResponse.setHttpStatusCd(httpStatusCd);
		signInResponse.setErrMessage(errMessage);
		signInResponse.setSignInedUser(signInedUser);
		return signInResponse;
	}	
}

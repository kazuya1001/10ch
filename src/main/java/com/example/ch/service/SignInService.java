package com.example.ch.service;

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
		Users signInedUser = new Users();
		String userId = signInUser.getUserId();
		String pass = signInUser.getPasswordHash();
		String email = signInUser.getEmail();
		try {
			//　ユーザID、email存在チェック
			if(existingCheck(userId,email)) {
				System.out.println("存在チェックOK");
				// 入力された情報を元にユーザ情報を取得
				if(!(userId.isEmpty())) {
					System.out.println("ユーザIDで登録確認");
					signInedUser = iUsersRepository.findByUserId(userId);
				}else if(!(email.isEmpty())) {
					System.out.println("emailで登録確認");
					signInedUser = iUsersRepository.findByEmail(email);
				}
				// パスワードチェック
				if (pass.equals(signInedUser.getPasswordHash())){
					System.out.println("パスワードチェックOK");
					signInResponse = addResponse(chUtil.SUCCESS,0,null,signInedUser);
				}else {
					System.out.println("パスワードチェックNG");
					signInResponse = addResponse(chUtil.FAILURE_2,0,chUtil.ERROR_PASS_WRONG,signInedUser);
				}
			}else {
				System.out.println("存在チェックNG");
				signInResponse = addResponse(chUtil.FAILURE_2,0,chUtil.ERROR_NOT_EXISTS,signInedUser);
			}
		}catch(Exception e) {
			System.out.println(e);
			signInResponse = addResponse(chUtil.FAILURE_1,0,chUtil.ERROR_DB,signInedUser);
		}
		return signInResponse;
	}
	
	// 存在チェック
	public boolean existingCheck(String userId,String email){
		System.out.println("SignUpResponse.existingCheck()呼び出し");
		boolean result = true;
		int countUserId = iUsersRepository.countUserIdRegistered(userId);
		int countEmail = iUsersRepository.countEmailRegistered(email);
		if(countUserId == 0 && countEmail == 0) {
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

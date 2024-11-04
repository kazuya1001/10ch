package com.example.ch.service;

import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HexFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ch.common.ChUtil;
import com.example.ch.model.Users;
import com.example.ch.repository.IUsersRepository;
import com.example.ch.response.UserInfoResponse;

import jakarta.servlet.http.HttpSession;

@Service
public class UserInfoService implements IUserInfoService {

	@Autowired
	UserInfoResponse userInfoResponse = new UserInfoResponse();
	@Autowired
	private IUsersRepository iUsersRepository;
	@Autowired
	ChUtil chUtil;
	@Autowired
	ISignUpService iSignUpService;

	public UserInfoResponse updateAcount(Users updateAcount, String nowPass, HttpSession session) {
		System.out.println("UserInfoService.updateAcount()呼び出し");
		String userId = updateAcount.getUserId();
		String newPass = updateAcount.getPasswordHash();
		String email = updateAcount.getEmail();
		Users sessionUser = (Users) session.getAttribute("user");
		String sessionPass = sessionUser.getPasswordHash();
		try {
			//新しいパスワードをハッシュ化
			MessageDigest sha256 = MessageDigest.getInstance("sha-256");
			byte[] sha256Byte = sha256.digest(newPass.getBytes());
			HexFormat hex = HexFormat.of().withLowerCase();
			String hashPass = hex.formatHex(sha256Byte);
			updateAcount.setPasswordHash(hashPass);
			// 現在のパスワードチェック
			// 取得した現在のパスワードをハッシュ化
			sha256Byte = sha256.digest(nowPass.getBytes());
			hex = HexFormat.of().withLowerCase();
			hashPass = hex.formatHex(sha256Byte);
			if (hashPass.equals(sessionPass)) {
				System.out.println("パスワードチェックOK");
				// 登録済みチェック
				if (registeredCheck(userId, email, sessionUser)) {
					System.out.println("登録済みチェックOK");
					// アカウント更新処理
					Date creatTime = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					try {
						Date formattedDate = sdf.parse(sdf.format(creatTime)); 
						updateAcount.setUpdateAt(formattedDate);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					iUsersRepository.updateAcount(updateAcount, sessionUser.getUserId());
					userInfoResponse = addResponse(chUtil.SUCCESS, 0, null);
				} else {
					System.out.println("登録済みチェックNG");
					userInfoResponse = addResponse(chUtil.FAILURE_2, 0, chUtil.ERROR_REGISTERED);
				}
			} else {
				System.out.println("パスワードチェックNG");
				userInfoResponse = addResponse(chUtil.FAILURE_2, 0, chUtil.ERROR_NOW_PASS_WRONG);
			}
		} catch (Exception e) {
			userInfoResponse = addResponse(chUtil.FAILURE_1, 0, chUtil.ERROR_DB);
		}
		return userInfoResponse;
	}

	// 登録済みチェック
	private boolean registeredCheck(String userId, String email, Users sessionUser) {
		System.out.println("UserInfoService.registeredCheck()呼び出し");
		if (userId.equals(sessionUser.getUserId())) {
			return true;
		}
		if (email.equals(sessionUser.getEmail())) {
			return true;
		}
		return iSignUpService.registeredCheck(userId, email);
	}

	// 処理結果追加
	private UserInfoResponse addResponse(int processResult, int httpStatusCd, String errMessage) {
		System.out.println("UserInfoService.addResponse()呼び出し");
		userInfoResponse.setProcessResult(processResult);
		userInfoResponse.setHttpStatusCd(httpStatusCd);
		userInfoResponse.setErrMessage(errMessage);
		return userInfoResponse;
	}
}

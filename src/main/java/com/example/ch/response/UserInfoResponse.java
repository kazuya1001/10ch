package com.example.ch.response;

import org.springframework.stereotype.Component;

import com.example.ch.model.Users;

import lombok.Data;

@Component
@Data
public class UserInfoResponse {
	// 処理結果
	private int processResult;
	// HTTPステータスコード
	private int httpStatusCd;
	// エラーメッセージ
	private String errMessage;
	// ユーザ情報
	private Users userInfo;
}

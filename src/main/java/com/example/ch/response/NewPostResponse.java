package com.example.ch.response;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class NewPostResponse {
	// 処理結果
	private int processResult;
	// HTTPステータスコード
	private int httpStatusCd;
	// エラーメッセージ
	private String errMessage;
}

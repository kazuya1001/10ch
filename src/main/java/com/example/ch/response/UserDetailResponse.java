package com.example.ch.response;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.ch.model.Posts;

import lombok.Data;

@Component
@Data
public class UserDetailResponse {
	// 処理結果
	private int processResult;
	// HTTPステータスコード
	private int httpStatusCd;
	// エラーメッセージ
	private String errMessage;
	// 投稿一覧
	private List<Posts> postRecord;
	// ユーザ名
	private String userName;
}

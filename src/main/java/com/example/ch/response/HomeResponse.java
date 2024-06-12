package com.example.ch.response;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.ch.model.Posts;

import lombok.Data;
@Component
@Data
public class HomeResponse {
	// 処理結果
	private int processResult;
	// HTTPステータスコード
	private int httpStatusCd;
	// エラーメッセージ
	private String errMessage;
	// 投稿情報
	private List<Posts> postRecord;
}

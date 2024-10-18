package com.example.ch.response;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.ch.model.Comments;

import lombok.Data;
@Component
@Data
public class CommentResponse {
	// 処理結果
	private int processResult;
	// HTTPステータスコード
	private int httpStatusCd;
	// エラーメッセージ
	private String errMessage;
	// コメント内容
	private Comments comment;
	// コメント一覧
	private List<Comments> commentList;
}

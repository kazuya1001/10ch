package com.example.ch.response;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.ch.model.Comments;
import com.example.ch.model.Posts;

import lombok.Data;

@Component
@Data
public class PostDetailResponse {

	// 処理結果
	private int processResult;
	// HTTPステータスコード
	private int httpStatusCd;
	// エラーメッセージ
	private String errMessage;
	// 投稿内容
	private Posts post;
	// 投稿者名
	private String userName;
	// コメント一覧
	private List<Comments> commentList;
	// コメント投稿者一覧
	private List<String> userNameListByPostComment;
}

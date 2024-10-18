package com.example.ch.service;

import com.example.ch.response.CommentResponse;
import com.example.ch.response.PostDetailResponse;

public interface IPostDetailService {

	/**
	 * 投稿情報取得
	 * @param postId ポストID
	 * @return postDetailResponse 投稿詳細処理結果
	 */
	PostDetailResponse getTargetPost(String postId);
	
	/**
	 * コメント登録
	 * @param content 内容
	 * @param postId ポストID
	 * @param userId ユーザID
	 * @return commentResponse 処理結果
	 */
	CommentResponse postComment(String content,String postId, String userId);
}

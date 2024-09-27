package com.example.ch.service;

import com.example.ch.response.PostDetailResponse;

public interface IPostDetailService {

	/**
	 * 対象投稿取得
	 * @param postId ポストID
	 * @return postDetailResponse 投稿詳細処理結果
	 */
	PostDetailResponse getTargetPost(String postId);
}

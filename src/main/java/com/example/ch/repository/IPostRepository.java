package com.example.ch.repository;

import java.util.List;

import com.example.ch.model.Posts;

public interface IPostRepository {
	
    /**
     * 新規投稿(新規投稿画面)
     * @param newPost 新規投稿
     */
	void createPost(Posts newPost);
	
    /**
     * ポストID取得(新規投稿画面)
     * @return ポストID一覧
     */
	List<String> getAllPostId();
	
    /**
     * 最新5投稿取得(ホーム画面)
     * @return 投稿一覧
     */
	List<Posts> getHomePostListToUserInfoNull();
	
    /**
     * 全投稿取得(ホーム画面)
     * @return 投稿一覧
     */
	List<Posts> getHomePostList();
	
    /**
     * 投稿一覧取得(ユーザ詳細画面)
     * @param userId ユーザID
     * @return 投稿一覧
     */
	List<Posts> getPostListByUserId(String userId);
	
    /**
     * 対象投稿取得(投稿詳細画面)
     * @param postId ポストID
     * @return 投稿内容
     */
	Posts getTargetPost(String postId);
}

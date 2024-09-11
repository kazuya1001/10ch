package com.example.ch.repository;

import java.util.List;

import com.example.ch.model.Posts;

public interface IPostRepository {
	
	void createPost(Posts newPost);
	
	List<String> getAllPostId();
	
	List<Posts> getHomePost();
	
	List<Posts> getPostListByUserId(String userId);
}

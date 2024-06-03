package com.example.ch.repository;

import com.example.ch.model.Posts;

public interface INewPostRepository {

	boolean existsByPostId(int postId);
	
	void createPost(Posts newPost);
}

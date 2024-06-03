package com.example.ch.service;

import com.example.ch.model.Posts;
import com.example.ch.response.NewPostResponse;

public interface INewPostService {

	NewPostResponse createPost(Posts newPost);
	
}

package com.example.ch.service;

import com.example.ch.response.NewPostResponse;

public interface INewPostService {

	NewPostResponse createPost(String title, String content, String userId);
	
}

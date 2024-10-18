package com.example.ch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.ch.response.NewPostResponse;
import com.example.ch.service.INewPostService;

@Controller
public class NewPostController {
	
	@Autowired
	private INewPostService iNewPostService;
	@Autowired
	NewPostResponse newPostResponse = new NewPostResponse();

	
	// 新規投稿画面表示
	@GetMapping("/posts/new")
	public String init() {
		System.out.println("NewPostController.init()呼び出し");
		return "new-post";
	}
	
	// 投稿機能
	@PostMapping("/api/newPost")
	@ResponseBody
	public NewPostResponse newPost(@RequestParam String title, String content, String userId) {
		System.out.println("NewPostController.newPost()呼び出し");
		// 新規投稿処理
		newPostResponse = iNewPostService.createPost(title, content, userId);
		return newPostResponse;
	}
}

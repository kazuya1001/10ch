package com.example.ch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.ch.response.UserDetailResponse;
import com.example.ch.service.IUserDetailService;

@Controller
public class UserDetailController {

    @Autowired
    private IUserDetailService iUserDetailService;
    @Autowired
    UserDetailResponse userDetailResponse = new UserDetailResponse();
    
	// ユーザ詳細画面表示
	@GetMapping("/user-detail")
	public String init() {
		System.out.println("UserDetailController.init()呼び出し");
		return "user-detail";
	}
	
	//　ユーザ投稿取得機能
	@PostMapping("/api/getUserPosts")
	@ResponseBody
	public UserDetailResponse getUserPosts(@RequestBody String userId, Model model) {
		System.out.println("UserDetailController.getUserPosts()呼び出し");
		userDetailResponse = iUserDetailService.getUserPosts(userId);
		return userDetailResponse;
	}
}

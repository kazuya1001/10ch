package com.example.ch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.ch.model.Users;
import com.example.ch.response.UserInfoResponse;
import com.example.ch.service.IUserInfoService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserInfoController {
	
	@Autowired
	IUserInfoService iUserInfoService;
	
	@GetMapping("/user-info")
	public String init(HttpSession session, Model model) {
		System.out.println("UserInfoController.init()呼び出し");
		Users userInfo = (Users) session.getAttribute("user");
		model.addAttribute("userId",userInfo.getUserId());
		model.addAttribute("userName",userInfo.getUserName());
		model.addAttribute("email",userInfo.getEmail());
		return "user-info";
	}
	
	@PostMapping("/api/updateAcount")
	public UserInfoResponse updateAcount(@RequestParam String userName, String userId, String nowPass, String newPass, String email, HttpSession session) {
		UserInfoResponse userInfoResponse = new UserInfoResponse();
		Users updateUser = new Users();
		updateUser.setUserName(userName);
		updateUser.setUserId(userId);
		updateUser.setPasswordHash(userName);
		updateUser.setEmail(email);
		userInfoResponse = iUserInfoService.updateAcount(updateUser, nowPass, session);
		return userInfoResponse;
	}
}

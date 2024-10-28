package com.example.ch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.ch.model.Users;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserInfoController {

	@GetMapping("/user-info")
	public String init(HttpSession session, Model model) {
		System.out.println("UserInfoController.init()呼び出し");
		Users userInfo = (Users) session.getAttribute("user");
		model.addAttribute("userId",userInfo.getUserId());
		model.addAttribute("userName",userInfo.getUserName());
		model.addAttribute("email",userInfo.getEmail());
		return "user-info";
	}
}

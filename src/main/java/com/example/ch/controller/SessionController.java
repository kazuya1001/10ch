package com.example.ch.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ch.model.Users;

import jakarta.servlet.http.HttpSession;
@RestController
public class SessionController {

	// セッション情報からユーザ情報取り出し
	@GetMapping("api/getUserInfo")
	@ResponseBody
	public Users getUserInfo(HttpSession session) {
		System.out.println("SessionController.getUserInfo()呼び出し");
		Users user = (Users) session.getAttribute("user");
		return user;
	}
}

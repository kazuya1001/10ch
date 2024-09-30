package com.example.ch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.ch.common.ChUtil;
import com.example.ch.response.HomeResponse;
import com.example.ch.service.IHomeService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	private IHomeService iHomeService;
	@Autowired
	ChUtil chUtil;
	
	// ホーム画面表示
	@GetMapping("/home")
	public String init() {
		System.out.println("HomeController.init()呼び出し");
		return "home";
	}
	
	// ホーム画面用テーブル取得
	@PostMapping("/api/getHomePost")
	@ResponseBody
	public HomeResponse getHomePost(HttpSession session) {
		HomeResponse homeResponse = new HomeResponse();
		homeResponse = iHomeService.getHomePost(session);
		return homeResponse;
	}
}

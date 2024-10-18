package com.example.ch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String init(HttpSession session, Model model) {
		System.out.println("HomeController.init()呼び出し");
		Object userInfo = session.getAttribute("user");
		if (userInfo != null) {
			model.addAttribute("isSignedIn",true);
		} else {
			model.addAttribute("isSignedIn",false);
			model.addAttribute("message",chUtil.IS_LOGGEDOUT);
		}
		return "home";
	}
	
	// ホーム画面用テーブル取得
	@PostMapping("/api/getHomePost")
	@ResponseBody
	public HomeResponse getHomePost(HttpSession session) {
		System.out.println("HomeController.getHomePost()呼び出し");
		HomeResponse homeResponse = new HomeResponse();
		homeResponse = iHomeService.getHomePost(session);
		return homeResponse;
	}
}

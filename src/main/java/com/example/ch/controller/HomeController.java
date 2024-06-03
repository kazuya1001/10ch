package com.example.ch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	// ホーム画面表示
	@GetMapping("/home")
	public String init() {
		System.out.println("HomeController.init()呼び出し");
		return "home";
	}
}

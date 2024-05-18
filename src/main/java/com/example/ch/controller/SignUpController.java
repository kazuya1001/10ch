package com.example.ch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignUpController {
	// サインアップ画面表示
	@GetMapping("sign-up")
	public String init() {
		System.out.println("SignUpController.init()呼び出し");
		return "sign-up";
	}
}

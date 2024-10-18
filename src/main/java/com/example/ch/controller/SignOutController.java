package com.example.ch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class SignOutController {

	@GetMapping("/sign-out")
	public String SignOut(HttpSession session) {
		System.out.println("SignOutController.SignOut()呼び出し");
		// セッション情報削除
		session.removeAttribute("user");
		// ホーム画面に遷移
		return "redirect:/home";
	}
}

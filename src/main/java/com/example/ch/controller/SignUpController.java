package com.example.ch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.ch.model.Users;
import com.example.ch.response.SignUpResponse;
import com.example.ch.service.ISignUpService;

@Controller
public class SignUpController {
	
	@Autowired
	private ISignUpService iSignUpService;
	@Autowired
	SignUpResponse signUpResponse = new SignUpResponse();
	
	// サインアップ画面表示
	@GetMapping("/sign-up")
	public String init() {
		System.out.println("SignUpController.init()呼び出し");
		return "sign-up";
	}
	
	// アカウント追加機能
	@PostMapping("/api/signUp")
	@ResponseBody
	public SignUpResponse signUp(@RequestParam String userId ,String userName ,String pass,String email) {
		System.out.println("SignUpController.signUp()呼び出し");
		Users addAcount = new Users();
		addAcount.setUserId(userId);
		addAcount.setUserName(userName);
		addAcount.setPasswordHash(pass);
		addAcount.setEmail(email);
		//カウント追加処理
		signUpResponse = iSignUpService.addAcount(addAcount);
		return signUpResponse;
	}
}

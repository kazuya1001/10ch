package com.example.ch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.ch.common.ChUtil;
import com.example.ch.model.Users;
import com.example.ch.response.SignInResponse;
import com.example.ch.service.ISignInService;

import jakarta.servlet.http.HttpSession;
@Controller
public class SignInController {
	
	@Autowired
	private ISignInService iSignInService;
	@Autowired
	ChUtil chUtil;
	
	// サインイン画面表示
    @GetMapping("/sign-in")
    public String init() {
    	System.out.println("SignInController.init()呼び出し");
        return "sign-in"; 
    }
    
	// サインイン機能
	@PostMapping("/api/signIn")
	@ResponseBody
	public SignInResponse signIn(@RequestParam String userId, String pass, String email,  HttpSession session) {
		System.out.println("SignInController.signUp()呼び出し");
		SignInResponse signInResponse = new SignInResponse();
		Users signInUser = new Users();
		signInUser.setUserId(userId);
		signInUser.setPasswordHash(pass);
		signInUser.setEmail(email);
		// サインイン処理
		signInResponse = iSignInService.signIn(signInUser);
		if (signInResponse.getProcessResult() == chUtil.SUCCESS) {
			session.setAttribute("user",signInResponse.getSignInedUser());
		}
		return signInResponse;
	}
}

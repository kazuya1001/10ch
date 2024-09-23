package com.example.ch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class PostDetailController {

	@GetMapping("/posts/post-detail")
	public String init() {
		System.out.println("PostDetailController.init()呼び出し");
		return "post-detail";
	}
}
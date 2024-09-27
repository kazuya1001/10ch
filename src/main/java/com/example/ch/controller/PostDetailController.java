package com.example.ch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.ch.response.PostDetailResponse;
import com.example.ch.service.IPostDetailService;
@Controller
public class PostDetailController {

    @Autowired
    private IPostDetailService iPostDetailService;
    @Autowired
    PostDetailResponse postDetailResponse = new PostDetailResponse();
    
	/**
	 * 投稿詳細画面表示
	 * @return htmlファイル "post-detail"
	 */
	@GetMapping("/posts/post-detail")
	public String init() {
		System.out.println("PostDetailController.init()呼び出し");
		return "post-detail";
	}
	
	/**
	 * @param postId ポストID
	 * @param model モデル
	 * @return postDetailResponse 処理結果
	 */
	@PostMapping("/api/getTargetPost")
	@ResponseBody
	public PostDetailResponse getTargetPost(@RequestBody String postId, Model model) {
		System.out.println("UserDetailController.getUserPosts()呼び出し");
		postDetailResponse = iPostDetailService.getTargetPost(postId);
		return postDetailResponse;
	}
}
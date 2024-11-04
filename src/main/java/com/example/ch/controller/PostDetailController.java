package com.example.ch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.ch.model.Users;
import com.example.ch.response.CommentResponse;
import com.example.ch.response.PostDetailResponse;
import com.example.ch.service.IPostDetailService;

import jakarta.servlet.http.HttpSession;
@Controller
public class PostDetailController {

    @Autowired
    private IPostDetailService iPostDetailService;
    @Autowired
    PostDetailResponse postDetailResponse = new PostDetailResponse();
    @Autowired
    CommentResponse commentResponse = new CommentResponse();
    
	/**
	 * 投稿詳細画面表示
	 * @return htmlファイル "post-detail"
	 */
	@GetMapping("/posts/post-detail")
	public String init(HttpSession session, Model model) {
		System.out.println("PostDetailController.init()呼び出し");
		Users userInfo = (Users) session.getAttribute("user");
		if (userInfo != null) {
			model.addAttribute("isSignedIn",true);
			model.addAttribute("userId",userInfo.getUserId());
		} else {
			model.addAttribute("isSignedIn",false);
		}
		return "post-detail";
	}
	
	/**
	 * 投稿情報取得
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
	
	/**
	 * コメント登録
	 * @param content 内容
	 * @param postId ポストID
	 * @param userId ユーザID
	 * @return postDetailResponse 処理結果
	 */
	@PostMapping("/api/postComment")
	@ResponseBody
	public CommentResponse postComment(@RequestParam String content,String postId, String userId) {
		System.out.println("UserDetailController.postComment()呼び出し");
		commentResponse = iPostDetailService.postComment(content, postId, userId);
		return commentResponse;
	}
	
}
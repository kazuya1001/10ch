package com.example.ch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ch.common.ChUtil;
import com.example.ch.model.Posts;
import com.example.ch.repository.IPostRepository;
import com.example.ch.repository.IUserRepository;
import com.example.ch.response.PostDetailResponse;

@Service
public class PostDetailService implements IPostDetailService {
	
    @Autowired
    PostDetailResponse postDetailResponse = new PostDetailResponse();
    @Autowired
    IPostRepository iPostRepository;
    @Autowired
    IUserRepository iUserRepository;
	@Autowired
	ChUtil chUtil;

	// 対象投稿取得
	public PostDetailResponse getTargetPost(String postId) {
		System.out.println("PostDetailResponse.getTargetPost()呼び出し");
		try {
			Posts post = iPostRepository.getTargetPost(postId);
			if (post != null && !post.getUserId().isEmpty()) {
				String userName = iUserRepository.getUserName(post.getUserId());
				postDetailResponse = addResponse(chUtil.SUCCESS,0,null,post,userName);
			} else {
				postDetailResponse = addResponse(chUtil.SUCCESS,0,null,null,null);
			}
		} catch(Exception e) {
			System.out.println(e);
			postDetailResponse = addResponse(chUtil.FAILURE_1,0,chUtil.ERROR_DB,null,null);
		}
		return postDetailResponse;
	}
	
	/**
	 * 処理結果追加
	 * @param processResult 処理結果
	 * @param httpStatusCd　HTTPステータスコード
	 * @param errMessage エラーメッセージ
	 * @param post 投稿内容
	 * @param userName 投稿者名
	 * @return postDetailResponse 投稿詳細レスポンス
	 */
	private PostDetailResponse addResponse(int processResult,int httpStatusCd,String errMessage,Posts post,String userName) {
		System.out.println("PostDetailResponse.addResponse()呼び出し");
		postDetailResponse.setProcessResult(processResult);
		postDetailResponse.setHttpStatusCd(httpStatusCd);
		postDetailResponse.setErrMessage(errMessage);
		postDetailResponse.setPost(post);
		postDetailResponse.setUserName(userName);
		return postDetailResponse;
	}
}

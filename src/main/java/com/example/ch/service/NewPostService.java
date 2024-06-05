package com.example.ch.service;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ch.common.ChUtil;
import com.example.ch.model.Posts;
import com.example.ch.repository.INewPostRepository;
import com.example.ch.response.NewPostResponse;
@Service
public class NewPostService implements INewPostService {

	@Autowired
	NewPostResponse newPostResponse = new NewPostResponse();
    @Autowired
    private INewPostRepository iNewPostRepository;
	@Autowired
	ChUtil chUtil;
	
	// 新規投稿機能
	@Override
	public NewPostResponse createPost(Posts newPost) {
		System.out.println("NewPostService.createPost()呼び出し");
		try {
			int postId = getPostId();
			Date CreatTime = new Date();
			newPost.setUpdateAt(CreatTime);
			newPost.setPostId(postId);
			iNewPostRepository.createPost(newPost);
			newPostResponse = addResponse(chUtil.SUCCESS,0,null);
		}catch(Exception e) {
			System.out.println(e);
			newPostResponse = addResponse(chUtil.FAILURE_1,0,chUtil.ERROR_DB);
		}
		return newPostResponse;
	}
	
	// postId取得処理(DBと重複しない値で最大7桁)
	private int getPostId() {
		Random random = new Random();
		int postId = 0;
		int attempts = 0;
        do {
            postId = random.nextInt(10000000);
            attempts++;
        } while (iNewPostRepository.existsByPostId(postId) && attempts < chUtil.MAX_ATTEMPTS);

        if (attempts >= chUtil.MAX_ATTEMPTS) {
            throw new RuntimeException("Unique ID could not be generated after " + chUtil.MAX_ATTEMPTS + " attempts");
        }
        return postId;
    }
	
	// 処理結果追加
	private NewPostResponse addResponse(int processResult,int httpStatusCd,String errMessage) {
		System.out.println("SignUpResponse.addResponse()呼び出し");
		newPostResponse.setProcessResult(processResult);
		newPostResponse.setHttpStatusCd(httpStatusCd);
		newPostResponse.setErrMessage(errMessage);
		return newPostResponse;
	}	
}

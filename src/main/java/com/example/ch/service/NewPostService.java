package com.example.ch.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ch.common.ChUtil;
import com.example.ch.model.Posts;
import com.example.ch.repository.IPostsRepository;
import com.example.ch.response.NewPostResponse;
@Service
public class NewPostService implements INewPostService {

	@Autowired
	NewPostResponse newPostResponse = new NewPostResponse();
    @Autowired
    private IPostsRepository iPostRepository;
	@Autowired
	ChUtil chUtil;
	
	// 新規投稿機能
	@Override
	public NewPostResponse createPost(String title, String content, String userId) {
		System.out.println("NewPostService.createPost()呼び出し");
		try {
			Posts newPost = new Posts();
			String postId = getUniquePostId();
			if (postId.equals(chUtil.NO_REMAINDER)) {
				newPostResponse = addResponse(chUtil.FAILURE_2,0,chUtil.CAN_NOT_POST);
				return newPostResponse;
			}
			newPost.setTitle(title);
			newPost.setContent(content);
			newPost.setUserId(userId);
			Date creatTime = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			try {
				Date formattedDate = sdf.parse(sdf.format(creatTime)); 
				newPost.setUpdateAt(formattedDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			newPost.setPostId(postId);
			iPostRepository.createPost(newPost);
			newPostResponse = addResponse(chUtil.SUCCESS,0,null);
		}catch(Exception e) {
			System.out.println(e);
			newPostResponse = addResponse(chUtil.FAILURE_1,0,chUtil.ERROR_DB);
		}
		return newPostResponse;
	}
	
	// postId取得処理(DBと重複しない値で最大7桁)
	private String getUniquePostId() {
		System.out.println("NewPostService.getPostId()呼び出し");
		Random random = new Random();
		// 16進数取得
		int hex_number = random.nextInt(0x1000000);;
		String postId = String.format("%07X", hex_number);;
		// postId取得
		List<String> postIds = iPostRepository.getAllPostId();
		for (int i = 0;i<=postIds.size();i++) {
			if(postIds.size() == 0 || !(postId.equals(postIds.get(i)))) {
				return postId;
			}
		}
        return chUtil.NO_REMAINDER;
    }
	
	// 処理結果追加
	private NewPostResponse addResponse(int processResult,int httpStatusCd,String errMessage) {
		System.out.println("NewPostService.addResponse()呼び出し");
		newPostResponse.setProcessResult(processResult);
		newPostResponse.setHttpStatusCd(httpStatusCd);
		newPostResponse.setErrMessage(errMessage);
		return newPostResponse;
	}	
}

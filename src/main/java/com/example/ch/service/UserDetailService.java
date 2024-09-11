package com.example.ch.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ch.common.ChUtil;
import com.example.ch.model.Posts;
import com.example.ch.repository.IPostRepository;
import com.example.ch.repository.IUserRepository;
import com.example.ch.response.UserDetailResponse;

@Service
public class UserDetailService implements IUserDetailService {

    @Autowired
    UserDetailResponse userDetailResponse = new UserDetailResponse();
    @Autowired
    IPostRepository iPostRepository;
    @Autowired
    IUserRepository iUserRepository;
	@Autowired
	ChUtil chUtil;
    
	public UserDetailResponse getUserPosts(String userId) {
		System.out.println("UserDetailResponse.getUserPosts()呼び出し");
		try {
			List<Posts> postRecord = iPostRepository.getPostListByUserId(userId);
			String userName = iUserRepository.getUserName(userId);
			userDetailResponse = addResponse(chUtil.SUCCESS,0,null,postRecord,userName);
		}catch(Exception e) {
			System.out.println(e);
			userDetailResponse = addResponse(chUtil.FAILURE_1,0,chUtil.ERROR_DB,null,"");
		}
		return userDetailResponse;
	}
	
	// 処理結果追加
	private UserDetailResponse addResponse(int processResult,int httpStatusCd,String errMessage,List<Posts> postRecord,String userName) {
		System.out.println("NewPostService.addResponse()呼び出し");
		userDetailResponse.setProcessResult(processResult);
		userDetailResponse.setHttpStatusCd(httpStatusCd);
		userDetailResponse.setErrMessage(errMessage);
		userDetailResponse.setPostRecord(postRecord);
		userDetailResponse.setUserName(userName);
		return userDetailResponse;
	}	
}
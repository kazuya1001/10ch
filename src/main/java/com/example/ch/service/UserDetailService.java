package com.example.ch.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ch.common.ChUtil;
import com.example.ch.model.Posts;
import com.example.ch.model.Users;
import com.example.ch.repository.IPostsRepository;
import com.example.ch.repository.IUsersRepository;
import com.example.ch.response.UserDetailResponse;

@Service
public class UserDetailService implements IUserDetailService {

    @Autowired
    UserDetailResponse userDetailResponse = new UserDetailResponse();
    @Autowired
    IPostsRepository iPostRepository;
    @Autowired
    IUsersRepository iUsersRepository;
	@Autowired
	ChUtil chUtil;
    
	public UserDetailResponse getUserPosts(String userId) {
		System.out.println("UserDetailResponse.getUserPosts()呼び出し");
		Users dbUser = new Users();
		try {
			List<Posts> postRecord = iPostRepository.getPostListByUserId(userId);
			dbUser = iUsersRepository.findByUserId(userId);
			if (dbUser == null) {
				throw new RuntimeException("対象のユーザが見つかりませんでした");
			}
			String userName = dbUser.getUserName();
			userDetailResponse = addResponse(chUtil.SUCCESS,0,null,postRecord,userName);
		}catch(Exception e) {
			System.out.println(e);
			userDetailResponse = addResponse(chUtil.FAILURE_1,0,chUtil.ERROR_DB,null,"");
		}
		return userDetailResponse;
	}
	
	// 処理結果追加
	private UserDetailResponse addResponse(int processResult,int httpStatusCd,String errMessage,List<Posts> postRecord,String userName) {
		System.out.println("UserDetailService.addResponse()呼び出し");
		userDetailResponse.setProcessResult(processResult);
		userDetailResponse.setHttpStatusCd(httpStatusCd);
		userDetailResponse.setErrMessage(errMessage);
		userDetailResponse.setPostRecord(postRecord);
		userDetailResponse.setUserName(userName);
		return userDetailResponse;
	}	
}

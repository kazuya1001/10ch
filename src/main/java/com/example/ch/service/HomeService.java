package com.example.ch.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ch.common.ChUtil;
import com.example.ch.model.Posts;
import com.example.ch.model.Users;
import com.example.ch.repository.IPostRepository;
import com.example.ch.repository.IUserRepository;
import com.example.ch.response.HomeResponse;

import jakarta.servlet.http.HttpSession;
@Service
public class HomeService implements IHomeService {

	@Autowired
	HomeResponse homeResponse = new HomeResponse();
    @Autowired
    private IPostRepository iPostRepository;
    @Autowired
    private IUserRepository iUserRepository;
	@Autowired
	ChUtil chUtil;
	
	// ホーム画面表示用最新5投稿取得
	@Override
	public HomeResponse getHomePost(HttpSession session) {
		try {
			Users userInfo = (Users) session.getAttribute("user");
			List<Posts> postList = new ArrayList<Posts>();
	        List<String> userIdList = new ArrayList<String>();
	        List<Users> userNameList = new ArrayList<Users>();
			if (userInfo == null) {
				postList = iPostRepository.getHomePostListToUserInfoNull();
			} else {
				postList = iPostRepository.getHomePostList();
			}
			userIdList = postList.stream().map(Posts::getUserId).collect(Collectors.toList());
			userNameList = iUserRepository.getUserNameListToUserInfoNull(userIdList);
	        List<String> userNameRecord = new ArrayList<String>();
	        // usersテーブルとpostsテーブルのuserIdを比較しuserNameをホーム画面の投稿順に並べる
			for (int i = 0; i < postList.size();i++) {
				for (int j = 0; j< userNameList.size(); j++) {
					if (postList.get(i).getUserId().equals(userNameList.get(j).getUserId())) {
						userNameRecord.add(userNameList.get(j).getUserName());
						break;
					}
				}
			}
			homeResponse = addResponse(chUtil.SUCCESS,0,null,postList,userNameRecord);
		}catch(Exception e){
			System.out.println(e);
			homeResponse = addResponse(chUtil.FAILURE_1,0,chUtil.ERROR_DB,null,null);
		}
		return homeResponse;
	}
	
	
	// 処理結果追加
	private HomeResponse addResponse(int processResult,int httpStatusCd,String errMessage,List<Posts> postRecord,List<String> userNameRecord) {
		System.out.println("HomeService.addResponse()呼び出し");
		homeResponse.setProcessResult(processResult);
		homeResponse.setHttpStatusCd(httpStatusCd);
		homeResponse.setErrMessage(errMessage);
		homeResponse.setPostRecord(postRecord);
		homeResponse.setUserNameRecord(userNameRecord);
		return homeResponse;
	}
}

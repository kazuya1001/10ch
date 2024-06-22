package com.example.ch.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ch.common.ChUtil;
import com.example.ch.model.Posts;
import com.example.ch.repository.IPostRepository;
import com.example.ch.response.HomeResponse;
@Service
public class HomeService implements IHomeService {

	@Autowired
	HomeResponse homeResponse = new HomeResponse();
    @Autowired
    private IPostRepository iPostRepository;
	@Autowired
	ChUtil chUtil;
	
	// ホーム画面表示用最新5投稿取得
	@Override
	public HomeResponse getHomePost() {
		
		try {
			List<Posts> postRecord = iPostRepository.getHomePost();
			homeResponse = addResponse(chUtil.SUCCESS,0,null,postRecord);
			
		}catch(Exception e){
			System.out.println(e);
			homeResponse = addResponse(chUtil.FAILURE_1,0,chUtil.ERROR_DB,null);
		}
		
		return homeResponse;
	}
	
	
	// 処理結果追加
	private HomeResponse addResponse(int processResult,int httpStatusCd,String errMessage,List<Posts> postRecord) {
		System.out.println("NewPostService.addResponse()呼び出し");
		homeResponse.setProcessResult(processResult);
		homeResponse.setHttpStatusCd(httpStatusCd);
		homeResponse.setErrMessage(errMessage);
		homeResponse.setPostRecord(postRecord);
		return homeResponse;
	}
}

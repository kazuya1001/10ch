package com.example.ch.service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.ch.common.ChUtil;
import com.example.ch.model.Comments;
import com.example.ch.model.Posts;
import com.example.ch.repository.ICommentsRepository;
import com.example.ch.repository.IPostRepository;
import com.example.ch.repository.IUserRepository;
import com.example.ch.response.CommentResponse;
import com.example.ch.response.PostDetailResponse;

@Service
public class PostDetailService implements IPostDetailService {
	
    @Autowired
    PostDetailResponse postDetailResponse = new PostDetailResponse();
    @Autowired
    CommentResponse commentResponse = new CommentResponse();
    @Autowired
    IPostRepository iPostRepository;
    @Autowired
    IUserRepository iUserRepository;
    @Autowired
    ICommentsRepository iCommentsRepository;
	@Autowired
	ChUtil chUtil;

	// 対象投稿取得
	public PostDetailResponse getTargetPost(String postId) {
		System.out.println("PostDetailService.getTargetPost()呼び出し");
		try {
			Posts post = iPostRepository.getTargetPost(postId);
			if (post != null && !post.getUserId().isEmpty()) {
				String userName = iUserRepository.getUserName(post.getUserId());
				postDetailResponse = addPostDetailResponse(chUtil.SUCCESS,0,null,post,userName);
			} else {
				postDetailResponse = addPostDetailResponse(chUtil.SUCCESS,0,null,null,null);
			}
		} catch(Exception e) {
			System.out.println(e);
			postDetailResponse = addPostDetailResponse(chUtil.FAILURE_1,0,chUtil.ERROR_DB,null,null);
		}
		return postDetailResponse;
	}
	
	// コメント登録
	public CommentResponse postComment(@RequestParam String content,String postId, String userId) {
		System.out.println("PostDetailService.postComment()呼び出し");
		try {
			Comments postComment = new Comments();
			String commentId = getUniquePostId();
			if (commentId.equals(chUtil.NO_REMAINDER)) {
				commentResponse = addCommentResponse(chUtil.FAILURE_2,0,chUtil.CAN_NOT_POST,postComment,Collections.emptyList());
				return commentResponse;
			}
			Date CreatTime = new Date();
			postComment.setContent(content);
			postComment.setPostId(postId);
			postComment.setUserId(userId);
			postComment.setCreatedAt(CreatTime);
			postComment.setCommentId(commentId);
			iCommentsRepository.createComment(postComment);
			commentResponse = addCommentResponse(chUtil.SUCCESS,0,null,postComment,Collections.emptyList());
		} catch(Exception e) {
			System.out.println(e);
		}
		return commentResponse;
	}
	
	// postId取得処理(DBと重複しない値で最大7桁)
	private String getUniquePostId() {
		System.out.println("PostDetailService.getPostId()呼び出し");
		Random random = new Random();
		// 16進数取得
		int hex_number = random.nextInt(0x1000000);;
		String commentId = String.format("%07X", hex_number);;
		// postId取得
		List<String> commentIds = iCommentsRepository.getAllCommentId();
		for (int i = 0;i<=commentIds.size();i++) {
			if(commentIds.size() == 0 || !(commentId.equals(commentIds.get(i)))) {
				return commentId;
			}
		}
        return chUtil.NO_REMAINDER;
    }
	
	/**
	 * 投稿詳細処理結果追加
	 * @param processResult 処理結果
	 * @param httpStatusCd　HTTPステータスコード
	 * @param errMessage エラーメッセージ
	 * @param post 投稿内容
	 * @param userName 投稿者名
	 * @return postDetailResponse 投稿詳細レスポンス
	 */
	private PostDetailResponse addPostDetailResponse(int processResult,int httpStatusCd,String errMessage,Posts post,String userName) {
		System.out.println("PostDetailService.addResponse()呼び出し");
		postDetailResponse.setProcessResult(processResult);
		postDetailResponse.setHttpStatusCd(httpStatusCd);
		postDetailResponse.setErrMessage(errMessage);
		postDetailResponse.setPost(post);
		postDetailResponse.setUserName(userName);
		return postDetailResponse;
	}
	
	/**
	 * コメント登録処理結果追加
	 * @param processResult 処理結果
	 * @param httpStatusCd　HTTPステータスコード
	 * @param errMessage エラーメッセージ
	 * @param comment コメント内容
	 * @param commentList コメント一覧
	 * @return postDetailResponse コメントレスポンス
	 */
	private CommentResponse addCommentResponse(int processResult, int httpStatusCd, String errMessage, Comments comment, List<Comments> commentList) {
		System.out.println("PostDetailService.addResponse()呼び出し");
		commentResponse.setProcessResult(processResult);
		commentResponse.setHttpStatusCd(httpStatusCd);
		commentResponse.setErrMessage(errMessage);
		commentResponse.setComment(comment);
		commentResponse.setCommentList(commentList);
		return commentResponse;
	}
}

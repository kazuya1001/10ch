package com.example.ch.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.example.ch.model.Users;
import com.example.ch.repository.ICommentsRepository;
import com.example.ch.repository.IPostRepository;
import com.example.ch.repository.IUsersRepository;
import com.example.ch.response.CommentResponse;
import com.example.ch.response.PostDetailResponse;

@Service
public class PostDetailService implements IPostDetailService {
	
    @Autowired
    PostDetailResponse postDetailResponse = new PostDetailResponse();
    @Autowired
    CommentResponse insertCommentResponse = new CommentResponse();
    @Autowired
    IPostRepository iPostRepository;
    @Autowired
    IUsersRepository iUsersRepository;
    @Autowired
    ICommentsRepository iCommentsRepository;
	@Autowired
	ChUtil chUtil;

	// 対象投稿取得
	public PostDetailResponse getTargetPost(String postId) {
		System.out.println("PostDetailService.getTargetPost()呼び出し");
		List<Comments> commentList = new ArrayList<>();
		List<String> userIdListByPostComment = new ArrayList<>();
		List<String> userNameListByPostComment = new ArrayList<>();
		List<Users> userInfoList = new ArrayList<>();
		try {
			Posts post = iPostRepository.getTargetPost(postId);
			String userName = iUsersRepository.getUserName(post.getUserId());
			commentList = iCommentsRepository.getCommentList(postId);
			// ユーザIDをユーザネーム検索用にセット
			for (int i = 0; i < commentList.size(); i++) {
				userIdListByPostComment.add(commentList.get(i).getUserId());
			}
			if (!userIdListByPostComment.isEmpty()) {
				userInfoList = getUserInfoList(userIdListByPostComment);
				for (int i = 0; i < userIdListByPostComment.size(); i++) {
					String userId = userIdListByPostComment.get(i);
					for (int j = 0; j < userInfoList.size(); j++) {
						String dbUserId = userInfoList.get(j).getUserId();
						if (userId.equals(dbUserId)) {
							String userNameByPostComment = userInfoList.get(j).getUserName();
							userNameListByPostComment.add(userNameByPostComment);
							break;
						}
					}
				}
			} else {
				userNameListByPostComment = Collections.emptyList();
			}
			postDetailResponse = addPostDetailResponse(chUtil.SUCCESS, 0, null, post, userName, commentList, userNameListByPostComment);
		} catch(Exception e) {
			System.out.println(e);
			postDetailResponse = addPostDetailResponse(chUtil.FAILURE_1, 0, chUtil.ERROR_DB, null, null, Collections.emptyList(),Collections.emptyList());
		}
		return postDetailResponse;
	}
	
	// コメント登録
	public CommentResponse postComment(@RequestParam String content, String postId, String userId) {
		System.out.println("PostDetailService.postComment()呼び出し");
		Comments postComment = new Comments();
		try {
			String commentId = getUniquePostId();
			if (commentId.equals(chUtil.NO_REMAINDER)) {
				insertCommentResponse = addInsertCommentResponse(chUtil.FAILURE_2, 0, chUtil.CAN_NOT_POST, postComment);
				return insertCommentResponse;
			}
			postComment.setContent(content);
			postComment.setPostId(postId);
			postComment.setUserId(userId);
			Date creatTime = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			try {
				Date formattedDate = sdf.parse(sdf.format(creatTime)); 
				postComment.setCreatedAt(formattedDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			postComment.setCommentId(commentId);
			iCommentsRepository.createComment(postComment);
			insertCommentResponse = addInsertCommentResponse(chUtil.SUCCESS, 0, null, postComment);
		} catch(Exception e) {
			System.out.println(e);
		}
		return insertCommentResponse;
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
	
	private List<Users> getUserInfoList(List<String> userIdList){
		List<Users> userInfoList = new ArrayList<Users>();
		userInfoList = iUsersRepository.getUserNameListToUserInfoNull(userIdList);
		return userInfoList;
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
	private PostDetailResponse addPostDetailResponse(int processResult, int httpStatusCd, String errMessage, Posts post, String userName, List<Comments> commentList, List<String> userNameListByPostComment) {
		System.out.println("PostDetailService.addResponse()呼び出し");
		postDetailResponse.setProcessResult(processResult);
		postDetailResponse.setHttpStatusCd(httpStatusCd);
		postDetailResponse.setErrMessage(errMessage);
		postDetailResponse.setPost(post);
		postDetailResponse.setUserName(userName);
		postDetailResponse.setCommentList(commentList);
		postDetailResponse.setUserNameListByPostComment(userNameListByPostComment);
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
	private CommentResponse addInsertCommentResponse(int processResult, int httpStatusCd, String errMessage, Comments comment) {
		System.out.println("PostDetailService.addResponse()呼び出し");
		insertCommentResponse.setProcessResult(processResult);
		insertCommentResponse.setHttpStatusCd(httpStatusCd);
		insertCommentResponse.setErrMessage(errMessage);
		insertCommentResponse.setComment(comment);
		return insertCommentResponse;
	}
}

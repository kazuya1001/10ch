package com.example.ch.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.ch.mapper.CommentsMapper;
import com.example.ch.model.Comments;
import com.example.ch.model.CommentsExample;
import com.example.ch.model.Users;
@Repository
public class CommentsRepository implements ICommentsRepository {
	
	@Autowired
	CommentsMapper commentsMapper;
	
	// コメントID取得(新規投稿画面)
	public List<String> getAllCommentId(){
		return commentsMapper.selectAllCommentIds();
	}

	// コメント登録(投稿詳細画面)
	public void createComment(Comments postComment) {
		commentsMapper.insert(postComment);
	};
	
	// コメント一覧取得(投稿詳細画面)
	public List<Comments> getCommentList(String postId) {
		CommentsExample example = new CommentsExample();
		CommentsExample.Criteria criteria = example.createCriteria();
		criteria.andPostIdEqualTo(postId);
		example.setOrderByClause("created_at DESC");
		return commentsMapper.selectByExample(example);
	}
	
	// アカウント更新
	@Override
	public void updateByUserId(Users updateAcount, String userId) {
		commentsMapper.updateByUserId(updateAcount.getUserId(), userId);
	}
}

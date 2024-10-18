package com.example.ch.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.ch.mapper.CommentsMapper;
import com.example.ch.model.Comments;
@Repository
public class CommentsRepository implements ICommentsRepository {
	
	@Autowired
	CommentsMapper commentsMapper;
	
	// ポストID取得(新規投稿画面)
	public List<String> getAllCommentId(){
		return commentsMapper.selectAllCommentIds();
	}

	public void createComment(Comments postComment) {
		commentsMapper.insert(postComment);
	};
}

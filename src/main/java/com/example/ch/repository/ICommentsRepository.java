package com.example.ch.repository;

import java.util.List;

import com.example.ch.model.Comments;

public interface ICommentsRepository {

    /**
     * コメントID取得(投稿詳細画面)
     * @return コメントID一覧
     */
	List<String> getAllCommentId();
	
    /**
     * コメント登録(投稿詳細画面)
     * @param postComment 投稿コメント
     */
	void createComment(Comments postComment);
}

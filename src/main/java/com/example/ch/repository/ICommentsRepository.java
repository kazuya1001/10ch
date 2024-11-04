package com.example.ch.repository;

import java.util.List;

import com.example.ch.model.Comments;
import com.example.ch.model.Users;

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
	
    /**
     * コメント一覧取得(投稿詳細画面)
     * @param postId ポストId
     * @return コメント一覧
     */
    public List<Comments> getCommentList(String postId);
    
    /**
     * ユーザID更新(ユーザ情報画面)
     * @param updateAcount 更新アカウント
     * @param userId ユーザID
     */
	void updateByUserId(Users updateAcount, String userId);
}

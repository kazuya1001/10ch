package com.example.ch.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.ch.mapper.PostsMapper;
import com.example.ch.model.Posts;
import com.example.ch.model.PostsExample;
@Repository
public class PostRepository implements IPostRepository {
	
	@Autowired
	private PostsMapper postsMapper;
	
	// 新規投稿(新規投稿画面)
	@Override
	public void createPost(Posts post) {
		postsMapper.insert(post);
	}
	
	// post_id取得(新規投稿画面)
	public List<String> getAllPostId(){
		return postsMapper.selectAllPostIds();
	}
	
	// 投稿一覧取得(ホーム画面)
	public List<Posts> getHomePost(){
		return postsMapper.selectRecentPosts();
	}
	
	// 投稿一覧取得(ユーザ詳細画面)
	public List<Posts> getPostListByUserId(String userId){
		PostsExample example = new PostsExample();
		PostsExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		example.setOrderByClause("update_at DESC");
		return postsMapper.selectByExample(example);
	}
	
}

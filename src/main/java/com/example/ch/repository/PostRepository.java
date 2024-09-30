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
	public void createPost(Posts post) {
		postsMapper.insert(post);
	}
	
	// ポストID取得(新規投稿画面)
	public List<String> getAllPostId(){
		return postsMapper.selectAllPostIds();
	}
	
	// 最新5投稿一覧取得(ホーム画面)
	public List<Posts> getHomePostListToUserInfoNull(){
		return postsMapper.selectRecentPosts();
	}
	
	public List<Posts> getHomePostList(){
		return postsMapper.selectAllPost();
	}
	
	// 投稿一覧取得(ユーザ詳細画面)
	public List<Posts> getPostListByUserId(String userId){
		PostsExample example = new PostsExample();
		PostsExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		example.setOrderByClause("update_at DESC");
		return postsMapper.selectByExample(example);
	}
	
    // 対象投稿取得(投稿詳細画面)
	public Posts getTargetPost(String postId) {
		PostsExample example = new PostsExample();
		PostsExample.Criteria criteria = example.createCriteria();
		criteria.andPostIdEqualTo(postId);
		return postsMapper.selectByPrimaryKey(postId);
	}
}

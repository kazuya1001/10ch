package com.example.ch.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.ch.mapper.PostsMapper;
import com.example.ch.model.Posts;
import com.example.ch.model.PostsExample;
@Repository
public class NewPostRepository implements INewPostRepository {
	
	@Autowired
	private PostsMapper postsMapper;

	// postId取得
	@Override
	public boolean existsByPostId(int postId) {
		boolean result = true;
		PostsExample example = new PostsExample();
		PostsExample.Criteria criteria = example.createCriteria();
		criteria.andPostIdEqualTo(postId);
		if (postsMapper.countByExample(example) != 0) {
			result = false;
		}
		return result;
	};
	
	// 新規投稿
	@Override
	public void createPost(Posts post) {
		postsMapper.insert(post);
	}
	
}

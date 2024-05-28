package com.example.ch.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.ch.mapper.UsersMapper;
import com.example.ch.model.Users;
import com.example.ch.model.UsersExample;

@Repository
public class UserRepository implements IUsersRepository {
	
	@Autowired
	private UsersMapper usersMapper;
	
	// アカウント追加(サインアップ画面)
	@Override
	public void addAcount (Users addAcount) {
			usersMapper.insert(addAcount);
	}
	
	// ユーザID件数取得(サインアップ画面:登録済みチェック)
	@Override
	public int countUserIdRegistered(String userId) {
		UsersExample example = new UsersExample();
		UsersExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		return (int) usersMapper.countByExample(example);
	}
	
	// email件数取得(サインアップ画面:登録済みチェック)
	@Override
	public int countEmailRegistered(String email) {
		UsersExample example = new UsersExample();
		UsersExample.Criteria criteria = example.createCriteria();
		criteria.andEmailEqualTo(email);
		return (int) usersMapper.countByExample(example);
	}
}

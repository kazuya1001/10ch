package com.example.ch.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.ch.mapper.UsersMapper;
import com.example.ch.model.Users;
import com.example.ch.model.UsersExample;

@Repository
public class UsersRepository implements IUsersRepository {
	
	@Autowired
	private UsersMapper usersMapper;
	
	// アカウント追加(サインアップ画面)
	@Override
	public void addAcount (Users addAcount) {
		usersMapper.insert(addAcount);
	}
	
	// ユーザIDをキーにアカウント検索(サインイン画面)
	public Users findByUserId(String userId) {
		UsersExample example = new UsersExample();
		UsersExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		return usersMapper.selectByExample(example);
	}
	
	// emailをキーにアカウント検索(サインイン画面)
	public Users findByEmail(String email) {
		UsersExample example = new UsersExample();
		UsersExample.Criteria criteria = example.createCriteria();
		criteria.andEmailEqualTo(email);
		return usersMapper.selectByExample(example);
	}	
	
	// ユーザID件数取得(サインアップ画面:登録済みチェック、サインイン画面:存在チェック)
	@Override
	public int countUserIdRegistered(String userId) {
		UsersExample example = new UsersExample();
		UsersExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		return (int) usersMapper.countByExample(example);
	}
	
	// email件数取得(サインアップ画面:登録済み、チェックサインイン画面:存在チェック)
	@Override
	public int countEmailRegistered(String email) {
		UsersExample example = new UsersExample();
		UsersExample.Criteria criteria = example.createCriteria();
		criteria.andEmailEqualTo(email);
		return (int) usersMapper.countByExample(example);
	}
	
	// postsテーブルのuserIdをキーにuserName取得
	@Override
	public List<Users> getUserNameListToUserInfoNull(List<String> userIdList) {
	    return usersMapper.selectUsersNameList(userIdList);
	}
	
	// userIdをキーにuserName取得
	@Override
	public String getUserName(String userId) {
		UsersExample example = new UsersExample();
		UsersExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		Users user = usersMapper.selectByExample(example);
		return user.getUserName();
	}
	
	// アカウント更新
	@Override
	public void updateAcount(Users updateAcount, String userId) {
		UsersExample example = new UsersExample();
		UsersExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		usersMapper.updateByExample(updateAcount, example);
	}

}
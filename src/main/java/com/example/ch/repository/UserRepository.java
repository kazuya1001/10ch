package com.example.ch.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.ch.mapper.UsersMapper;
import com.example.ch.model.Users;

@Repository
public class UserRepository implements IUsersRepository {
	
	@Autowired
	private UsersMapper usersMapper;
	
	// アカウント追加(サインアップ画面)
	@Override
	public boolean addAcount (Users addAcount) {
		try {
			usersMapper.insert(addAcount);
		}catch(Exception e) {
			return false;
		}
		return true;
	}
}

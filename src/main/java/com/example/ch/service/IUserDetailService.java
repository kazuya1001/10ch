package com.example.ch.service;

import com.example.ch.response.UserDetailResponse;

public interface IUserDetailService {

	UserDetailResponse getUserPosts(String userId);
}

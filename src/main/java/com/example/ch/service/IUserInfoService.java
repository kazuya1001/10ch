package com.example.ch.service;

import com.example.ch.model.Users;
import com.example.ch.response.UserInfoResponse;

import jakarta.servlet.http.HttpSession;

public interface IUserInfoService {

	UserInfoResponse updateAcount(Users updateAcount, String nowPass, HttpSession session);
}

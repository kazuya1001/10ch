package com.example.ch.service;

import com.example.ch.response.HomeResponse;

import jakarta.servlet.http.HttpSession;

public interface IHomeService {

	HomeResponse getHomePost(HttpSession session);
}

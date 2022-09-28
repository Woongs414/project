package com.spring.ec.kakao.service;

import java.util.HashMap;

public interface KakaoService {
	public HashMap<String,Object> getUserInfo(String access_Token) throws Exception;

	public String getAccessToken(String authorize_code) throws Exception;
}

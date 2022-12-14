package com.spring.ec.kakao.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.ec.kakao.service.KakaoService;

@Controller("kakaoController")
public class KakaoControllerImpl {
	@Autowired
	private KakaoService kakaoService;

	
	@RequestMapping("/kakao") //localhost:8080/ec/kakao 로 호출해야된다.
	//직접호출하면 카카오로그인api에서 정보 못받아오기때문에 무의미하다.
    public String home(@RequestParam(value = "code", required = false) String code) throws Exception{
		 System.out.println("#########" + code);
	        String access_Token = kakaoService.getAccessToken(code);
	        HashMap<String, Object> userInfo = kakaoService.getUserInfo(access_Token);
	        System.out.println("###access_Token#### : " + access_Token);
	        System.out.println("###email#### : " + userInfo.get("email"));
	        System.out.println("###nickname#### : " + userInfo.get("nickname"));
	        System.out.println("###profile_image#### : " + userInfo.get("profile_image"));
        return "main";
    }
}

package com.spring.ec.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.ec.user.vo.MemberVO;

public interface UserController {
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView listBoards(HttpServletRequest request, HttpServletResponse response)throws Exception;
	
	public ModelAndView listEatBoards(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView listSeeBoards(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView loginform(@RequestParam(value = "result", required = false) String result,@RequestParam(value = "action", required = false) String action, HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	public ModelAndView find_id(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView find_pwd(HttpServletRequest request, HttpServletResponse response) throws Exception ;
	
	public ModelAndView memberform_main(@RequestParam(value = "result", required = false) String result,@RequestParam(value = "action", required = false) String action, HttpServletRequest request,HttpServletResponse response) throws Exception;

	public ModelAndView memberform_udetail(@RequestParam(value = "result", required = false) String result,@RequestParam(value = "action", required = false) String action, HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	public ModelAndView addMember(@ModelAttribute("member") MemberVO member, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView login(@ModelAttribute("member") MemberVO member, RedirectAttributes rAttr, HttpServletRequest request, HttpServletResponse response)throws Exception;

	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception;
	//	public ModelAndView category(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView regadmin(@RequestParam(value = "result", required = false) String result,@RequestParam(value = "action", required = false) String action, HttpServletRequest request,HttpServletResponse response) throws Exception;
	
}

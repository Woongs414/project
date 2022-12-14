package com.spring.ec.user.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.spring.ec.user.vo.BoardVO;
import com.spring.ec.user.vo.MemberVO;

public interface UserService {
	public List<BoardVO> listBoards()throws Exception;
	
	public List<BoardVO> eatListBoards()throws Exception;
	
	public int addMember(MemberVO memberVO) throws DataAccessException; 
	
	public MemberVO login(MemberVO member)throws DataAccessException;
	
	public List seeListBoards();
}

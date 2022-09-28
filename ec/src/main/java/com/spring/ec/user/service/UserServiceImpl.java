package com.spring.ec.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.spring.ec.user.dao.UserDAO;
import com.spring.ec.user.vo.BoardVO;
import com.spring.ec.user.vo.MemberVO;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	UserDAO userDAO;
	
	
	@Override
	public List<BoardVO> listBoards()throws Exception{
		return userDAO.selectAllBoardsList();
	}
	
	@Override
	public List<BoardVO> eatListBoards()throws Exception{
		return userDAO.selectEatBoardsList();
	}
	
	@Override
	public MemberVO login(MemberVO memberVO) throws DataAccessException{
		return userDAO.loginById(memberVO);
	}
	@Override
	public int addMember(MemberVO member) throws DataAccessException {
		return userDAO.insertMember(member);
	}

	@Override
	public List seeListBoards() {
		// TODO Auto-generated method stub
		return null;
	} 
}

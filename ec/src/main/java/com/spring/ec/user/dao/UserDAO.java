package com.spring.ec.user.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.spring.ec.user.vo.MemberVO;

public interface UserDAO {
	public List selectAllBoardsList() throws DataAccessException;
	
	public List selectEatBoardsList() throws DataAccessException;
	
	
	public MemberVO loginById(MemberVO memberVO) throws DataAccessException;
	
	public int insertMember(MemberVO memberVO) throws DataAccessException;
}

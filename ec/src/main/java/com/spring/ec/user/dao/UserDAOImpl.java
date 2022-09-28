package com.spring.ec.user.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring.ec.user.vo.BoardVO;
import com.spring.ec.user.vo.MemberVO;

@Repository("userDAO")
public class UserDAOImpl implements UserDAO {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List selectAllBoardsList() throws DataAccessException{
		List<BoardVO> boardsList = sqlSession.selectList("mapper.board.selectAllBoardsList");
		return boardsList;
	}
	
	@Override
	public List selectEatBoardsList() throws DataAccessException{
		List<BoardVO> boardsList = sqlSession.selectList("mapper.board.selectEatBoardsList");
		return boardsList;
	}
	
	@Override
	public MemberVO loginById(MemberVO memberVO) throws DataAccessException {
		MemberVO vo = sqlSession.selectOne("mapper.member.loginById", memberVO);
		return vo;
	}
	@Override
	public int insertMember(MemberVO memberVO) throws DataAccessException {
		int result = sqlSession.insert("mapper.member.insertMember", memberVO);
		return result;
	}
	
}

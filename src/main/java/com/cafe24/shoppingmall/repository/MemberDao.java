package com.cafe24.shoppingmall.repository;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.shoppingmall.vo.MemberVo;

@Repository
public class MemberDao {
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private DataSource dataSource;

	public Boolean insert(MemberVo memberVo) {
		return 1 == sqlSession.insert("member.insert", memberVo);
	}

	public MemberVo get(String username) {
		return sqlSession.selectOne("member.getByUsername", username);
	}
	
	public MemberVo get(MemberVo memberVo) {
		return sqlSession.selectOne("member.getByUsernameAndPassword", memberVo);
	}
}

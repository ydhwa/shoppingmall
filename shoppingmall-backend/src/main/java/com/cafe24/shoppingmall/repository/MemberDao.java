package com.cafe24.shoppingmall.repository;

import java.util.HashMap;
import java.util.List;

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
	
	// 회원가입 작업 이후에 곧장 실행됨. currval() 때문에 파라미터 넣을 필요 없다.
	public Boolean insertAuthority() {
		return 1 == sqlSession.insert("member.insertAuthority");
	}

	public MemberVo get(String username) {
		return sqlSession.selectOne("member.getByUsername", username);
	}
	
	public MemberVo get(MemberVo memberVo) {
		return sqlSession.selectOne("member.getByUsernameAndPassword", memberVo);
	}
	
	public MemberVo get(Long no) {
		return sqlSession.selectOne("member.getOne", no);
	}

	public Boolean update(MemberVo memberVo) {
		return 1 == sqlSession.update("member.update", memberVo);
	}

	public Boolean delete(Long no) {
		return 1 == sqlSession.update("member.updateDelStatus", no);
	}

	public MemberVo getToAdmin(Long no) {
		return sqlSession.selectOne("member.getOneToAdmin", no);
	}

	public List<MemberVo> searchToAdmin(HashMap<String, String> paramMap) {
		return sqlSession.selectList("member.searchToAdmin", paramMap);
	}

	public Boolean updateToAdmin(MemberVo memberVo) {
		return 1 == sqlSession.update("member.updateToAdmin", memberVo);
	}
}

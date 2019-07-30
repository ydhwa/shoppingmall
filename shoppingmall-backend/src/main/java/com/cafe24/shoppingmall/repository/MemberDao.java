package com.cafe24.shoppingmall.repository;

import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.shoppingmall.vo.MemberVo;

/**
 * 회원에 대한 DAO
 * 
 * @author YDH
 *
 */
@Repository
public class MemberDao {

	@Autowired
	private SqlSession sqlSession;
	@Autowired
	private DataSource dataSource;

	// 회원 추가
	public Boolean insert(MemberVo memberVo) {
		return 1 == sqlSession.insert("member.insert", memberVo);
	}
	// 권한 테이블에 USER_ROLE 권한과 함께 회원 정보 등록
	// 회원 추가 작업 이후에 곧장 실행됨. currval() 때문에 파라미터 넣을 필요 없다.
	public Boolean insertAuthority() {
		return 1 == sqlSession.insert("member.insertAuthority");
	}

	// 회원 아이디로 조회
	// 아이디 중복 검사 시 사용한다.
	public MemberVo get(String username) {
		return sqlSession.selectOne("member.getByUsername", username);
	}
	
	// 회원 아이디와 비밀번호로 조회
	// 로그인 시 사용한다.
	public MemberVo get(MemberVo memberVo) {
		return sqlSession.selectOne("member.getByUsernameAndPassword", memberVo);
	}
	
	// 회원 번호로 조회
	public MemberVo get(Long no) {
		return sqlSession.selectOne("member.getOne", no);
	}

	// 회원 정보 수정
	public Boolean update(MemberVo memberVo) {
		return 1 == sqlSession.update("member.update", memberVo);
	}

	// 회원 삭제
	// 정확히는 삭제 상태만 활성화시킨다.
	// 시간이 나면 회원과 관련된 정보를 더 이상은 열람할 수 없도록 쓰레기 값을 넣는 등의 기능을 추가해야 한다.
	public Boolean delete(Long no) {
		return 1 == sqlSession.update("member.updateDelStatus", no);
	}

	// 관리자가 회원 번호로 조회 (더 많은 정보를 열람할 수 있다.)
	public MemberVo getToAdmin(Long no) {
		return sqlSession.selectOne("member.getOneToAdmin", no);
	}

	// 관리자가 회원 정보 검색
	public List<MemberVo> searchToAdmin(HashMap<String, String> paramMap) {
		return sqlSession.selectList("member.searchToAdmin", paramMap);
	}

	// 관리자가 회원 정보 수정(회원이 변경할 수 없는 정보까지 변경할 수 있다.)
	public Boolean updateToAdmin(MemberVo memberVo) {
		return 1 == sqlSession.update("member.updateToAdmin", memberVo);
	}
}

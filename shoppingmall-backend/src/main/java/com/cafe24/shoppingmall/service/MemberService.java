package com.cafe24.shoppingmall.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.shoppingmall.repository.MemberDao;
import com.cafe24.shoppingmall.vo.Enum.MemberStatus;
import com.cafe24.shoppingmall.vo.MemberVo;

/**
 * 회원에 대한 비즈니스 로직이 담긴 서비스.
 * 
 * @author YDH
 *
 */
@Service
public class MemberService {
	
	@Autowired
	private MemberDao memberDao;
	
	/**
	 * 회원가입
	 * 
	 * @param memberVo 회원가입 할 고객의 회원 정보
	 * @return 회원가입 성공여부
	 */
	@Transactional
	public Boolean join(MemberVo memberVo) {
		// 남은 데이터 등록(회원번호, 등록일은 데이터베이스 단에서 처리)
		memberVo.setStatus(MemberStatus.ENABLE);
		
		// 회원가입 이후 권한 테이블에 ROLE_USER 생김
		return memberDao.insert(memberVo) && memberDao.insertAuthority();
	}
	
	/**
	 * 유저네임(아이디) 중복 체크
	 * 
	 * @param memberVo 아이디만 담겨 있는 회원 정보
	 * @return 중복 아이디 존재 여부(true/false)
	 */
	public Boolean checkUsernameDuplication(String username) {
		return memberDao.get(username) != null;
	}
	
	/**
	 * 로그인
	 * 
	 * @param memberVo username과 password 가 담겨 있는 회원 정보
	 * @return 로그인에 성공한 회원의 간략한 정보(번호, 아이디, 이름) 
	 */
	public MemberVo login(MemberVo memberVo) {
		return memberDao.get(memberVo);
	}
	
	// 회원 상세정보 열람(회원용)
	public MemberVo showMemberDetails(Long no) {
		return memberDao.get(no);
	}

	// 회원 수정
	public Boolean modify(MemberVo memberVo) {
		return memberDao.update(memberVo);
	}
	
	// 회원 삭제
	public Boolean delete(Long no) {
		return memberDao.delete(no);
	}

	// 회원 상세정보 열람(관리자용)
	public MemberVo showMemberDetailsToAdmin(Long no) {
		return memberDao.getToAdmin(no);
	}

	// 회원목록 검색결과 열람(관리자용)
	public List<MemberVo> searchMembersToAdmin(HashMap<String, String> paramMap) {
		return memberDao.searchToAdmin(paramMap);
	}

	// 회원 수정(관리자용)
	public Boolean modifyToAdmin(MemberVo memberVo) {
		return memberDao.updateToAdmin(memberVo);
	}
	
	

}

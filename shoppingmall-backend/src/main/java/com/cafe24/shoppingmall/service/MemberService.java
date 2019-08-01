package com.cafe24.shoppingmall.service;

import java.util.HashMap;
import java.util.List;

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
	
	// 회원가입
	@Transactional
	public Boolean join(MemberVo memberVo) {
		// 남은 데이터 등록(회원번호, 등록일은 데이터베이스 단에서 처리)
		memberVo.setStatus(MemberStatus.ENABLE);
		
		// 회원가입 이후 권한 테이블에 ROLE_USER 생김
		return memberDao.insert(memberVo) && memberDao.insertAuthority();
	}
	
	// 유저네임(아이디) 중복 체크
	public Boolean checkUsernameDuplication(String username) {
		// 중복 아이디가 존재하면 true, 존재하지 않으면 false를 반환한다.
		return memberDao.get(username) != null;
	}
	
	// 로그인
	public MemberVo login(MemberVo memberVo) {
		return memberDao.get(memberVo);
	}
	
	// 회원 상세정보 열람(회원용)
	public MemberVo showMemberDetails(Long no) {
		return memberDao.get(no);
	}

	// 회원 수정(회원용)
	public Boolean modify(MemberVo memberVo) {
		return memberDao.update(memberVo);
	}
	
	// 회원 삭제(회원용)
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

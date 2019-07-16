package com.cafe24.shoppingmall.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.cafe24.shoppingmall.vo.MemberVo;
import com.cafe24.shoppingmall.vo.MemberVo.MemberRole;
import com.cafe24.shoppingmall.vo.MemberVo.MemberStatus;

/**
 * 회원에 대한 간단한 서비스.
 * 일단 여기에 임시 데이터를 등록해두고, 컨트롤러가 잘 동작하는지 확인한다.
 * 
 * @author YDH
 *
 */
@Service
public class MemberService {

	// 테스트용 데이터
	private List<MemberVo> memberList;
	@PostConstruct
	public void init() {
		memberList = new ArrayList<MemberVo>();
		memberList.add(new MemberVo(1L, "user", "asdf1234!", "2019-07-13", "기본유저", "1980-01-01", "031-111-1111", "010-1111-1111", "user1@test1.com", MemberStatus.ENABLE, MemberRole.USER));
		memberList.add(new MemberVo(2L, "testuser", "aaaa111!", "2019-07-15", "유저", "1990-01-01", "032-222-2222", "011-2222-2222", "test@email.com", MemberStatus.ENABLE, MemberRole.USER));
	}
	
	/**
	 * 회원가입
	 * 
	 * @param memberVo 회원가입 할 고객의 회원 정보
	 * @return 회원가입 된 고객의 회원의 아이디만 담긴 정보
	 */
	public Object join(MemberVo memberVo) {
		// 남은 데이터 등록(회원번호, 등록일은 데이터베이스 단에서 처리)
		memberVo.setStatus(MemberStatus.ENABLE);
		memberVo.setRole(MemberRole.USER);
		
		memberList.add(memberVo);
		
		MemberVo joinMemberVo = new MemberVo();
		joinMemberVo.setUsername(memberVo.getUsername());
		
		// memberDao.insert(memberVo);
		
		return joinMemberVo;
	}
	
	/**
	 * 유저네임(아이디) 중복 체크
	 * 
	 * @param memberVo 아이디만 담겨 있는 회원 정보
	 * @return 중복 아이디 존재 여부(true/false)
	 */
	public Boolean checkUsernameDuplication(String username) {
		for(MemberVo member: memberList) {
			if(username.equals(member.getUsername())) {
				return true;
			}
		}
		
		// memberDao.existUsername(memberVo);
		
		return false;
	}
	
	/**
	 * 로그인
	 * 
	 * @param memberVo username과 password 가 담겨 있는 회원 정보
	 * @return 로그인에 성공한 회원의 간략한 정보(번호, 아이디, 이름) 
	 */
	public Object login(MemberVo memberVo) {
		for(MemberVo member: memberList) {
			if(memberVo.getUsername().equals(member.getUsername()) && memberVo.getPassword().equals(member.getPassword())) {
				MemberVo loginMemberVo = new MemberVo();
				loginMemberVo.setNo(member.getNo());
				loginMemberVo.setUsername(member.getUsername());
				loginMemberVo.setName(member.getName());
				return loginMemberVo;
			}
		}
		
		// memberDao.existMember(memberVo);
		
		return null;
	}

}

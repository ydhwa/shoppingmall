package com.cafe24.shoppingmall.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.cafe24.shoppingmall.exception.Message;
import com.cafe24.shoppingmall.vo.MemberLoginVo;
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
		memberList.add(new MemberVo("user", "asdf1234!", "기본유저", "1111-01-01", "031-111-1111", "010-1111-1111", "user1@test1.com", true, false));
		memberList.add(new MemberVo("testuser", "aaaa111!", "유저", "2222-02-02", "032-222-2222", "011-2222-2222", "test@email.com", true, false));
	}
	
	/**
	 * 회원가입
	 * 
	 * @param memberVo 회원가입 할 고객의 회원 정보
	 * @return 회원가입 된 고객의 회원 정보
	 */
	public Object join(MemberVo memberVo) {
		
		// 남은 데이터 등록(회원번호, 등록일은 데이터베이스 단에서 처리)
		memberVo.setSavings(0);
		memberVo.setStatus(MemberStatus.ENABLE);
		memberVo.setRole(MemberRole.USER);
		
		memberList.add(memberVo);
		
		// memberDao.insert(memberVo);
		
		return memberVo;
	}
	
	/**
	 * 유저네임(아이디) 중복 체크
	 * 
	 * @param memberVo 아이디만 담겨 있는 회원 정보
	 * @return 상태 메시지(유니크하다/중복되어있다)
	 */
	public String checkUsername(String username) {
		for(MemberVo member: memberList) {
			if(username.equals(member.getUsername())) {
				return Message.USERNAME_DUPLICATED.toString();
			}
		}
		
		// memberDao.existUsername(memberVo);
		
		return Message.USERNAME_UNIQUE.toString();
	}
	
	/**
	 * 로그인
	 * 
	 * @param memberVo username과 password 가 담겨 있는 회원 정보
	 * @return 상태 메시지(로그인 성공/실패)
	 */
	public String login(MemberLoginVo memberVo) {
		for(MemberVo member: memberList) {
			if(memberVo.getUsername().equals(member.getUsername()) && memberVo.getPassword().equals(member.getPassword())) {
				return Message.LOGIN_SUCCESS.toString();
			}
		}
		
		// memberDao.existMember(memberVo);
		
		return Message.LOGIN_FAILURE.toString();
	}

}

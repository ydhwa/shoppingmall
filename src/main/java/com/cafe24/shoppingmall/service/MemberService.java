package com.cafe24.shoppingmall.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.shoppingmall.exception.Message;
import com.cafe24.shoppingmall.vo.MemberCheckDuplicateVo;
import com.cafe24.shoppingmall.vo.MemberJoinVo;
import com.cafe24.shoppingmall.vo.MemberJoinVo.MemberRole;
import com.cafe24.shoppingmall.vo.MemberJoinVo.MemberStatus;
import com.cafe24.shoppingmall.vo.MemberLoginVo;

@Service
public class MemberService {

	// 테스트용 데이터
	private List<MemberJoinVo> memberList;
	@PostConstruct
	public void init() {
		memberList = new ArrayList<MemberJoinVo>();
		memberList.add(new MemberJoinVo("user", "asdf1234!", "기본유저", "1111-01-01", "031-111-1111", "010-1111-1111", "user1@test1.com", true, false));
		memberList.add(new MemberJoinVo("testuser", "aaaa111!", "유저", "2222-02-02", "032-222-2222", "011-2222-2222", "test@email.com", true, false));
	}
	
	public Object join(MemberJoinVo memberVo) {
		
		// 남은 데이터 등록(회원번호, 등록일은 데이터베이스 단에서 처리)
		memberVo.setSavings(0);
		memberVo.setStatus(MemberStatus.ENABLE);
		memberVo.setRole(MemberRole.USER);
		
		memberList.add(memberVo);
		
		// memberDao.insert(memberVo);
		
		return memberVo;
	}
	
	public String checkUsername(MemberCheckDuplicateVo memberVo) {
		for(MemberJoinVo member: memberList) {
			if(memberVo.getUsername().equals(member.getUsername())) {
				return Message.USERNAME_DUPLICATED.toString();
			}
		}
		
		// memberDao.existUsername(memberVo);
		
		return Message.USERNAME_UNIQUE.toString();
	}
	
	public String login(MemberLoginVo memberVo) {
		for(MemberJoinVo member: memberList) {
			if(memberVo.getUsername().equals(member.getUsername()) && memberVo.getPassword().equals(member.getPassword())) {
				return Message.LOGIN_SUCCESS.toString();
			}
		}
		
		// memberDao.existMember(memberVo);
		
		return Message.LOGIN_FAILURE.toString();
	}

}

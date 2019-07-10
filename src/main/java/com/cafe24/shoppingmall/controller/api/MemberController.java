package com.cafe24.shoppingmall.controller.api;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shoppingmall.dto.JSONResult;
import com.cafe24.shoppingmall.vo.MemberVo;

@RestController("memberAPIController")
@RequestMapping("/api/member")
public class MemberController {

	@RequestMapping(value="/join", method=RequestMethod.POST)
	public JSONResult join(@RequestBody MemberVo memberVo) {
		return JSONResult.success(memberVo);
	}
}

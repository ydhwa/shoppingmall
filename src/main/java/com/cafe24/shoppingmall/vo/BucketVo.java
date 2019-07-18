package com.cafe24.shoppingmall.vo;

import java.util.List;

/**
 * 장바구니 관련 작업 시 사용하는 장바구니 VO
 * 
 * @author YDH
 *
 */
public class BucketVo {
	private Long no;				// 장바구니 번호
	private String sessionId;		// 비회원용 식별자 - 세션 아이디
	private Long memberNo;			// 회원용 식별자 - 회원 번호
	private List<BucketItemVo> buekctItemList;	// 장바구니 상품 목록
	
	public BucketVo() {}
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public Long getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(Long memberNo) {
		this.memberNo = memberNo;
	}
	public List<BucketItemVo> getBuekctItemList() {
		return buekctItemList;
	}
	public void setBuekctItemList(List<BucketItemVo> buekctItemList) {
		this.buekctItemList = buekctItemList;
	}
	
	@Override
	public String toString() {
		return "BucketVo [no=" + no + ", sessionId=" + sessionId + ", memberNo=" + memberNo + ", buekctItemList="
				+ buekctItemList + "]";
	}
}

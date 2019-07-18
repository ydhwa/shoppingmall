package com.cafe24.shoppingmall.vo;

import java.util.List;

/**
 * 장바구니 관련 작업 시 사용하는 장바구니 VO
 * 
 * @author YDH
 *
 */
public class BucketVo {
	private String identifier;		// 고객 식별자
	private Long memberNo;			// 회원용 식별자 - 회원 번호
	private List<BucketItemVo> buekctItemList;	// 장바구니 상품 목록
	
	public BucketVo() {}
	
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
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
		return "BucketVo [identifier=" + identifier + ", memberNo=" + memberNo + ", buekctItemList="
				+ buekctItemList + "]";
	}
}

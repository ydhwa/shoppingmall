package com.cafe24.shoppingmall.vo;

import java.util.List;

import com.cafe24.shoppingmall.vo.Enum.OrdersStatus;

/**
 * 주문, 주문조회/관리 시 사용하는 주문 VO
 * 
 * @author YDH
 *
 */
public class OrdersVo {
	private Long no;					// 주문번호
	private String code;				// 주문코드
	private String date;				// 주문일
	private String memo;				// 메모
	private OrdersStatus status;		// 주문상태
	
	private String ordererName;				// 주문자 이름
	private String ordererHomeNumber;		// 주문자 유선전화번호
	private String ordererPhoneNumber;		// 주문자 휴대전화번호
	private String ordererEmail;			// 주문자 이메일
	private String ordererPostalCode;		// 주문자 우편번호
	private String ordererBaseAddress;		// 주문자 기본주소
	private String ordererDetailAddress;	// 주문자 상세주소
	
	private String receiverName;			// 수령자 이름
	private String receiverHomeNumber;		// 수령자 유선전화번호
	private String receiverPhoneNumber;		// 수령자 휴대전화번호
	private String receiverPostalCode;		// 수령자 우편번호
	private String receiverBaseAddress;		// 수령자 기본주소
	private String receiverDetailAddress;	// 수령자 상세주소
	
	private Integer totalOrderAccount;	// 주문총액(배송비 포함)
	
	private Long memberNo;				// (회원용)회원번호
	private String password;			// (비회원용)주문조회 비밀번호
	
	private List<OrdersItemVo> ordersItemList;	// 주문 상품 목록

	public OrdersVo() {}
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public OrdersStatus getStatus() {
		return status;
	}
	public void setStatus(OrdersStatus status) {
		this.status = status;
	}
	public String getOrdererName() {
		return ordererName;
	}
	public void setOrdererName(String ordererName) {
		this.ordererName = ordererName;
	}
	public String getOrdererHomeNumber() {
		return ordererHomeNumber;
	}
	public void setOrdererHomeNumber(String ordererHomeNumber) {
		this.ordererHomeNumber = ordererHomeNumber;
	}
	public String getOrdererPhoneNumber() {
		return ordererPhoneNumber;
	}
	public void setOrdererPhoneNumber(String ordererPhoneNumber) {
		this.ordererPhoneNumber = ordererPhoneNumber;
	}
	public String getOrdererEmail() {
		return ordererEmail;
	}
	public void setOrdererEmail(String ordererEmail) {
		this.ordererEmail = ordererEmail;
	}
	public String getOrdererPostalCode() {
		return ordererPostalCode;
	}
	public void setOrdererPostalCode(String ordererPostalCode) {
		this.ordererPostalCode = ordererPostalCode;
	}
	public String getOrdererBaseAddress() {
		return ordererBaseAddress;
	}
	public void setOrdererBaseAddress(String ordererBaseAddress) {
		this.ordererBaseAddress = ordererBaseAddress;
	}
	public String getOrdererDetailAddress() {
		return ordererDetailAddress;
	}
	public void setOrdererDetailAddress(String ordererDetailAddress) {
		this.ordererDetailAddress = ordererDetailAddress;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getReceiverHomeNumber() {
		return receiverHomeNumber;
	}
	public void setReceiverHomeNumber(String receiverHomeNumber) {
		this.receiverHomeNumber = receiverHomeNumber;
	}
	public String getReceiverPhoneNumber() {
		return receiverPhoneNumber;
	}
	public void setReceiverPhoneNumber(String receiverPhoneNumber) {
		this.receiverPhoneNumber = receiverPhoneNumber;
	}
	public String getReceiverPostalCode() {
		return receiverPostalCode;
	}
	public void setReceiverPostalCode(String receiverPostalCode) {
		this.receiverPostalCode = receiverPostalCode;
	}
	public String getReceiverBaseAddress() {
		return receiverBaseAddress;
	}
	public void setReceiverBaseAddress(String receiverBaseAddress) {
		this.receiverBaseAddress = receiverBaseAddress;
	}
	public String getReceiverDetailAddress() {
		return receiverDetailAddress;
	}
	public void setReceiverDetailAddress(String receiverDetailAddress) {
		this.receiverDetailAddress = receiverDetailAddress;
	}
	public Integer getTotalOrderAccount() {
		return totalOrderAccount;
	}
	public void setTotalOrderAccount(Integer totalOrderAccount) {
		this.totalOrderAccount = totalOrderAccount;
	}
	public Long getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(Long memberNo) {
		this.memberNo = memberNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<OrdersItemVo> getOrdersList() {
		return ordersItemList;
	}
	public void setOrdersList(List<OrdersItemVo> ordersItemList) {
		this.ordersItemList = ordersItemList;
	}
	
	@Override
	public String toString() {
		return "OrdersVo [no=" + no + ", code=" + code + ", date=" + date + ", memo=" + memo + ", status="
				+ status + ", ordererName=" + ordererName + ", ordererHomeNumber=" + ordererHomeNumber
				+ ", ordererPhoneNumber=" + ordererPhoneNumber + ", ordererEmail=" + ordererEmail
				+ ", ordererPostalCode=" + ordererPostalCode + ", ordererBaseAddress=" + ordererBaseAddress
				+ ", ordererDetailAddress=" + ordererDetailAddress + ", receiverName=" + receiverName
				+ ", receiverHomeNumber=" + receiverHomeNumber + ", receiverPhoneNumber=" + receiverPhoneNumber
				+ ", receiverPostalCode=" + receiverPostalCode + ", receiverBaseAddress=" + receiverBaseAddress
				+ ", receiverDetailAddress=" + receiverDetailAddress + ", totalOrderAccount=" + totalOrderAccount
				+ ", memberNo=" + memberNo + ", password=" + password + ", ordersItemList=" + ordersItemList + "]";
	}
}

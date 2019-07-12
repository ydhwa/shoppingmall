package com.cafe24.shoppingmall.vo;

public class OrdersVo {
	private Long no;					// 주문번호
	private String code;				// 주문코드
	private String date;				// 주문일
	private String memo;				// 메모
	private OrdersStatus ordersStatus;	// 주문상태
	
	private String ordererName;			// 주문자 이름
	private String ordererHomeNumber;	// 주문자 유선전화번호
	private String ordererPhoneNumber;	// 주문자 휴대전화번호
	private String ordererEmail;		// 주문자 이메일
	private String ordererAddress;		// 주문자 주소
	
	private String receiverName;		// 수령자 이름
	private String receiverHomeNumber;	// 수령자 유선전화번호
	private String receiverPhoneNumber;	// 수령자 휴대전화번호
	private String receiverAddress;		// 수령자 주소
	
	private Integer totalOrderAccount;	// 주문총액(배송비 포함)
	
	private Long memberNo;				// (회원용)회원번호
	private String password;			// (비회원용)주문조회 비밀번호
	
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
	public OrdersStatus getOrdersStatus() {
		return ordersStatus;
	}
	public void setOrdersStatus(OrdersStatus ordersStatus) {
		this.ordersStatus = ordersStatus;
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
	public String getOrdererAddress() {
		return ordererAddress;
	}
	public void setOrdererAddress(String ordererAddress) {
		this.ordererAddress = ordererAddress;
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
	public String getReceiverAddress() {
		return receiverAddress;
	}
	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
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
	
	@Override
	public String toString() {
		return "OrdersVo [no=" + no + ", code=" + code + ", date=" + date + ", memo=" + memo + ", ordersStatus="
				+ ordersStatus + ", ordererName=" + ordererName + ", ordererHomeNumber=" + ordererHomeNumber
				+ ", ordererPhoneNumber=" + ordererPhoneNumber + ", ordererEmail=" + ordererEmail + ", ordererAddress="
				+ ordererAddress + ", receiverName=" + receiverName + ", receiverHomeNumber=" + receiverHomeNumber
				+ ", receiverPhoneNumber=" + receiverPhoneNumber + ", receiverAddress=" + receiverAddress
				+ ", totalOrderAccount=" + totalOrderAccount + ", memberNo=" + memberNo + ", password=" + password
				+ "]";
	}

	public enum OrdersStatus {
		
	}
}

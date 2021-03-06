<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="">
	<meta name="author" content="">
	<title>Shop Homepage - Start Bootstrap Template</title>
	<!-- Bootstrap core CSS -->
	<link href="${pageContext.servletContext.contextPath }/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<!-- Custom styles for this template -->
	<link href="${pageContext.servletContext.contextPath }/assets/css/shop-homepage.css" rel="stylesheet">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
	<script src="${ pageContext.servletContext.contextPath }/assets/js/jquery/jquery.min.js"></script>
	
	<style type="text/css">
		/* 꽉차게! */
		html, body {
			height: 93.5%;
		}
		.container {
			min-height: 95%;
		}
		caption {
			caption-side: top !important;
			color: black !important;
			font-size: 1.5em;
			font-weight: bolder;
		}
		table {
			font-size: 0.8em;
		}
		.required {
			color: red;
		}
	</style>
	
	<script type="text/javascript">
		var bucketList = new Array();
		
		$(function() {
			$('#buy').click(function() {
				var ordersItemList = new Array();
				for(var i = 0; i < bucketList.length; i++) {
					var ordersItem = {
						'memberNo': bucketList[i].memberNo,
						'identifier': bucketList[i].identifier,
						'productOptionItemNo': bucketList[i].productOptionItemNo,
						'quantity': bucketList[i].quantity
					}
					ordersItemList.push(ordersItem);
				}
				var orders = new Object();
				
				orders.ordererName = $('#ordererName').val();
				orders.ordererPostalCode = $('#ordererPostalCode').val();
				orders.ordererBaseAddress = $('#ordererBaseAddress').val();
				orders.ordererDetailAddress = $('#ordererDetailAddress').val();
				orders.ordererHomeNumber = $('#ordererHomeNumber1').val() + '-' + $('#ordererHomeNumber2').val() + '-' + $('#ordererHomeNumber3').val();
				orders.ordererPhoneNumber = $('#ordererPhoneNumber1').val() + '-' + $('#ordererPhoneNumber2').val() + '-' + $('#ordererPhoneNumber3').val();
				orders.ordererEmail = $('#ordererEmail1').val() + '@' + $('#ordererEmail2').val();
				orders.memberNo = bucketList[0].memberNo == '' ? 0 : bucketList[0].memberNo;
				orders.password = $('#password').val();
				orders.memberStatus = orders.memberNo == 0 ? 'N' : 'Y';
				orders.status = 'ORDER_COMPLETE';
					
				orders.receiverName = $('#receiverName').val();
				orders.receiverPostalCode = $('#receiverPostalCode').val();
				orders.receiverBaseAddress = $('#receiverBaseAddress').val();
				orders.receiverDetailAddress = $('#receiverDetailAddress').val();
				orders.receiverHomeNumber = $('#receiverHomeNumber1').val() + '-' + $('#receiverHomeNumber2').val() + '-' + $('#receiverHomeNumber3').val();
				orders.receiverPhoneNumber = $('#receiverPhoneNumber1').val() + '-' + $('#receiverPhoneNumber2').val() + '-' + $('#receiverPhoneNumber3').val();
					
				orders.memo = $('#memo').val();
				
				// 간단하게 필수 사항 체크
				if(
					orders.ordererName == '' ||
					orders.ordererPostalCode == '' ||
					orders.ordererBaseAddress == '' ||
					orders.ordererDetailAddress == '' ||
					orders.ordererPhoneNumber == '--' ||
					orders.ordererEmail == '' ||
					orders.ordererPhoneNumber == '--' ||
					(orders.memberNo == 0 && orders.password == '' && orders.password != $('#passwordCheck').val()) ||
					orders.receiverName == '' ||
					orders.receiverPostalCode == '' ||
					orders.receiverBaseAddress == '' ||
					orders.receiverDetailAddress == '' ||
					orders.receiverPhoneNumber == '') {
					alert('필수 입력 사항을 전부 입력해주세요!');
					return ;
				}
				
				var ordersMap = {
					'ordersItemList': ordersItemList,
					'orders': orders
				}
				
				$.ajax({
					url: '${ pageContext.servletContext.contextPath }/order/regist',
					type: 'post',
					dataType: 'json',
					data: JSON.stringify(ordersMap),
					contentType: 'application/json',
			       	success: function(response) {
			       		$('.orderform').empty();
			       		
			       		var html = 
			       			'<div>' + 
			       			'<h1>성공적으로 주문하였습니다.</h1>' + 
			       			'<h3>주문 코드는 "' + response.data + '" 입니다.</h3>' + 
			       			'<h3 style="color: red;">비회원 주문의 경우, 주문 내역 확인을 위해 비밀번호와 함께 필수로 기억하고 계셔야 합니다. 분실 시 책임지지 않습니다.</h3>' + 
			       			'<a href="${ pageContext.servletContext.contextPath }">메인화면으로 돌아가기</a>' + 
			       			'</div>';
			       			
			       		$('.orderform').append(html);
					},
					error: function(jqXHR, status, e) {
						console.error('[ERROR] ' + status + ': ' + e);
					}
				});
			});
		})
		
	</script>

</head>
<body>
	<!-- Navigation -->
	<c:import url='/WEB-INF/views/includes/navigation.jsp'>
	</c:import>
	<!-- /.Navigation -->

	<!-- Page Content -->
	<div class="container">
		<div class="row" style="margin-bottom: 5%;">

			<div class="col-lg-3">
				<a href="${pageContext.servletContext.contextPath }" style="color: black; text-decoration: none;"><h1 class="my-4" style="letter-spacing: 3px; font-family: consolas; text-transform: uppercase; text-align: center;">Shopping<br>Mall</h1></a>
				<div class="list-group">
					<c:forEach var="category" items="${ categories }">
						<a href="${ pageContext.servletContext.contextPath }/list?categoryNo=${ category.no }" class="list-group-item" style="padding-left: ${ category.level * 15 + 20 }px;">
							<c:if test="${ category.level > 0 }">
								<img src="">
							</c:if>
							<c:out value="${ category.name }" />
						</a>
					</c:forEach>
				</div>
			</div>
			<!-- /.col-lg-3 -->

			<div class="col-lg-9 orderform">

				<div class="card mt-4">
					<div class="card-body">
						<h3 class="card-title">주문서 작성</h3>
						<table class="table table-borderless">
							<caption>주문내역</caption>
							<tbody>
								<tr>
									<th>상품정보</th>
									<th>판매가</th>
									<th>수량</th>
									<th>합계</th>
								</tr>
							
								<!-- 여기에 forEach문으로 장바구니 내의 물품들을 뽑아내야 한다. -->
								<c:forEach var="item" items="${ orderList }" varStatus="status">
									<tr>
										<input type="hidden" value="${ status.index }">
										<td>
											<span style="font-weight: bold;">
												<a href="${ pageContext.servletContext.contextPath }/product/${ item.productNo }"><c:out value="${ item.productName }" /></a>
											</span><br>
											<span style="font-size: 0.9em; color: #787878;">
												[옵션: <c:out value="${ fn:replace(item.productOptionDetails, ';', '/') }" />]
											</span>
										</td>
										<td>
											<fmt:formatNumber value="${ item.sellPrice }" pattern="#,###" />원
										</td>
										<td style="max-width: 100px;">
											<c:out value="${ item.quantity }" />
										</td>
										<td>
											<fmt:formatNumber value="${ item.sellPrice * item.quantity }" pattern="#,###" />원
										</td>
									</tr>
									
									<script>
										var bucketItem = {
											'sellPrice': '${ item.sellPrice }',
											'quantity': '${ item.quantity }',
											'productNo': '${ item.productNo }',
											'productOptionItemNo': '${ item.productOptionItemNo }',
											'productOptionDetails': '${ item.productOptionDetails }',
											'memberNo': '${ item.memberNo }',
											'identifier': '${ item.identifier }'
										};
										bucketList.push(bucketItem);
									</script>
								</c:forEach>
								
								<tr>
									<td colspan="6" class="text-right" style="padding-right: 5%;">
										<h6>총 상품금액: 
											<span style="color: #AF4848;">
												<fmt:formatNumber value="${ totalPrice }" pattern="#,###" />원
											</span>
										</h6>
									</td>
								</tr>						
							</tbody>
						</table>
						
						<hr>
						
						<form class="form-inline">
						<table class="table table-bordered">
							<caption>주문 정보</caption>
							<tbody>
								<tr>
									<th colspan="2" class="text-right" style="font-size: 0.8em;"><span class="required">*</span>&nbsp;필수 입력 사항</th>
								</tr>
								<tr>
									<th style="width: 150px;">주문하시는 분<span class="required">&nbsp;*</span></th>
									<td>
										<input type="text" class="form-control form-control-sm" name="ordererName" id="ordererName">
									</td>
								</tr>
								<tr>
									<th>주소<span class="required">&nbsp;*</span></th>
									<td>
										<input type="text" class="form-control form-control-sm" name="ordererPostalCode" id="ordererPostalCode">
										<button type="button" class="btn btn-sm btn-light">
											우편번호
										</button><br>
										<input type="text" class="form-control form-control-sm" name="ordererBaseAddress" id="ordererBaseAddress">기본주소<br>
										<input type="text" class="form-control form-control-sm" name="ordererDetailAddress" id="ordererDetailAddress">나머지주소
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
            // 예제를 참고하여 다양한 활용법을 확인해 보세요.
        }
    });
</script>
									</td>
								</tr>
								<tr>
									<th>일반전화</th>
									<td>
										<input type="text" class="form-control form-control-sm" name="ordererHomeNumber1" id="ordererHomeNumber1">-
										<input type="text" class="form-control form-control-sm" name="ordererHomeNumber2" id="ordererHomeNumber2">-
										<input type="text" class="form-control form-control-sm" name="ordererHomeNumber3" id="ordererHomeNumber3">
									</td>
								</tr>
								<tr>
									<th>휴대전화<span class="required">&nbsp;*</span></th>
									<td>
										<input type="text" class="form-control form-control-sm" name="ordererPhoneNumber1" id="ordererPhoneNumber1">-
										<input type="text" class="form-control form-control-sm" name="ordererPhoneNumber2" id="ordererPhoneNumber2">-
										<input type="text" class="form-control form-control-sm" name="ordererPhoneNumber3" id="ordererPhoneNumber3">
									</td>
								</tr>
								<tr>
									<th>이메일<span class="required">&nbsp;*</span></th>
									<td>
										<input type="text" class="form-control form-control-sm" name="ordererEmail1" id="ordererEmail1">@
										<input type="text" class="form-control form-control-sm" name="ordererEmail2" id="ordererEmail2">
									</td>
								</tr>
								<sec:authorize access="!isAuthenticated()">
									<tr>
										<th>주문조회 비밀번호<span class="required">&nbsp;*</span></th>
										<td>
											<input type="password" class="form-control form-control-sm" name="password" id="password">
										</td>
									</tr>
									<tr>
										<th>주문조회 비밀번호<br>확인<span class="required">&nbsp;*</span></th>
										<td>
											<input type="password" class="form-control form-control-sm" name="passwordCheck" id="passwordCheck">
										</td>
									</tr>
								</sec:authorize>
							</tbody>
						</table>
						
						<table class="table table-bordered">
							<caption>배송 정보</caption>
							<tbody>
								<tr>
									<th style="width: 150px;">배송지 선택</th>
									<td>
										<div class="custom-control custom-radio custom-control-inline">
											<input type="radio" class="custom-control-input" id="receivePlaceOrderer" name="receivePlace" value="ORDERER">
											<label class="custom-control-label" for="receivePlaceOrderer">주문자 정보와 동일</label>
										</div>
										<div class="custom-control custom-radio custom-control-inline">
											<input type="radio" class="custom-control-input" id="receivePlaceNew" name="receivePlace" value="NEW" checked>
											<label class="custom-control-label" for="receivePlaceNew">새로운 배송지</label>
										</div>
									</td>
								</tr>
								<tr>
									<th>받으시는 분<span class="required">&nbsp;*</span></th>
									<td>
										<input type="text" class="form-control form-control-sm" name="receiverName" id="receiverName">
									</td>
								</tr>
								<tr>
									<th>주소<span class="required">&nbsp;*</span></th>
									<td>
										<input type="text" class="form-control form-control-sm" name="receiverPostalCode" id="receiverPostalCode">
										<button type="button" class="btn btn-sm btn-light">
											우편번호
										</button><br>
										<input type="text" class="form-control form-control-sm" name="receiverBaseAddress" id="receiverBaseAddress">기본주소<br>
										<input type="text" class="form-control form-control-sm" name="receiverDetailAddress" id="receiverDetailAddress">나머지주소
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
            // 예제를 참고하여 다양한 활용법을 확인해 보세요.
        }
    });
</script>										
									</td>
								</tr>
								<tr>
									<th>일반전화</th>
									<td>
										<input type="text" class="form-control form-control-sm" name="receiverHomeNumber1" id="receiverHomeNumber1">-
										<input type="text" class="form-control form-control-sm" name="receiverHomeNumber2" id="receiverHomeNumber2">-
										<input type="text" class="form-control form-control-sm" name="receiverHomeNumber3" id="receiverHomeNumber3">
									</td>
								</tr>
								<tr>
									<th>휴대전화<span class="required">&nbsp;*</span></th>
									<td>
										<input type="text" class="form-control form-control-sm" name="receiverPhoneNumber1" id="receiverPhoneNumber1">-
										<input type="text" class="form-control form-control-sm" name="receiverPhoneNumber2" id="receiverPhoneNumber2">-
										<input type="text" class="form-control form-control-sm" name="receiverPhoneNumber3" id="receiverPhoneNumber3">
									</td>
								</tr>
								<tr>
									<th>배송메시지</th>
									<td>
										<textarea rows="5" class="form-control" name="memo" id="memo"></textarea>
									</td>
								</tr>
							</tbody>
						</table>
						</form>
						<hr>
						
						<table class="table">
							<caption>결제 예정 금액</caption>
							<tbody>
								<tr>
									<th>총 주문 금액</th>
									<th>총 할인 + 부가결제 금액</th>
									<th>총 결제예정 금액</th>
								</tr>
								<tr>
									<td><fmt:formatNumber value="${ totalPrice }" pattern="#,###" />원</td>
									<td>0원</td>
									<td><fmt:formatNumber value="${ totalPrice - 0 }" pattern="#,###" />원</td>
								</tr>
							</tbody>
						</table>
						
						<table class="table">
							<caption>결제수단</caption>
							<tr>
								<th>결제</th>
								<th style="width: 200px;" class="text-right">최종 결제 금액</th>
							</tr>
							<tr>
								<td>아직 미구현</td>
								<td style="color: red;" class="text-right">
									<b style="font-size: 2em;">
										<fmt:formatNumber value="${ totalPrice }" pattern="#,###" />
									</b>원<br>
									<button type="button" class="btn btn-sm btn-dark btn-block" id="buy" style="font-size: 0.9em;">
										<br>결제하기<br><br>
									</button>
									
								</td>
							</tr>
						</table>

					</div>
				</div>


			</div>
			<!-- /.col-lg-9 -->

		</div>

	</div>
	<!-- /.container -->

	<!-- Footer -->
	<c:import url='/WEB-INF/views/includes/footer.jsp' />
	<!-- /.Footer -->
</body>

</html>
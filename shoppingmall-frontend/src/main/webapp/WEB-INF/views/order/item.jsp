<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
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
	
	<!-- HTML Editor -->
	<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/smarteditor/js/service/HuskyEZCreator.js" charset="utf-8"></script>
	
	
	<style type="text/css">
		/* 꽉차게! */
		html, body {
			width: 100%;
			height: 93.6%;
		}
		.container {
			width: 100%;
			min-height: 100%;
		}
		table {
			margin-top: 5%;
			font-size: 0.9em;
		}
		caption {
			caption-side: top !important;
			color: black !important;
			font-size: 1.5em;
			font-weight: bolder;
		}
		.required {
			display: none;
		}
	</style>
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

			<div class="col-lg-9">
				<h3 style="margin: 5% 0;">주문정보</h3>

				<table class="table table-borderless">
					<caption>주문내역</caption>
					<tbody>
						<tr>
							<th>상품코드</th>
							<th>상품정보</th>
							<th>판매가</th>
							<th>수량</th>
							<th>합계</th>
						</tr>

						<c:forEach var="item" items="${ order.ordersItemDtoList }" varStatus="status">
							<tr>
								<td>
									<c:out value="${ item.productCode }" />
								</td>
								<td>
									<span style="font-weight: bold;">
										<a href="${ pageContext.servletContext.contextPath }/product/${ item.productNo }">
											<c:out value="${ item.productName }" />
										</a>
									</span><br>
									<span style="font-size: 0.9em; color: #787878;">
										[옵션: <c:out value="${ fn:replace(item.productOptionItemDetails, ';', '/') }" />]
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
						</c:forEach>

						<tr>
							<td colspan="6" class="text-right" style="padding-right: 5%;">
								<h6>
									총 상품금액: 
									<span style="color: #AF4848;">
										<fmt:formatNumber value="${ order.totalOrderAccount }" pattern="#,###" />원
									</span>
								</h6>
							</td>
						</tr>
					</tbody>
				</table>

				<hr>

				<table class="table table-bordered">
					<caption>주문자 정보</caption>
					<tbody>
						<tr>
							<th style="width: 150px;">주문자<span class="required">&nbsp;*</span></th>
							<td>
								<c:out value="${ order.ordererName }" />
							</td>
						</tr>
						<tr>
							<th>주소<span class="required">&nbsp;*</span></th>
							<td>
								<c:out value="${ order.ordererPostalCode }" /><br>
								<c:out value="${ order.ordererBaseAddress }" /><br>
								<c:out value="${ order.ordererDetailAddress }" />
							</td>
						</tr>
						<tr>
							<th>일반전화</th>
							<td>
								<c:out value="${ order.ordererHomeNumber }" />
							</td>
						</tr>
						<tr>
							<th>휴대전화<span class="required">&nbsp;*</span></th>
							<td>
								<c:out value="${ order.ordererPhoneNumber }" />
							</td>
						</tr>
						<tr>
							<th>이메일<span class="required">&nbsp;*</span></th>
							<td>
								<c:out value="${ order.ordererEmail }" />
							</td>
						</tr>
					</tbody>
				</table>

				<table class="table table-bordered">
					<caption>수령자 정보</caption>
					<tbody>
						<tr>
							<th style="width: 150px;">수령자<span class="required">&nbsp;*</span></th>
							<td>
								<c:out value="${ order.receiverName }" />
							</td>
						</tr>
						<tr>
							<th>주소<span class="required">&nbsp;*</span></th>
							<td>
								<c:out value="${ order.receiverPostalCode }" /><br>
								<c:out value="${ order.receiverBaseAddress }" /><br>
								<c:out value="${ order.receiverDetailAddress }" />
							</td>
						</tr>
						<tr>
							<th>일반전화</th>
							<td>
								<c:out value="${ order.receiverHomeNumber }" />
							</td>
						</tr>
						<tr>
							<th>휴대전화<span class="required">&nbsp;*</span></th>
							<td>
								<c:out value="${ order.receiverPhoneNumber }" />
							</td>
						</tr>
					</tbody>
				</table>
				
				<table class="table table-bordered">
					<caption>그 외 주문 정보</caption>
					<tbody>
						<tr>
							<th style="width: 150px;">주문코드<span class="required">&nbsp;*</span></th>
							<td>
								<c:out value="${ order.code }" />
							</td>
						</tr>
						<tr>
							<th>주문일<span class="required">&nbsp;*</span></th>
							<td>
								<c:out value="${ order.date }" />
							</td>
						</tr>
						<tr>
							<th>주문상태</th>
							<td>
								<c:out value="${ order.status }" />
							</td>
						</tr>
						<tr>
							<th>메모<span class="required">&nbsp;*</span></th>
							<td>
								<c:out value="${ order.memo }" />
							</td>
						</tr>
					</tbody>
				</table>

			</div>
			<!-- /.col-lg-9 -->
		</div>
		<!-- /.row -->
		
	</div>
	<!-- /.container -->

	<!-- Footer -->
	<c:import url='/WEB-INF/views/includes/footer.jsp' />
	<!-- /.Footer -->
</body>

</html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
			font-size: 0.9em;
		}
		th, td {
			overflow: hidden;
			white-space: nowrap;
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
				<h3 style="margin: 5% 0;">주문목록</h3>
			
				<div class="table-responsive"> <!-- Not working -->
				<table class="table table-striped">
					<thead style="text-align: center;">
						<tr>
							<th>번호</th>
							<th>코드</th>
							<th>주문일</th>
							<th>주문내용</th>
							<th>주문결제금액</th>
							<th>주문상태</th>
						</tr>
					</thead>
					
					<tbody>
						<c:forEach var="order" items="${ orderSummaryList }">
							<tr>
								<td><c:out value="${ order.ordersNo }" /></td>
								<td><c:out value="${ order.ordersCode }" /></td>
								<td><c:out value="${ order.ordersDate }" /></td>
								<td><a href="${pageContext.servletContext.contextPath }/order/member/view/${ order.ordersNo }"><c:out value="${ order.details }" /></a></td>
								<td><fmt:formatNumber value="${ order.ordersTotalOrderAccount }" pattern="#,###" /></td>
								<td><c:out value="${ order.ordersStatus }" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</div>
				
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

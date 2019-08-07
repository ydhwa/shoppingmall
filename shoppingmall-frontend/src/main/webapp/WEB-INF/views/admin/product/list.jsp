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
		<c:param name="active" value="admin" />
	</c:import>
	<!-- /.Navigation -->
	
	<div class="container">
		<div class="row">

			<c:import url='/WEB-INF/views/admin/includes/menu.jsp' />

			<!-- /.col-lg-3 -->

			<div class="col-lg-9">
				<h3 style="margin: 5% 0;">상품목록</h3>
			
				<div class="table-responsive"> <!-- Not working -->
				<table class="table table-striped">
					<thead style="text-align: center;">
						<tr>
							<th>번호</th>
							<th>코드</th>
							<th>상품명</th>
							<th>판매가</th>
							<th>요약설명</th>
							<th>등록일</th>
							<th>삭제여부</th>
							<th>진열상태</th>
							<th>판매여부</th>
							<th>관리상태</th>
							<th>카테고리</th>
						</tr>
					</thead>
					
					<tbody>
						<c:forEach var="product" items="${ products }">
							<tr>
								<td><c:out value="${ product.no }" /></td>
								<td><c:out value="${ product.code }" /></td>
								<td><c:out value="${ product.name }" /></td>
								<td><c:out value="${ product.sellPrice }" /></td>
								<td><c:out value="${ product.summaryDescription }" /></td>
								<td><c:out value="${ product.regDate }" /></td>
								<td><c:out value="${ product.delStatus }" /></td>
								<td><c:out value="${ product.displayStatus }" /></td>
								<td><c:out value="${ product.availability }" /></td>
								<td><c:out value="${ product.manageStatus }" /></td>
								<td><c:out value="${ product.categoryList }" /></td>
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

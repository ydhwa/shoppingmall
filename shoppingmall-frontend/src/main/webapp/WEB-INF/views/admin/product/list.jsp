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
			height: 93.6%;
		}
		.container {
			min-height: 100%;
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
			
				<table class="table table-striped" style="margin-top: 5%;">
					<thead style="text-align: center;">
						<tr>
							<th>번호</th>
							<th>아이디</th>
							<th>가입일</th>
							<th>이름</th>
							<th>생년월일</th>
							<th>휴대전화번호</th>
							<th>이메일</th>
						</tr>
					</thead>
					
					<tbody>
						<tr>
							<td>1</td>
							<td>userA01</td>
							<td>2019.08.06</td>
							<td>회원1</td>
							<td>1970.01.01</td>
							<td>010-0000-0000</td>
							<td>email@email.com</td>
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

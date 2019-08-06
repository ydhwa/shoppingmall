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
			
				<div class="table-responsive"> <!-- Not working -->
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
						<c:forEach var="user" items="${ users }">
							<tr>
								<td><c:out value="${ user.no }" /></td>
								<td><c:out value="${ user.username }" /></td>
								<td><c:out value="${ user.regDate }" /></td>
								<td><c:out value="${ user.name }" /></td>
								<td><c:out value="${ user.birthDate }" /></td>
								<td><c:out value="${ user.phoneNumber }" /></td>
								<td><c:out value="${ user.email }" /></td>
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

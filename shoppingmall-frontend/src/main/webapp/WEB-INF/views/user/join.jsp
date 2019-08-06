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
	<link href="${pageContext.servletContext.contextPath }/assets/css/shop-login.css" rel="stylesheet">
	
	<style type="text/css">
		/* 꽉차게! */
		html, body {
			height: 87.3%;
		}
		.container {
			min-height: 100%;
		}
		table {
			font-size: 0.85em;
		}
		th {
			min-width: 100px;
		}
		input {
			max-width: 200px;
		}
	</style>
</head>
<body>
	<!-- Navigation -->
	<c:import url='/WEB-INF/views/includes/navigation.jsp'>
		<c:param name="active" value="join" />
	</c:import>
	<!-- /.Navigation -->

 	<div class="container">
		<div class="card card-container">
			<h4 class="card-title">회원가입</h4>
			<form method="post" action="${ pageContext.servletContext.contextPath }/user/join" class="form-join" name="joinForm">
				<table class="table">
					<tr>
						<th>아이디</th>
						<td>
							<input type="text" id="inputUsername" class="form-control form-control-sm" pattern="[A-Za-z0-9_]{4,12}" placeholder="아이디" name="username" required autofocus>
						</td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td>
							<input type="password" id="inputPassword" class="form-control form-control-sm" pattern="(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,20}" placeholder="비밀번호" name="password" required>
						</td>
					</tr>
					<tr>
						<th>이름</th>
						<td>
							<input type="text" id="inputName" class="form-control form-control-sm" placeholder="이름" name="name" required>
						</td>
					</tr>
					<tr>
						<th>생년월일</th>
						<td>
							<input type="date" id="inputBirthDate" class="form-control form-control-sm" placeholder="YYYY-MM-DD" name="birthdate" required>
						</td>
					</tr>
					<tr>
						<th>유선<br>전화번호</th>
						<td>
							<input type="tel" id="inputHomeNumber" class="form-control form-control-sm" pattern="\d{2,3}-\d{3,4}-\d{4}" name="homenumber">
						</td>
					</tr>
					<tr>
						<th>휴대<br>전화번호</th>
						<td>
							<input type="tel" id="inputPhoneNumber" class="form-control form-control-sm" pattern="\d{3}-\d{4}-\d{4}" name="phonenumber" required>
						</td>
					</tr>
					<tr>
						<th>이메일</th>
						<td>
							<input type="email" id="inputEmail" class="form-control form-control-sm" name="email" required>
						</td>
					</tr>
				</table>
				<button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">가입하기</button>
			</form>
			<!-- /form -->
		</div>

		<!-- /.card-container -->
	</div>
	<!-- /.container -->

	<!-- Footer -->
	<c:import url='/WEB-INF/views/includes/footer.jsp' />
	<!-- /.Footer -->
</body>
</html>
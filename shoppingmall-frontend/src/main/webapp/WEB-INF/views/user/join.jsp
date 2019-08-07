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
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
	<script src="${ pageContext.servletContext.contextPath }/assets/js/jquery/jquery.min.js"></script>
	
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
	</style>
	
	<script>
	$(function() {
		$('#check-icon').hide();
		
		$('#inputUsername').change(function() {
			$('#check-button').show();
			$('#check-icon').hide();
		});
		
		
		$('#check-button').click(function() {
			var username = $('#inputUsername').val();
			if(username == '') {
				return ;
			}
			
			$.ajax({
				url: '${ pageContext.servletContext.contextPath }/user/checkusername?username=' + username,
				type: 'get',
				dataType: 'json',
				data: '',
				success: function(response) {
					if(response.result != 'success') {
						return ;
					}
					
					if(response.data == true) {
						alert('이미 존재하는 아이디입니다.\n다른 아이디를 사용해주세요.')
						$('#inputUsername').focus();
						$('#inputUsername').val('');
						return ;
					}
					
					$('#check-button').hide();
					$('#check-icon').show();
				},
				error: function(xhr, error) {
					console.error('[ERROR] ' + error);
				}
			});
		});
	});
	</script>
</head>
<body>
	<!-- Navigation -->
	<c:import url='/WEB-INF/views/includes/navigation.jsp'>
		<c:param name="active" value="join" />
	</c:import>
	<!-- /.Navigation -->

 	<div class="container">
		<div class="card w-50 card-body">
			<h4 class="card-title">회원가입</h4>
			<form method="post" action="${ pageContext.servletContext.contextPath }/user/join" class="form-join" name="joinForm">
				<table class="table">
					<tr>
						<th>아이디</th>
						<td>
							<input type="text" id="inputUsername" class="form-control form-control-sm" pattern="[A-Za-z0-9_]{4,12}" placeholder="아이디" name="username" required autofocus>
						</td>
						<td style="white-space: nowrap; width: 1%;">
							<input type="button" id="check-button" class="btn btn-light btn-sm" value="체크">
							<i class="fas fa-check" id="check-icon"></i>
						</td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td>
							<input type="password" id="inputPassword" class="form-control form-control-sm" pattern="(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,20}" placeholder="비밀번호" name="password" required>
						</td>
						<td></td>
					</tr>
					<tr>
						<th>이름</th>
						<td>
							<input type="text" id="inputName" class="form-control form-control-sm" placeholder="이름" name="name" required>
						</td>
						<td></td>
					</tr>
					<tr>
						<th>생년월일</th>
						<td>
							<input type="date" id="inputBirthDate" class="form-control form-control-sm" placeholder="YYYY-MM-DD" name="birthDate" required>
						</td>
						<td></td>
					</tr>
					<tr>
						<th>유선<br>전화번호</th>
						<td>
							<input type="tel" id="inputHomeNumber" class="form-control form-control-sm" pattern="\d{2,3}-\d{3,4}-\d{4}" name="homeNumber">
						</td>
						<td></td>
					</tr>
					<tr>
						<th>휴대<br>전화번호</th>
						<td>
							<input type="tel" id="inputPhoneNumber" class="form-control form-control-sm" pattern="\d{3}-\d{4}-\d{4}" name="phoneNumber" required>
						</td>
						<td></td>
					</tr>
					<tr>
						<th>이메일</th>
						<td>
							<input type="email" id="inputEmail" class="form-control form-control-sm" name="email" required>
						</td>
						<td></td>
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
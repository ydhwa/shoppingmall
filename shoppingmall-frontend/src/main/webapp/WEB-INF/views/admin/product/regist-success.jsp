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
	</style>
</head>
<body>
	<!-- Navigation -->
	<c:import url='/WEB-INF/views/includes/navigation.jsp'>
		<c:param name="active" value="join" />
	</c:import>
	<!-- /.Navigation -->

	<div class="container">
		<div class="row">
			<c:import url='/WEB-INF/views/admin/includes/menu.jsp' />

			<div class="col-lg-9" style="margin: 10% 0;">
				<div class="card card-container">
					<h5 class="card-title" style="text-align: center">상품 등록에 성공했습니다!</h5>
					<button class="btn btn-lg btn-primary btn-block btn-signin" type="button" onclick="location.href='${ pageContext.servletContext.contextPath }/admin/product/regist'">
						상품 더 등록하기
					</button>
					<button class="btn btn-lg btn-primary btn-block btn-signin" type="button" onclick="location.href='${ pageContext.servletContext.contextPath }/admin/product/list'">
						상품 목록 보기
					</button>
					<!-- /form -->
				</div>
			</div>
		</div>

		<!-- /.card-container -->
	</div>
	<!-- /.container -->

	<!-- Footer -->
	<c:import url='/WEB-INF/views/includes/footer.jsp' />
	<!-- /.Footer -->
</body>
</html>
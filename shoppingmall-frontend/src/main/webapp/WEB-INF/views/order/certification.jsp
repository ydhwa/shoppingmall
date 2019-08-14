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
	</c:import>
	<!-- /.Navigation -->

 	<div class="container">
 		<h4>비회원 주문 조회</h4>
 		<div class="card card-container">
            <form method="post" action="${ pageContext.servletContext.contextPath }/order/non-member" class="form-signin" name="loginForm">
                <input type="text" id="code" class="form-control" placeholder="주문코드('-' 포함)" name="code" required autofocus>
                <input type="password" id="password" class="form-control" placeholder="비밀번호" name="password" required>
                <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">주문 조회</button>
            </form><!-- /form -->
        </div>
        <!-- /.card-container -->
	</div>
	<!-- /.container -->

	<!-- Footer -->
	<c:import url='/WEB-INF/views/includes/footer.jsp' />
	<!-- /.Footer -->
</body>
</html>
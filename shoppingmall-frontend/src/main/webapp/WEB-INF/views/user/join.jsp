<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
   
   <div id="container">
      <div class="card card-container">
            <form:form modelAttribute="memberVo" id="join-form" name="joinForm" method="post" action="${pageContext.servletContext.contextPath }/user/join" >
               <label class="block-label" for="id">아이디</label>
               <input type="button" id="check-button" value="체크" class="btn btn-light">
               <img style="display:none" id="check-image" src="${pageContext.servletContext.contextPath }/assets/images/check.png" />
               <p style="font-weight:bold; color:#f00; text-align:left; padding:0; margin:0 ">
                  <form:errors path="id" />
               </p>
               
               <form:input path="username" id="username" class="form-control"/>
               
               <label class="block-label">패스워드</label>
               <form:password path="password" id="password" class="form-control"/>
               
               <label class="block-label" for="name">이름</label>
               <input id="name" name="name" type="text" value="" class="form-control">
               <spring:hasBindErrors name="memberVo">
                   <c:if test="${errors.hasFieldErrors('name') }">
                     <p style="font-weight:bold; color:red; text-align:left; padding:0">
                           <spring:message 
                             code="${errors.getFieldError( 'name' ).codes[0] }"                  
                             text="${errors.getFieldError( 'name' ).defaultMessage }" />
                       </p> 
                  </c:if>
               </spring:hasBindErrors>
               
               <label class="block-label" for="phone">전화번호</label>
               <form:input path="phone" id="phone" class="form-control"/>
               
               <label class="block-label" for="email">이메일</label>
               <form:input path="email" id="email" class="form-control"/>
            
               
               <label class="block-label" for="birth">생일</label>
               <form:input path="birth" id="birth" class="form-control"/>
               
               <fieldset>
                  <label class="block-label" for="gender">성별</label><br>
                  <label>여</label> <form:radiobutton path="gender" value="female" checked="checked" />
                  <label>남</label> <form:radiobutton path="gender" value="male" />
               </fieldset>
               
               <fieldset>
                  <legend>약관동의</legend>
                  <!-- form:checkbox path="agreeProv" value="y"/-->
                  <input id="agree-prov" type="checkbox" name="agreeProv" value="y">
                  <label>서비스 약관에 동의합니다.</label>
               </fieldset>
               
               <input type="submit" value="가입하기" class="btn btn-lg btn-primary btn-block btn-join">
               
            </form:form>
      </div>
   </div>
   <c:import url="/WEB-INF/views/includes/footer.jsp" />
</body>
</html>
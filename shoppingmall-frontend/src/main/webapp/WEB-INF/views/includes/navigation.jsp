<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
	<div class="container">
		<a class="navbar-brand" href="#">&nbsp;</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
				<sec:authorize access="isAuthenticated()">
					<li class="nav-item" style="padding-top: 10px; padding-right: 30px; color: #ccc; font-size: 0.75em;"><b><sec:authentication property="principal.name"/></b>님 안녕하세요~</li>
				</sec:authorize>
				
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<c:choose>
						<c:when test="${ param.active == 'admin' }">
							<li class="nav-item active">
								<a class="nav-link" href="${pageContext.servletContext.contextPath }/admin/main">쇼핑몰관리<span class="sr-only">(current)</span></a>
							</li>
						</c:when>
						<c:otherwise>
							<li class="nav-item">
								<a class="nav-link" href="${pageContext.servletContext.contextPath }/admin/main">쇼핑몰관리</a>
							</li>
						</c:otherwise>
					</c:choose>
					
				</sec:authorize>
			
				<c:choose>
					<c:when test="${ param.active == 'login' || param.active == 'join' || param.active == 'modify' || param.active == 'bucket' || param.active == 'admin' }">
						<li class="nav-item">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }">홈</a>
						</li>
					</c:when>
					<c:otherwise>
						<li class="nav-item active">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }">홈<span class="sr-only">(current)</span></a>
						</li>
					</c:otherwise>
				</c:choose>
				
				<sec:authorize access="!isAuthenticated()">
					<c:choose>
						<c:when test="${ param.active == 'login' }">
							<li class="nav-item active">
								<a class="nav-link" href="${pageContext.servletContext.contextPath }/user/login">로그인<span class="sr-only">(current)</span></a>
							</li>
						</c:when>
						<c:otherwise>
							<li class="nav-item">
								<a class="nav-link" href="${pageContext.servletContext.contextPath }/user/login">로그인</a>
							</li>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${ param.active == 'join' }">
							<li class="nav-item active">
								<a class="nav-link" href="${pageContext.servletContext.contextPath }/user/join">회원가입<span class="sr-only">(current)</span></a>
							</li>
						</c:when>
						<c:otherwise>
							<li class="nav-item">
								<a class="nav-link" href="${pageContext.servletContext.contextPath }/user/join">회원가입</a>
							</li>
						</c:otherwise>
					</c:choose>
					<li class="nav-item">
						<a class="nav-link" href="${pageContext.servletContext.contextPath }/order/non-member">주문조회(비회원)</a>
					</li>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<c:choose>
						<c:when test="${ param.active == 'modify' }">
							<li class="nav-item active">
								<a class="nav-link" href="${pageContext.servletContext.contextPath }/user/modify">회원정보수정<span class="sr-only">(current)</span></a>
							</li>
						</c:when>
						<c:otherwise>
							<li class="nav-item">
								<a class="nav-link" href="${pageContext.servletContext.contextPath }/user/modify">회원정보수정</a>
							</li>
						</c:otherwise>
					</c:choose>
					<li class="nav-item">
						<a class="nav-link" href="${pageContext.servletContext.contextPath }/user/logout">로그아웃</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="${pageContext.servletContext.contextPath }/order/member">주문목록조회</a>
					</li>
				</sec:authorize>
				
				<c:choose>
					<c:when test="${ param.active == 'bucket' }">
						<li class="nav-item active">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }/bucket">장바구니<span class="sr-only">(current)</span></a>
						</li>
					</c:when>
					<c:otherwise>
						<li class="nav-item">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }/bucket">장바구니</a>
						</li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</div>
</nav>
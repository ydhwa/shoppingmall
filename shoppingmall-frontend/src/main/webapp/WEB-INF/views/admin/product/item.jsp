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
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
	<script src="${ pageContext.servletContext.contextPath }/assets/js/jquery/jquery.min.js"></script>
	
	<!-- HTML Editor -->
	<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/smarteditor/js/service/HuskyEZCreator.js" charset="utf-8"></script>
	
	
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
			margin-top: 5%;
			font-size: 0.9em;
		}
		caption {
			caption-side: top !important;
			color: black !important;
			font-size: 1.5em;
			font-weight: bolder;
		}
	</style>
</head>
<body>
	<!-- Navigation -->
	<c:import url='/WEB-INF/views/includes/navigation.jsp'>
		<c:param name="active" value="admin" />
	</c:import>
	<!-- /.Navigation -->
	
	<div class="container" style="padding-bottom: 3%;">
		<div class="row">

			<c:import url='/WEB-INF/views/admin/includes/menu.jsp' />

			<!-- /.col-lg-3 -->

			<div class="col-lg-9">
				<h3 style="margin: 5% 0;">상품정보</h3>
			
				<table class="table">
					<caption>기본 정보</caption>
					<tr>
						<th>상품명</th>
						<td>
							<c:out value="${ product.name }"/>
						</td>
					</tr>
					<tr>
						<th>판매가</th>
						<td>
							<c:out value="${ product.sellPrice }"/>
						</td>
					</tr>
					<tr>
						<th>공급가</th>
						<td>
							<c:out value="${ product.supplyPrice }"/>
						</td>
					</tr>
					<tr>
						<th>상품 요약설명</th>
						<td>
							<c:out value="${ product.summaryDescription }"/>
						</td>
					</tr>
					<tr>
						<th height="400">상품 상세설명</th>
							<td>
								<div>${ product.detailedDescription }</div>
							</td>
					</tr>
					<tr>
						<th>이미지</th>
						<td>
						<div class="row">
						<div class="col-sm-9">
							</div>
								<div class="col-sm-3">
									<img id="customImage" alt="" src="${pageContext.servletContext.contextPath }/assets/images/default.png" width="100" height="100">
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<th>상품 하나 당<br>중량(kg)</th>
						<td>
							<c:out value="${ product.weight }"/>
						</td>
					</tr>
				</table>
				
				
				<table class="table">
					<caption>쇼핑몰 진열설정</caption>
					<tr>
						<th>진열상태</th>
						<td>
							<c:out value="${ product.displayStatus }"/>
						</td>
					</tr>
					<tr>
						<th>판매상태</th>
						<td>
							<c:out value="${ product.availability }"/>
						</td>
					</tr>
					<tr>
						<th>상품분류</th>
						<td>
							<div class="row">
								<div class="list-group">
									<c:forEach var="category" items="${ product.categoryList }">
										<c:out value="${ category.name }" /><br>
									</c:forEach>
								</div>
							</div>
						</td>
					</tr>
				</table>
				
				
				<table class="table">
					<caption>옵션설정</caption>
					<tr>
						<th style="max-width: 35px !important;">상품옵션</th>
						<td>
							<c:out value="${ product.optionAvailable }"/>
						</td>
					</tr>
					
					<tr id="optionSettings">
						<th>옵션설정</th>
						<td>
							<table class="table" style="margin: 0;">
								<tr>
									<th>옵션명</th>
									<th>옵션값&nbsp;&nbsp;(Enter를 누르거나 ;로 각 옵션값을 구분할 수 있습니다.)</th>
								</tr>
								<c:forEach var="option" items="${ product.optionList }">
								<tr class="optionTemplate">
									<td>
										<c:out value="${ option.name }"/>
									</td>
									<td class="optionValueFormWrapper">
										<c:forEach var="optionValue" items="${ option.productOptionValueList }">
											<c:out value="${ optionValue.value }"/><br>
										</c:forEach>
									</td>
								</tr>
								</c:forEach>
							</table>
							
							<br>
							
							<table class="table" id="optionItemTable">
								<tr>
									<th>번호</th>
									<th>옵션 (품목코드)</th>
									<th>추가금액</th>
									<th>재고관리</th>
									<th>재고수량</th>
								</tr>
								<c:forEach var="optionItem" items="${ product.optionItemList }">
									<tr>
										<td><c:out value="${ optionItem.no }"/></td>
										<td><c:out value="${ optionItem.details }"/></td>
										<td><c:out value="${ optionItem.additionalAmount }"/></td>
										<td><c:out value="${ optionItem.manageStatus }"/></td>
										<td><c:out value="${ optionItem.stockQuantity }"/></td>
									</tr>
								</c:forEach>
							</table>
						</td>
					</tr>
					
					<tr id="stockManageStatusForm">
						<th style="max-width: 35px !important;">재고관리</th>
						<td>
							<c:out value="${ product.manageStatus }"/>
						</td>
					</tr>
					<tr>
						<th style="max-width: 35px !important;">재고수량</th>
						<td>
							<c:out value="${ product.stockQuantity }"/>
						</td>
					</tr>
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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	
	<style type="text/css">
		/* 꽉차게! */
		html, body {
			height: 93.5%;
		}
		.container {
			min-height: 95%;
		}
	</style>

</head>
<body>
	<!-- Navigation -->
	<c:import url='/WEB-INF/views/includes/navigation.jsp'>
		<c:param name="active" value="bucket" />
	</c:import>
	<!-- /.Navigation -->

	<!-- Page Content -->
	<div class="container">
		<div class="row" style="margin-bottom: 5%;">

			<div class="col-lg-3">
				<a href="${pageContext.servletContext.contextPath }" style="color: black; text-decoration: none;"><h1 class="my-4" style="letter-spacing: 3px; font-family: consolas; text-transform: uppercase; text-align: center;">Shopping<br>Mall</h1></a>
				<div class="list-group">
					<c:forEach var="category" items="${ categories }">
						<a href="${ pageContext.servletContext.contextPath }/list?categoryNo=${ category.no }" class="list-group-item" style="padding-left: ${ category.level * 15 + 20 }px;">
							<c:if test="${ category.level > 0 }">
								<img src="">
							</c:if>
							<c:out value="${ category.name }" />
						</a>
					</c:forEach>
				</div>
			</div>
			<!-- /.col-lg-3 -->

			<div class="col-lg-9">

				<div class="card mt-4">
					<img class="card-img-top img-fluid" src="http://placehold.it/900x400" alt="">
					<div class="card-body">
						<h3 class="card-title"><c:out value="${ product.name }" /></h3>
						<table class="table table-borderless" style="font-size: 0.8em;">
							<tbody>
								<tr>
									<th><input type="checkbox" class="form-control"></th>
									<th>상품정보</th>
									<th>판매가</th>
									<th>수량</th>
									<th>합계</th>
									<th>
										선택
										<button type="button" class="btn btn-sm">주문하기</button>
										<button type="button" class="btn btn-sm">삭제</button>
									</th>
								</tr>
							
								<!-- 여기에 forEach문으로 장바구니 내의 물품들을 뽑아내야 한다. -->
								<c:forEach var="item" items="${ bucketItemList }" varStatus="index">
									<tr>
										<td>
										</td>
										<td>
										</td>
										<td>
										</td>
										<td>
										</td>
										<td>
											<button type="button" class="btn btn-sm">주문하기</button>
											<button type="button" class="btn btn-sm">삭제</button>
										</td>
									</tr>
								</c:forEach>
								
								<tr>
									<th>판매가</th>
									<th><fmt:formatNumber value="${ product.sellPrice }" pattern="#,###" />원</th>
								</tr>
								<tr>
									<td>상품코드</td>
									<td><c:out value="${ product.code }" /></td>
								</tr>
								
								<c:forEach var="option" items="${ product.optionList }" varStatus="optionStatus">
									<tr style="border-top: 1px solid #dfdfdf;">
										<td><c:out value="${ option.name }" /></td>
										<td>
											<select class="selectbox" name="optionValue${ optionStatus.count }">
												<option value="0">=== 옵션을 선택하세요 ===</option>
												<c:forEach var="optionValue" items="${ option.productOptionValueList }" varStatus="optionValueStatus">
													<option value="${ optionValueStatus.count }"><c:out value="${ optionValue.value }" /></option>
												</c:forEach>
											</select>
										</td>
									</tr>
								</c:forEach>
								
								<tr>
									<td colspan="2">
										<table id="selectedItems">
											
										</table>
									</td>									
								</tr>
								
								<tr>
									<td colspan="2" style="text-align: center;">
										<button type="button" style="font-size: 0.9em; border: 1px solid #dadada; margin: auto; width: 47%;" class="btn btn-light"><br>바로 구매하기<br><br></button>
										<button type="button" onclick="addBucketList()" style="font-size: 0.9em; border: 1px solid #dadada; margin: auto; width: 47%;" class="btn btn-dark"><br><i class="fas fa-cart-plus"></i>&nbsp;장바구니에 담기<br><br></button>
									</td>
								</tr>
							</tbody>
						</table>
						
						<hr>
						<p class="card-text" style="font-size: 0.8em;">
							${ product.detailedDescription }
						</p>
<!-- 						<span class="text-warning">&#9733; &#9733; &#9733; &#9733; -->
<!-- 							&#9734;</span> 4.0 stars -->
					</div>
				</div>
				<!-- /.card -->

<!-- 				<div class="card card-outline-secondary my-4"> -->
<!-- 					<div class="card-header">Product Reviews</div> -->
<!-- 					<div class="card-body"> -->
<!-- 						<p> -->
<!-- 							Lorem ipsum dolor sit amet, consectetur adipisicing elit. -->
<!-- 							Omnis et enim aperiam inventore, similique necessitatibus neque -->
<!-- 							non! Doloribus, modi sapiente laboriosam aperiam fugiat laborum. -->
<!-- 							Sequi mollitia, necessitatibus quae sint natus. -->
<!-- 						</p> -->
<!-- 						<small class="text-muted">Posted by Anonymous on 3/1/17</small> -->
<!-- 						<hr> -->
<!-- 						<a href="#" class="btn btn-success">Leave a Review</a> -->
<!-- 					</div> -->
<!-- 				</div> -->
				<!-- /.card -->

			</div>
			<!-- /.col-lg-9 -->

		</div>

	</div>
	<!-- /.container -->

	<!-- Footer -->
	<c:import url='/WEB-INF/views/includes/footer.jsp' />
	<!-- /.Footer -->
</body>

</html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
	
	<script type="text/javascript">
		var bucketList = new Array();
		var selectedBucketList = new Array();
	
		$(function() {
			// 수량 변경 시
			$('.quantity').change(function() {
				var index = $(this).parent().parent().children().eq(0).val();
				var memberNo = bucketList[index].memberNo;
				var identifier = bucketList[index].identifier;
				var quantity = $(this).val();
				var productOptionItemNo = bucketList[index].productOptionItemNo;
				
				var bucketItemVo = {
					'memberNo': memberNo,
					'identifier': identifier,
					'quantity': quantity,
					'productOptionItemNo': productOptionItemNo
				}
				
				// 장바구니 수량 수정 후 refresh
				$.ajax({
					url: '${ pageContext.servletContext.contextPath }/bucket',
					type: 'put',
					dataType: 'json',
					data: JSON.stringify(bucketItemVo),
					contentType: 'application/json',
		       		success: function(response) {
		       			if(response.data == true) {
			       			location.reload();
		       			}
			       		return ;
					},
					error: function(jqXHR, status, e) {
						console.error('[ERROR] ' + status + ': ' + e);
					}
				});
			});
			
			// 전체 주문 시
			$('#allOrder').click(function() {
				listOrder(bucketList);
			});
			// 선택 주문 시
			$('#selectOrder').click(function() {
				if(selectedBucketList.length > 0) {
					listOrder(selectedBucketList);
				}
			});
			// 전체 삭제 시
			$('#allDelete').click(function() {
				listDelete(bucketList);
			});
			// 선택 삭제 시
			$('#selectDelete').click(function() {
				if(selectedBucketList.length > 0) {
					listDelete(selectedBucketList);
				}
			});
			
			// 체크박스 클릭 시 selectedBucketList에 추가/삭제됨
			$('.selectbox').change(function() {
				var index = $(this).parent().parent().children().eq(0).val();
				var productOptionItemNo = bucketList[index].productOptionItemNo;
				
				if($(this).is(':checked')) {
					var memberNo = bucketList[index].memberNo;
					var identifier = bucketList[index].identifier;
					var quantity = bucketList[index].quantity;
					
					var bucketItemVo = {
						'sellPrice': bucketList[index].sellPrice,
						'quantity': quantity,
						'productNo': bucketList[index].productNo,
						'productName': bucketList[index].productName,
						'productOptionItemNo': bucketList[index].productOptionItemNo,
						'productOptionDetails': bucketList[index].productOptionDetails,
						'memberNo': memberNo,
						'identifier': identifier
					}
					selectedBucketList.push(bucketItemVo);
				} else {
					var i;
					for(i = 0; i < selectedBucketList.length; i++) {
						if(selectedBucketList[i].productOptionItemNo == bucketList[index].productOptionItemNo) {
							break;
						}
					}
					selectedBucketList.splice(i, 1);
				}
			});
		});
		// 하나만 주문
		function oneOrder(obj) {
			var index = $(obj).parent().parent().children().eq(0).val();
			var oneBucketList = new Array();
			oneBucketList.push(bucketList[index]);
			
			listOrder(oneBucketList);
		}
		// 하나만 삭제
		function oneDelete(obj) {
			var index = $(obj).parent().parent().children().eq(0).val();
			var oneBucketList = new Array();
			oneBucketList.push(bucketList[index]);
			
			listDelete(oneBucketList);
		}
		
		// 리스트에 담겨 있는 것 주문
		function listOrder(list) {
			var form = document.createElement('form');
			form.method = 'get';
			form.action = '${ pageContext.servletContext.contextPath }/order';
					
			var hiddenField = document.createElement('input');
			hiddenField.name = 'listStr';
			hiddenField.type = 'hidden';
			hiddenField.value = JSON.stringify(list);
			form.appendChild(hiddenField);
					
			document.body.appendChild(form);
			form.submit();
		}
		
		// 리스트에 담겨 있는 것 삭제
		function listDelete(list) {
			$.ajax({
				url: '${ pageContext.servletContext.contextPath }/bucket',
				type: 'delete',
				dataType: 'json',
				data: JSON.stringify(list),
				contentType: 'application/json',
		       	success: function(response) {
		       		if(response.data == true) {
		       			location.reload();
	       			}
		       		return ;
				},
				error: function(jqXHR, status, e) {
					console.error('[ERROR] ' + status + ': ' + e);
				}
			});
		}
	</script>

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
					<div class="card-body">
						<h3 class="card-title">장바구니</h3>
						<table class="table table-borderless" style="font-size: 0.8em;">
							<tbody>
								<tr>
									<th></th>
									<th>상품정보</th>
									<th>판매가</th>
									<th>수량</th>
									<th>합계</th>
									<th>선택</th>
								</tr>
							
								<!-- 여기에 forEach문으로 장바구니 내의 물품들을 뽑아내야 한다. -->
								<c:forEach var="item" items="${ bucketList }" varStatus="status">
									<tr>
										<input type="hidden" value="${ status.index }">
										<td>
											<input type="checkbox" class="custom-control custom-checkbox selectbox">
										</td>
										<td>
											<span style="font-weight: bold;">
												<a href="${ pageContext.servletContext.contextPath }/product/${ item.productNo }"><c:out value="${ item.productName }" /></a>
											</span><br>
											<span style="font-size: 0.9em; color: #787878;">
												[옵션: <c:out value="${ fn:replace(item.productOptionDetails, ';', '/') }" />]
											</span>
										</td>
										<td>
											<fmt:formatNumber value="${ item.sellPrice }" pattern="#,###" />원
										</td>
										<td style="max-width: 100px;">
											<input type="number" class="form-control form-control-sm quantity" value="${ item.quantity }" min="1">
										</td>
										<td>
											<fmt:formatNumber value="${ item.sellPrice * item.quantity }" pattern="#,###" />원
										</td>
										<td class="text-center">
											<button onclick="oneOrder(this);" style="font-size: 0.6em;" type="button" class="btn btn-sm btn-dark">주문하기</button><br>
											<button onclick="oneDelete(this);" style="font-size: 0.6em;" type="button" class="btn btn-sm btn-light"><i class="fas fa-trash-alt"></i>&nbsp;삭제</button>
										</td>
									</tr>
									
									<script>
										var bucketItem = {
											'sellPrice': '${ item.sellPrice }',
											'quantity': '${ item.quantity }',
											'productNo': '${ item.productNo }',
											'productName': '${ item.productName }',
											'productOptionItemNo': '${ item.productOptionItemNo }',
											'productOptionDetails': '${ item.productOptionDetails }',
											'memberNo': '${ item.memberNo }',
											'identifier': '${ item.identifier }'
										};
										bucketList.push(bucketItem);
									</script>
								</c:forEach>
								
								<tr>
									<td colspan="6" class="text-right" style="padding-right: 5%;">
										<h6>총 상품금액: 
											<span style="color: #AF4848;">
												<fmt:formatNumber value="${ totalPrice }" pattern="#,###" />원
											</span>
										</h6>
									</td>
								</tr>
								
								<tr>
									<td colspan="5" style="font-size: 0.9em;">
										선택상품을&nbsp;
										<button type="button" id="selectDelete" style="font-size: 0.9em;" class="btn btn-sm btn-light">
											<i class="fas fa-trash-alt"></i>&nbsp;삭제
										</button>
									</td>
									<td colspan="1" style="font-size: 0.9em;">
										<button type="button" id="allDelete" style="font-size: 0.9em;" class="btn btn-sm btn-light">
											<i class="fas fa-trash-alt"></i>&nbsp;장바구니 비우기
										</button>
									</td>
								</tr>
								
								<tr>
									<td colspan="6" class="text-center">
										<button type="button" id="allOrder" class="btn btn-sm btn-dark" style="font-size: 0.9em;">
											&nbsp;&nbsp;&nbsp;전체상품주문&nbsp;&nbsp;&nbsp;
										</button>
										<button type="button" id="selectOrder" class="btn btn-sm btn-secondary" style="font-size: 0.9em;">
											&nbsp;&nbsp;&nbsp;선택상품주문&nbsp;&nbsp;&nbsp;
										</button>
									</td>
								</tr>								
								
							</tbody>
						</table>
						

					</div>
				</div>


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
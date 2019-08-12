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
	
	<script type="text/javascript">
		var bucketList = new Array();
	
		$(function() {
			$('.selectbox').change(function() {
				var optionValueKeys = '';
				var fullChange = true;
				$('.selectbox').each(function(index, item) {
					var optionValueKey = $(item).val();
					if(optionValueKey == 0) {
						fullChange = false;
						return ;
					}
					optionValueKeys += optionValueKey + ';';
				});
				
				if(!fullChange) {	// 모든 항목을 선택하지 않았으면 아예 다음 작업이 실행되지 않도록 함
					return ;
				}
				
				optionValueKeys = optionValueKeys.substr(0, optionValueKeys.length - 1);
				
				$.ajax({
					url: '${ pageContext.servletContext.contextPath }/product/optionitem?productNo=${ product.no }&optionValueKeys=' + optionValueKeys,
					type: 'get',
					dataType: 'json',
					success: function(response) {
						if(response.data != null) {
							var duplicate = false;
							
							var index = -1;
							if(bucketList.length > 0) {
								index = findIndex(response.data.no);
							}
							
							if(index < 0) {
								var additionalAmount = response.data.additionalAmount + ${ product.sellPrice };
								var html =
									'<tr>' + 
										'<td>' + 
											'<span style="font-weight: bold;">${ product.name }</span><br><span style="color: #787878; font-size: 0.9em;">' +
											response.data.details.replace(';', '/') + 
										'</span></td>' + 
										'<td>' + 
											'<input type="number" class="quantity form-control form-control-sm" value="1" min="1" onChange="renewalQuantity(this, ' + response.data.no + ');">' +
										'</td>' +
										'<td>' + 
											'<i onclick="deleteItem(' + response.data.no + ')" class="far fa-trash-alt"></i>' + 
										'</td>' + 
										'<td>' + 
											additionalAmount.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + 
										'원</td>' + 
									'</tr>';
								$('#selectedItems').append(html);
							
								// 장바구니 후보에 담기
								var item = {
									'productOptionItemNo': response.data.no,
									'price': additionalAmount
								};
								bucketList.push(item);
							} else {
								$('.quantity').eq(index).val(parseInt($('.quantity').eq(index).val()) + 1);
								bucketList[index].quantity += 1;
								
								// 수량 및 가격 갱신
								renewalQuantity($('.quantity').eq(index), bucketList[index].productOptionItemNo);
							}
						} else {
							alert('상품이 없습니다.');
							$('.selectbox').each(function(index, item) {
								$(item).val(0);
							});
							return false;
						}
						$('.selectbox').each(function(index, item) {
							$(item).val(0);
						});
					},
					error: function(jqXHR, status, e) {
						console.error('[ERROR] ' + status + ': ' + e);
					}
				});
			});
		});
		
		function deleteItem(no) {
			var index = findIndex(no);
			
			bucketList.splice(index, 1);
			$('#selectedItems').children('tr').eq(index).remove();
		}
		// 해당 번호에 맞는 인덱스 가져오기
		function findIndex(no) {
			for(var i = 0; i < bucketList.length; i++) {
				if(bucketList[i].productOptionItemNo == no) {
					return i;
				}
			}
			return -1;
		}
		// 수량 변동에 따른 가격 갱신
		function renewalQuantity(obj, no) {
			var amount = bucketList[findIndex(no)].price * $(obj).val();
			$(obj).parent().parent().children().eq(3).text(amount.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',') + '원');
		}
		
		// 장바구니 담기 동작
		function addBucketList() {
			// 총 수량 갱신
			for(var i = 0; i < bucketList.length; i++) {
				bucketList[i].quantity = parseInt($('.quantity').eq(i).val());
			}
			
			// 파라미터 넣기
			var paramMap = new Object();
			paramMap.memberNo = '${ memberNo }' == '' ? null : '${ memberNo }';
			paramMap.identifier = '${ identifier }' == '' ? null : '${ identifier }';
			var newBucketList = new Array();
			
			// price 속성값 제외하기(for mapping)
			for(var i = 0; i < bucketList.length; i++) {
				var bucketItem = {
					'productOptionItemNo': bucketList[i].productOptionItemNo,
					'quantity': bucketList[i].quantity
				};
				newBucketList.push(bucketItem);
			}
			paramMap.bucketItemList = newBucketList;
			
			$.ajax({
				url: '${ pageContext.servletContext.contextPath }/bucket',
				type: 'post',
				dataType: 'json',
				data: JSON.stringify(paramMap),
				contentType: 'application/json',
		       	success: function(response) {
		       		if(response.data == true) {
		       			alert('장바구니에 성공적으로 담겼습니다!');
		       		} else {
		       			alert('담기 실패!');
		       		}
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
		<c:param name="active" value="shopping" />
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
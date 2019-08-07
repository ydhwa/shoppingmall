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
		th, td {
			overflow: hidden;
			white-space: nowrap;
		}
		th > span {
			font-size: 0.7em;
			color: #0054FF;
		}
		caption {
			caption-side: top !important;
			color: black !important;
			font-size: 1.5em;
			font-weight: bolder;
		}
	</style>
	
	<script>
		var productOptionList = new Array();
		var productOptionItemList = new Array();
		var categoryList = new Array();
		var productImageList = new Array();
	
		$(function() {
			$('#final-submit').click(function() {
				// required 검증
				if($('#inputProductName').val() == '') {
					$('#inputProductName').focus();
					return;
				}
				if($('#inputSellPrice').val() == '') {
					$('#inputSellPrice').focus();
					return;
				}
				
				// 값들 Object에 셋팅
				var product = {
						name: $('#inputProductName').val(),
						supplyPrice: $('#inputSupplyPrice').val(),
						sellPrice: $('#inputSellPrice').val(),
						summaryDescription: $('#inputSummaryDescription').val(),
						detailedDescription: getDetailedDescription(),
						weight: $('#inputWeight').val(),
						optionAvailable: $(":input:radio[name=optionAvailable]:checked").val(),
						displayStatus: $(":input:radio[name=displayStatus]:checked").val(),
						availability: $(":input:radio[name=availability]:checked").val(),
						manageStatus: $(":input:radio[name=manageStatus]:checked").val()
				};
				
				var paramMap =  {'product': product,
						'productOptionList': productOptionList,
						'productOptionItemList': productOptionItemList,
						'categoryList': categoryList,
						'productImageList': productImageList};
				
				console.log(JSON.stringify(paramMap));
				
				
				// 상품 등록 Ajax
				$.ajax({
					url: '${ pageContext.servletContext.contextPath }/admin/product/regist',
					type: 'post',
					dataType: 'json',
					data: JSON.stringify(paramMap),
					contentType: 'application/json',
			        success: function(response) {
						console.log(response);
					},
					error: function(jqXHR, status, e) {
						console.error('[ERROR] ' + status + ': ' + e);
					}
				});
			});
		});
		
		// 카테고리 선택 시 동작
		function addCategory(no, name) {
			var category = {
				'no': no
			};
			categoryList.push(category);
			
			var selectedCategory = '<li class="list-group-item">' + name + '&nbsp;&nbsp;<i onclick="deleteCategory(' + no + ', this)" class="fas fa-trash-alt"></i></li>';
			$('#selectedCategoryList').append(selectedCategory);
		}
		// 선택했던 카테고리 삭제 시 동작
		function deleteCategory(no, obj) {
			const categoryToFind = categoryList.find(function(category) {return category.no === no});
			const index = categoryList.indexOf(categoryToFind);
			if(index > -1) categoryList.splice(index, 1);
			
			remove_entry($(obj).parent());
		}
		function remove_entry(entry) {
			entry.remove();
		}
	</script>
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
				<h3 style="margin: 5% 0;">상품등록</h3>
			
				<table class="table">
					<caption>기본 정보</caption>
					<tr>
						<th>상품명<span>(필수)</span></th>
						<td>
							<input type="text" id="inputProductName" class="form-control form-control-sm" placeholder="예시) 쉬폰 원피스" name="name" required>
						</td>
					</tr>
					<tr>
						<th>판매가<span>(필수)</span></th>
						<td>
							<input type="text" id="inputSellPrice" class="form-control form-control-sm" name="sellPrice" required>
						</td>
					</tr>
					<tr>
						<th>공급가</th>
						<td>
							<input type="text" id="inputSupplyPrice" class="form-control form-control-sm" name="supplyPrice">
						</td>
					</tr>
					<tr>
						<th>상품 요약설명</th>
						<td>
							<input type="text" id="inputSummaryDescription" class="form-control form-control-sm" name="summaryDescription" required>
						</td>
					</tr>
					<tr>
						<th height="400">상품 상세설명</th>
							<td>
								<textarea id="inputDetailedDescription" name="detailedDescription" style="height: 350px;"></textarea>
							</td>
<!-- Smart Editor -->	
<script type="text/javascript">
	var oEditors = [];
	nhn.husky.EZCreator.createInIFrame({
		oAppRef: oEditors,
		elPlaceHolder: "inputDetailedDescription",
		sSkinURI: "${ pageContext.servletContext.contextPath }/resources/smarteditor/SmartEditor2Skin.html",
		fCreator: "createSEditor2"
	});
	
	// 스마트에디터 안의 값 얻기
	function getDetailedDescription() {
		// 에디터 내용이 textarea에 적용됨
		oEditors.getById["inputDetailedDescription"].exec("UPDATE_CONTENTS_FIELD", []);
		
		return $('#inputDetailedDescription').val();
	}
</script>
					</tr>
					<tr>
						<th>이미지</th>
						<td>
							시간 관계상 구현하지 않음
						</td>
					</tr>
					<tr>
						<th>상품 하나 당<br>중량(kg)</th>
						<td>
							<input type="text" id="inputWeight" class="form-control form-control-sm" name="weight">
						</td>
					</tr>
				</table>
				
				
				<table class="table">
					<caption>쇼핑몰 진열설정</caption>
					<tr>
						<th>진열상태</th>
						<td>
							<div class="custom-control custom-radio custom-control-inline">
								<input type="radio" class="custom-control-input" id="inputDisplayStatusMain" name="displayStatus" value="MAIN">
								<label class="custom-control-label" for="inputDisplayStatusMain">메인 진열</label>
							</div>
							<div class="custom-control custom-radio custom-control-inline">
								<input type="radio" class="custom-control-input" id="inputDisplayStatusEvent" name="displayStatus" value="EVENT">
								<label class="custom-control-label" for="inputDisplayStatusEvent">이벤트 진열</label>
							</div>
							<div class="custom-control custom-radio custom-control-inline">
								<input type="radio" class="custom-control-input" id="inputDisplayStatusNone" name="displayStatus" value="NONE" checked>
								<label class="custom-control-label" for="inputDisplayStatusNone">해당 사항 없음</label>
							</div>
						</td>
					</tr>
					<tr>
						<th>판매상태</th>
						<td>
							<div class="custom-control custom-radio custom-control-inline">
								<input type="radio" class="custom-control-input" id="inputAvailabilityY" name="availability" value="Y">
								<label class="custom-control-label" for="inputAvailabilityY">판매함</label>
							</div>
							<div class="custom-control custom-radio custom-control-inline">
								<input type="radio" class="custom-control-input" id="inputAvailabilityN" name="availability" value="N" checked>
								<label class="custom-control-label" for="inputAvailabilityN">판매안함</label>
							</div>
						</td>
					</tr>
					<tr>
						<th>상품분류</th>
						<td>
							<div class="row">
								<div class="col-sm-6">
									<div class="list-group">
										<c:forEach var="category" items="${ categories }">
											<a onclick="addCategory(${ category.no }, '${ category.name }');"
												class="list-group-item"
												style="cursor: pointer; padding-left: ${ category.level * 15 + 20 }px;">
												<c:if test="${ category.level > 0 }">
													<img src="">
												</c:if> <c:out value="${ category.name }" />
											</a>
										</c:forEach>
									</div>
								</div>
								<div class="col-sm-6">
									<h5>등록된 카테고리 리스트</h5>
									<ul id="selectedCategoryList" class="list-group">
									</ul>
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
							<div class="custom-control custom-radio custom-control-inline">
								<input type="radio" class="custom-control-input" id="inputOptionAvailableY" name="optionAvailable" value="Y">
								<label class="custom-control-label" for="inputOptionAvailableY">사용함</label>
							</div>
							<div class="custom-control custom-radio custom-control-inline">
								<input type="radio" class="custom-control-input" id="inputOptionAvailableN" name="optionAvailable" value="N" checked>
								<label class="custom-control-label" for="inputOptionAvailableN">사용안함</label>
							</div>
						</td>
					</tr>
					
					<tr>
						<th>옵션설정</th>
						<td>
							<table class="table" style="margin: 0;">
								<tr>
									<th>옵션명</th>
									<th>옵션값</th>
									<th></th>
								</tr>
								<tr class="optionTemplate">
									<td>
										<input type="text" id="optionName1" class="form-control form-control-sm" placeholder="예시) 색상" required>
									</td>
									<td>
										<input type="text" id="optionName1" class="form-control form-control-sm" placeholder="예시) 네이비" required>
									</td>
									<td>
										<button type="button" class="btn btn-outline-secondary btn-sm">
											<i class="fas fa-plus"></i>
										</button>
										<button type="button" class="btn btn-outline-secondary btn-sm">
											<i class="fas fa-minus"></i>
										</button>
									</td>
								</tr>
							</table>
							
							<br>
							<div style="text-align: right;">
								<button type="button" class="btn btn-secondary btn-sm">
									옵션품목 만들기
								</button>
								<button type="button" class="btn btn-outline-secondary btn-sm">
									품목정보 초기화
								</button>
							</div>
							<br>
							
							<table class="table">
								<tr>
									<th>옵션 (품목코드)</th>
									<th>추가금액</th>
									<th>재고관리</th>
									<th>재고수량</th>
									<th>삭제</th>
								</tr>
								<tr class="optionItemTemplate">
									<td>
									</td>
									<td>
										<input type="number" id="optionItemAdditionalAmount" class="form-control form-control-sm" value="0" style="max-width: 90px;">
									</td>
									<td>
										<select class="form-control form-control-sm">
											<option value="">재고수량 관리 안함</option>
											<option value="">재고수량 소진 시 품절 표시함</option>
										</select>
									</td>
									<td>
										<input type="number" id="optionItemStockQuantity" class="form-control form-control-sm" value="0" style="max-width: 90px;">
									</td>
									<td>
										<button type="button" class="btn btn-outline-secondary btn-sm">
											<i class="fas fa-trash-alt"></i>
										</button>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					
					<tr>
						<th style="max-width: 35px !important;">재고관리</th>
						<td>
							<div class="custom-control custom-radio custom-control-inline">
								<input type="radio" class="custom-control-input" id="inputManageStatusStock" name="manageStatus" value="STOCK">
								<label class="custom-control-label" for="inputManageStatusStock">사용함</label>
							</div>
							<div class="custom-control custom-radio custom-control-inline">
								<input type="radio" class="custom-control-input" id="inputManageStatusNonStock" name="manageStatus" value="NON_STOCK" checked>
								<label class="custom-control-label" for="inputManageStatusNonStock">사용안함</label>
							</div>
						</td>
					</tr>
				</table>
				
				<br><hr><br>
				
				<button type="button" id="final-submit" class="btn btn-primary btn-lg btn-block">상품등록</button>
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

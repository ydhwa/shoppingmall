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
							<input type="text" id="inputName" class="form-control form-control-sm" name="summaryDescription" required>
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
	
	/* 편집 내용 서버로 전송 */
	// 저장을 위한 액션 시 submitContents 호출된다고 하자.
	function submitContents(elClickedObj) {
		// 에디터 내용이 textarea에 적용됨
		oEditors.getById["inputDetailedDescription"].exec("UPDATE_CONTENTS_FIELD", []);
		
		// 에디터의 내용에 대한 값 검증은 document.getElementById("contents").value를 이용하여 처리한다.
		
		try {
			elClickedObj.form.submit();
		} catch(e) {}		
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
								<div class="col-sm-6">선택할 카테고리들이 잔뜩 뽑힐 예정</div>
								<div class="col-sm-6">등록할 카테고리 목록이 나올 예정</div>
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
										<input type="text" id="optionName1" class="form-control form-control-sm" placeholder="예시) 색상">
									</td>
									<td>
										<input type="text" id="optionName1" class="form-control form-control-sm" placeholder="예시) 네이비">
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
				
				<button type="button" class="btn btn-primary btn-lg btn-block">상품등록</button>
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

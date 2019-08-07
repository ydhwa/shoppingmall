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
	<script src="${ pageContext.servletContext.contextPath }/assets/js/jquery/jquery.min.js"></script>
	
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
				<h3 style="margin: 5% 0;">회원목록</h3>
			
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
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-bs4.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-bs4.js"></script>
  </head>
  <body>
    <div id="summernote"></div>
    <script>
      $('#summernote').summernote({
        tabsize: 2,
        height: 350
      });
    </script>
  </body>
</html>
						</td>
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
							<div class="custom-control custom-radio">
								<input type="radio" class="custom-control-input" id="inputDisplayStatusMain" name="displayStatus" value="MAIN">
								<label class="custom-control-label" for="inputDisplayStatusMain">메인 진열</label>
							</div>
							<div class="custom-control custom-radio">
								<input type="radio" class="custom-control-input" id="inputDisplayStatusEvent" name="displayStatus" value="EVENT">
								<label class="custom-control-label" for="inputDisplayStatusEvent">이벤트 진열</label>
							</div>
							<div class="custom-control custom-radio">
								<input type="radio" class="custom-control-input" id="inputDisplayStatusNone" name="displayStatus" value="NONE" checked>
								<label class="custom-control-label" for="inputDisplayStatusNone">해당 사항 없음</label>
							</div>
						</td>
					</tr>
					<tr>
						<th>판매상태</th>
						<td>
							<div class="custom-control custom-radio">
								<input type="radio" class="custom-control-input" id="inputAvailabilityY" name="availability" value="Y">
								<label class="custom-control-label" for="inputAvailabilityY">판매함</label>
							</div>
							<div class="custom-control custom-radio">
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
							<div class="custom-control custom-radio">
								<input type="radio" class="custom-control-input" id="inputOptionAvailableY" name="optionAvailable" value="Y">
								<label class="custom-control-label" for="inputOptionAvailableY">사용함</label>
							</div>
							<div class="custom-control custom-radio">
								<input type="radio" class="custom-control-input" id="inputOptionAvailableN" name="optionAvailable" value="N" checked>
								<label class="custom-control-label" for="inputOptionAvailableN">사용안함</label>
							</div>
						</td>
					</tr>
					<tr>
						<th style="max-width: 35px !important;">재고관리</th>
						<td>
							<div class="custom-control custom-radio">
								<input type="radio" class="custom-control-input" id="inputManageStatusStock" name="manageStatus" value="STOCK">
								<label class="custom-control-label" for="inputManageStatusStock">사용함</label>
							</div>
							<div class="custom-control custom-radio">
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

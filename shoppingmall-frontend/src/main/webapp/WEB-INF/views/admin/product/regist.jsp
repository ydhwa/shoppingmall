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
	<!-- dm-uploader(css) -->
	<link href="${pageContext.servletContext.contextPath }/assets/css/jquery.dm-uploader.min.css" rel="stylesheet">
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
			// 몇 가지 입력 폼들 비활성화 시킴
			$('#optionSettings').hide();
			$('#stockForm').hide();
			
			// 옵션 설정 박스 활성화
			$('input:radio[name=optionAvailable]').click(function() {
				if($('input[name=optionAvailable]:checked').val() == 'Y') {
					$('#optionSettings').show();
					$('#stockManageStatusForm').hide();
				} else if($('input[name=optionAvailable]:checked').val() == 'N') {
					$('#optionSettings').hide();
					$('#stockManageStatusForm').show();
	        	}
			});
			// 재고 설정 박스 활성화(옵션 설정 비활성화 시 확인할 수 있음)
			$('input:radio[name=manageStatus]').click(function() {
				if($('input[name=manageStatus]:checked').val() == 'STOCK') {
					$('#stockForm').show();
				} else if($('input[name=manageStatus]:checked').val() == 'NON_STOCK') {
					$('#stockForm').hide();
	        	}
			});
			
			
			// 상품 등록 시 동작
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
				// 품목 추가하지 못했던 나머지 항목 추가
				$('.optionItemTemplate').each(function(index, item) {
					productOptionItemList[index].additionalAmount = $(item).children('td').eq(1).children().eq(0).val();
					productOptionItemList[index].manageStatus = $(item).children('td').eq(2).children().eq(0).val();
					productOptionItemList[index].stockQuantity = $(item).children('td').eq(3).children().eq(0).val();
				});
				
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
						window.location.href = '${ pageContext.servletContext.contextPath }/admin/product/regist-success';
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
		
		// 옵션 추가 시 동작
		function addOptionForm() {
			var optionForm =
				'<tr class="optionTemplate">' +
					'<td>' +
						'<input type="text" class="optionName class="form-control form-control-sm" placeholder="예시) 색상" required>' +
					'</td>' + 
					'<td class="optionValueFormWrapper">' + 
						'<input type="text" onkeypress="return optionValueEvent(this, event);" class="form-control form-control-sm" placeholder="예시) 네이비;블랙;화이트" required>' + 
					'</td>' + 
					'<td>' + 
						'<button type="button" class="btn btn-outline-secondary btn-sm" onclick="addOptionForm();">' + 
							'<i class="fas fa-plus"></i>' + 
						'</button>' + 
						'<button type="button" class="btn btn-outline-secondary btn-sm" onclick="deleteOptionForm(this);">' + 
							'<i class="fas fa-minus"></i>' + 
						'</button>' + 
					'</td>' + 
				'</tr>';
			
			$('.optionTemplate').parent().append(optionForm);
		}
		// 옵션 삭제 시 동작
		function deleteOptionForm(obj) {
			$(obj).parent().parent().remove();
		}
		// 옵션값 추가 시 동작(;나 Enter 누르면 기존 입력했던 값이 옵션값으로 추가됨)
		function optionValueEvent(obj, event) {
			if(event.keyCode == 59 || event.keyCode == 13) {
				if($(obj).val() == '') {
					return ;
				} else {
					event.preventDefault();
					var optionValue = '<span>' + $(obj).val() + '</span>&nbsp;';
					$(obj).parent().append(optionValue);
					$(obj).val('');
					$(obj).focus();
				}
			}
		}
		
		// 옵션 리스트 생성
		function makeOptions() {
			// 옵션 품목 생성 위한 리스트
			var optionListOfListForItemList = new Array();
			var valueIndexListOfListForItemList = new Array();
			var optionNameKeys = '';
			
			$('.optionTemplate').each(function(index, item) {
				var optionName = $(item).children().eq(0).children('.optionName').eq(0).val();
				var optionValues = $(item).children().eq(1).children('span');
				
				// 비어있는 값 체크
				if(optionName == '' || optionValues.length == 0) {
					alert('옵션명과 옵션값은 필수로 입력해야 합니다.');
					return ;
				}
				
				var productOptionValueList = new Array();
				
				// 옵션 품목 생성 위한 리스트2
				var optionListForItemList = new Array();
				var valueIndexListForItemList = new Array();
				$(optionValues).each(function(index1, item1) {
					var optionValue = $(item1).text();
					var productOptionValue = {
						'value': optionValue
					};
					productOptionValueList.push(productOptionValue);
					
					// 옵션 품목 생성 위한 객체 생성 후 리스트2에 넣음
					var optionForItemList = optionName + '=' + optionValue;
					optionListForItemList.push(optionForItemList);
					valueIndexListForItemList.push(index1 + 1);
				});
				
				var productOption = {
					'name': optionName,
					'productOptionValueList': productOptionValueList
				};
				
				// 옵션 리스트에 넣어둠
				productOptionList.push(productOption);
				
				// 옵션 품목 생성 위한 리스트에도 추가
				optionListOfListForItemList.push(optionListForItemList);
				valueIndexListOfListForItemList.push(valueIndexListForItemList);
				optionNameKeys += (index + 1) + ';';
			});
			
			// 옵션 품목에 들어갈 항목 생성
			var allOptionItemList = allPossibleCases(optionListOfListForItemList);
			var allValueIndexItemList = allPossibleCases(valueIndexListOfListForItemList);
			// 옵션 품목 생성
			makeOptionItems(allOptionItemList, allValueIndexItemList, optionNameKeys.substr(0, optionNameKeys.length - 1));

			// 동적으로 옵션 품목들 추가
			appendOptionItems();
		}
		// 옵션 품목 생성
		function makeOptionItems(optionDetailsList, optionValueKeyList, optionNameKey) {
			productOptionItemList = [];
			for(var i = 0; i < optionDetailsList.length; i++) {
				var productOptionItem = {
					'details': optionDetailsList[i],
					'optionNameKeys': optionNameKey,
					'optionValueKeys': optionValueKeyList[i],
					'availability': 'Y'
				};
				productOptionItemList.push(productOptionItem);
			}
		}
		// 동적으로 옵션 품목들 추가
		function appendOptionItems() {
			productOptionItemList.forEach(function(item, index, array) {
				var template = 
					'<tr class="optionItemTemplate">' +
						'<td>'+ item.details + '</td>' +
						'<td>' + 
							'<input type="number" class="optionItemAdditionalAmount form-control form-control-sm" value="0" style="max-width: 90px;">' +
						'</td>' + 
						'<td>' + 
							'<select name="optionItemManageStatus" class="form-control form-control-sm" onchange="selectEvent(this);">' + 
								'<option value="NON_STOCK">재고수량 관리 안함</option>' + 
								'<option value="STOCK">재고수량 소진 시 품절 표시함</option>' + 
							'</select>' + 
						'</td>' + 
						'<td class="itemStock">' + 
							'<input type="number" class="optionItemStockQuantity form-control form-control-sm" value="0" style="max-width: 90px;">' + 
						'</td>' + 
						'<td>' + 
							'<button type="button" onclick=\'deleteOptionItem("' + item.details + '", this)\' class="btn btn-outline-secondary btn-sm">' + 
								'<i class="fas fa-trash-alt"></i>' + 
							'</button>' + 
						'</td>' + 
					'</tr>';
				$('#optionItemTable').append(template);
			});
			
			$('.optionItemStockQuantity').hide();		// default: NON_STOCK
		}
		// 옵션 품목 -> select box 선택 시 동작
		function selectEvent(obj) {
			var value = $(obj).val();
			
			if(value == 'STOCK') {
				$(obj).parent().parent().children('.itemStock').eq(0).children('.optionItemStockQuantity').eq(0).show();
			} else {
				// 'NON_STOCK'
				$(obj).parent().parent().children('.itemStock').eq(0).children('.optionItemStockQuantity').eq(0).hide();
			}
		}
		// 옵션 품목 삭제(리스트에서도 삭제하고, HTML element도 삭제함)
		function deleteOptionItem(details, obj) {
			const optionItemToFind = productOptionItemList.find(function(optionItem) {return optionItem.details === details});
			const index = productOptionItemList.indexOf(optionItemToFind);
			if(index > -1) productOptionItemList.splice(index, 1);
			
			remove_entry($(obj).parent().parent());
		}
		
		// 옵션 품목 조합
		function allPossibleCases(arr) {
			if (arr.length === 0) {
				return [];
			} else if (arr.length ===1) {
				return arr[0];
			}
			else {
				var result = [];
				var allCasesOfRest = allPossibleCases(arr.slice(1));  // recur with the rest of array
				for (var c in allCasesOfRest) {
					for (var i = 0; i < arr[0].length; i++) {
						result.push(arr[0][i] + ';' + allCasesOfRest[c]);
					}
				}
				return result;
			}
		}
		
		// 품목 정보 초기화
		function deleteOptions() {
			productOptionItemList = [];
			$('#optionItemTable').empty();
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
<div class="row">
	<div class="col-md-6 col-sm-12">
		<form name="uploadForm" id="uploadForm" enctype="multipart/form-data" method="post">
		<!-- /uploader -->
		<div id="drag-and-drop-zone" class="dm-uploader p-5">
			<h5 class="text-center">Drag &amp; drop files here</h5>
<!-- 			<div class="btn btn-primary btn-block mb-5"> -->
<!-- 				<span>Open the file Browser</span> -->
<!-- 				<input type="file" id="click-zone" title='Click to add Files' /> -->
<!-- 			</div> -->
		</div>
		</form>
		
		<button type="button" onclick="uploadFile(); return false;" class="btn btn-block btn-primary btn-md">파일 업로드</button>
	</div>

	<div class="col-md-6 col-sm-12">
		<!-- /file list -->
		<div class="card h-100">
			<div class="card-header">파일 리스트</div>
			<ul class="list-unstyled p-2 d-flex flex-column col" id="files">
<!-- 				<li class="text-muted text-center empty">No files uploaded.</li> -->
			</ul>
		</div>
	</div>
</div>
<script type="text/javascript">
	var fileIndex = 0;
	var totalFileSize = 0;
	var fileList = new Array();
	var fileSizeList = new Array();
	var uploadSize = 50;
	var maxUploadSize = 500;
	
	$(function() {
		fileDropDown();
	});
	
	function fileDropDown() {
		var dropzone = $('#drag-and-drop-zone');
		
		dropzone.on('dragenter', function(e) {
			e.stopPropagation();
			e.preventDefault();
			dropzone.css('background-color', '#e3f2fc');
		});
		dropzone.on('dragleave',function(e){
			e.stopPropagation();
			e.preventDefault();
			dropzone.css('background-color','#FFFFFF');
		});
		dropzone.on('dragover',function(e){
			e.stopPropagation();
			e.preventDefault();
			dropzone.css('background-color','#E3F2FC');
		});
		dropzone.on('drop',function(e){
			e.preventDefault();
			dropzone.css('background-color','#FFFFFF');
			var files = e.originalEvent.dataTransfer.files;
			if(files != null) {
				if(files.length < 1) {
					alert("폴더 업로드 불가");
					return;
				}
				selectFile(files);
			} else {
				alert("ERROR");
			}
		});
	}
	
	function selectFile(files) {
		if(files != null) {
			for(var i = 0; i < files.length; i++) {
				var fileFullName = files[i].name;
				var fileIndex = fileFullName.lastIndexOf('.');
				var fileName = fileFullName.substr(0, fileIndex);
				var ext = fileFullName.substr(fileIndex + 1);
				var fileSize = files[i].size / 1024 / 1024;
				
                if($.inArray(ext, ['exe', 'bat', 'sh', 'java', 'jsp', 'html', 'js', 'css', 'xml']) >= 0) {
					alert('등록 불가 확장자');
					break;
//                 } else if(fileSize > uploadSize) {
//                 	alert('용량 초과\n업로드 가능 용량: ' +  uploadSize + ' MB');
//                 	break;
                } else {
                	totalFileSize += fileSize;
                	fileList[fileIndex] = files[i];
                	fileSizeList[fileIndex] = fileSize;
                	addFileList(fileIndex, fileFullName, fileSize);
                	fileIndex++;
                }
			}
		} else {
			alert('ERROR');
		}
	}
	
	function addFileList(fIndex, fileName, fileSize) {
		var html = '' + 
			'<li class="text-muted text-center empty" id="fileItem_' + fIndex + '">' +
				fileName + 
				'<button type="button" class="btn btn-sm" onclick="deleteFile(' + fIndex + '); return false;">' +
					'<i class="fas fa-trash-alt"></i>' +
				'</button>' +  
			'</li>';
		$('#files').append(html);
	}
	
	function deleteFile(fIndex) {
		totalFileSize -= fileSizeList[fIndex];
		delete fileList[fIndex];
		delete fileSizeList[fIndex];
		$('#fileItem_' + fIndex).remove();
	}
	
	function uploadFile() {
		var uploadFileList = Object.keys(fileList);
		console.log(fileList);
		
		if(uploadFileList.length == 0) {
			return ;
		}
		
		if(totalFileSize > maxUploadSize) {
			alert('업로드 허용 용량 초과');
			return ;
		}
		
		if(confirm('이미지를 등록하시겠습니까?')) {
			var form = $('#uploadForm')[0];
			var formData = new FormData(form);
			
			for(var i = 0; i < uploadFileList.length; i++) {
				formData.append('files', fileList[uploadFileList[i]]);
			}
			
			$.ajax({
				url: '${ pageContext.servletContext.contextPath }/admin/file/upload',
				data: formData,
				type: 'post',
				enctype: 'multipart/form-data',
				processData: false,
				contentType: false,
				dataType: 'json',
				cache: false,
				success: function(result) {
					if(result.data.length> 0) {
						for(var i = 0; i < result.data.length; i++) {
							productImageList.push(result.data[i]);
						}
						console.log(productImageList);
						alert('성공');
					} else {
						alert('실패');
					}
				}
			});
		}
	}

</script>

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
					
					<tr id="optionSettings">
						<th>옵션설정</th>
						<td>
							<table class="table" style="margin: 0;">
								<tr>
									<th>옵션명</th>
									<th>옵션값&nbsp;&nbsp;(Enter를 누르거나 ;로 각 옵션값을 구분할 수 있습니다.)</th>
									<th></th>
								</tr>
								<tr class="optionTemplate">
									<td>
										<input type="text" class="optionName form-control form-control-sm" placeholder="예시) 색상" required>
									</td>
									<td class="optionValueFormWrapper">
										<input type="text" onkeypress="return optionValueEvent(this, event);" class="form-control form-control-sm" placeholder="예시) 네이비;블랙;화이트" required>
									</td>
									<td>
										<button type="button" class="btn btn-outline-secondary btn-sm" onclick="addOptionForm();">
											<i class="fas fa-plus"></i>
										</button>
									</td>
								</tr>
							</table>
							
							<br>
							<div style="text-align: right;">
								<button type="button" class="btn btn-secondary btn-sm" onclick="makeOptions();">
									옵션품목 만들기
								</button>
								<button type="button" class="btn btn-outline-secondary btn-sm" onclick="deleteOptions();">
									품목정보 초기화
								</button>
							</div>
							<br>
							
							<table class="table" id="optionItemTable">
								<tr>
									<th>옵션 (품목코드)</th>
									<th>추가금액</th>
									<th>재고관리</th>
									<th>재고수량</th>
									<th>삭제</th>
								</tr>
							</table>
						</td>
					</tr>
					
					<tr id="stockManageStatusForm">
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
							<div id="stockForm" class="col-xs-2" style="padding-top: 3%;">
								<input style="width: 200px;" type="number" id="inputStockQuantity" class="form-control form-control-sm" name="stockQuantity" value="0">
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

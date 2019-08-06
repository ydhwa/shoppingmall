<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

			<div class="col-lg-3">
				<a href="${pageContext.servletContext.contextPath }/admin" style="color: black; text-decoration: none;"><h1 class="my-4" style="letter-spacing: 3px; font-family: consolas; text-transform: uppercase; text-align: center;">admin</h1></a>
				<div class="list-group">
					<a href="${ pageContext.servletContext.contextPath }/admin/user" class="list-group-item">
						회원관리
					</a>
					<a href="${ pageContext.servletContext.contextPath }/admin/product" class="list-group-item">
						상품관리
					</a>
					<a href="${ pageContext.servletContext.contextPath }/admin/bucket" class="list-group-item">
						장바구니관리
					</a>
				</div>
			</div>

			  <!-- Bootstrap core JavaScript -->
  <script src="${pageContext.servletContext.contextPath }/assets/js/jquery/jquery.min.js"></script>
  <script src="${pageContext.servletContext.contextPath }/assets/bootstrap/js/bootstrap.bundle.min.js"></script>
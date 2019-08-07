<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

			<div class="col-lg-3">
				<a href="${pageContext.servletContext.contextPath }/admin" style="color: black; text-decoration: none;"><h1 class="my-4" style="letter-spacing: 3px; font-family: consolas; text-transform: uppercase; text-align: center;">admin</h1></a>
				<div class="list-group">
					<a href="${ pageContext.servletContext.contextPath }/admin/user/list" class="list-group-item list-group-item-dark">
						회원목록
					</a>
					<a href="${ pageContext.servletContext.contextPath }/admin/product/list" class="list-group-item list-group-item-dark">
						상품목록
					</a>
					<a href="${ pageContext.servletContext.contextPath }/admin/product/regist" class="list-group-item list-group-item-dark">
						상품등록
					</a>
					<a href="${ pageContext.servletContext.contextPath }/admin/bucket" class="list-group-item list-group-item-dark">
						장바구니목록
					</a>
				</div>
			</div>

			  <!-- Bootstrap core JavaScript -->
  <script src="${pageContext.servletContext.contextPath }/assets/js/jquery/jquery.min.js"></script>
  <script src="${pageContext.servletContext.contextPath }/assets/bootstrap/js/bootstrap.bundle.min.js"></script>
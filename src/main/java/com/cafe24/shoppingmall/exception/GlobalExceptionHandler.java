package com.cafe24.shoppingmall.exception;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cafe24.shoppingmall.dto.JSONResult;
import com.fasterxml.jackson.databind.ObjectMapper;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Log LOGGER = LogFactory.getLog(GlobalExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	public void handleException(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {
		// 1. 로깅
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors)); // 보조 스트림 이용하여 연결
		LOGGER.error(errors.toString());

		String accept = request.getHeader("accept");
		if (accept.matches(".*application/json.*")) { // application/json을 포함한 모든 문자
			// JSON 응답
			response.setStatus(HttpServletResponse.SC_OK);
			
			JSONResult jsonResult = JSONResult.failure(e.toString());
			// 결과를 직접 JSON 형식으로 만들어야 함.
			String result = new ObjectMapper().writeValueAsString(jsonResult);

			System.out.println(result);
			OutputStream os = response.getOutputStream();	// socketOutputStream이 나옴.
			os.write(result.getBytes("utf-8"));
			os.close();
		} else {
			// 2. 안내 페이지 가기 + 정상 종료(response)
			request.setAttribute("uri", request.getRequestURI());
			request.setAttribute("exception", errors.toString());
			request.getRequestDispatcher("/WEB-INF/views/error/exception.jsp").forward(request, response);
		}
	}
}

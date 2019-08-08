package com.cafe24.shoppingmall.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shoppingmall.dto.JSONResult2;
import com.cafe24.shoppingmall.vo.ProductImageVo;

@RestController("fileUploadController")
@RequestMapping("/admin/file/upload")
public class FileUploadController {
	private static final String SAVE_IMAGE_PATH = "/shoppingmall-uploads/";
	private static final String IMAGE_URL = "/assets/images/";
	
	@RequestMapping(method=RequestMethod.POST, value="")
	public ResponseEntity<JSONResult2> uploadImageBase64(@RequestBody ProductImageVo productImageVo) {
		System.out.println(productImageVo);
		
		FileOutputStream fileOutputStream = null;
		
		try {
			byte[] imgBytes = Base64.decodeBase64(productImageVo.getBase64EncodingData());
			String fileName = generateSaveFileName(productImageVo.getName());
			
			fileOutputStream = new FileOutputStream(SAVE_IMAGE_PATH + fileName);
			fileOutputStream.write(imgBytes);
			
			productImageVo.setPath(IMAGE_URL + fileName);
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult2.fail("파일 업로드 에러"));
		} finally {
			if(fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					return ResponseEntity.status(HttpStatus.OK).body(JSONResult2.fail("fileOutputStream close error!!"));
				}
			}
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult2.success(productImageVo));
	}

	private String generateSaveFileName(String extName) {
		Calendar calendar = Calendar.getInstance();
		String filename = String.format("%s%s%s%s%s%s%s%s.%s", 
				calendar.get(Calendar.YEAR), 
				calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DATE),
				calendar.get(Calendar.HOUR),
				calendar.get(Calendar.MINUTE),
				calendar.get(Calendar.SECOND),
				calendar.get(Calendar.MILLISECOND),
				UUID.randomUUID().toString(),	// 동시에 다른 사용자가 파일 업로드를 진행할 수 있으므로
				extName);

		return filename;
	}
}

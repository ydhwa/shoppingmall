package com.cafe24.shoppingmall.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.shoppingmall.dto.JSONResult2;
import com.cafe24.shoppingmall.vo.ProductImageVo;

@RestController("fileUploadController")
@RequestMapping("/admin/file/upload")
public class FileUploadController {
	private static final String SAVE_IMAGE_PATH = "/shoppingmall-uploads/";
	private static final String IMAGE_URL = "/assets/images/";



	@RequestMapping(method=RequestMethod.POST, value="")
	public ResponseEntity<JSONResult2> uploadImages(
			MultipartFile[] files,
			Model model) {

		String url = "";
		List<ProductImageVo> imageList = new ArrayList<>();

		try {
			for(MultipartFile multipartFile: files) {
				if (multipartFile.isEmpty()) {
					ResponseEntity.status(HttpStatus.OK).body(JSONResult2.fail(url));
				}

				String originalFileName = multipartFile.getOriginalFilename();
				String extName = originalFileName.substring(originalFileName.lastIndexOf('.') + 1);
				String saveFileName = generateSaveFileName(extName);

//				System.out.printf("########### %s\n########### %s\n########### %s\n########### %d\n",
//						originalFileName, extName, saveFileName, fileSize);
				
				ProductImageVo productImageVo = new ProductImageVo(saveFileName, extName, IMAGE_URL);
				imageList.add(productImageVo);

				byte[] fileData = multipartFile.getBytes();
				OutputStream os = new FileOutputStream(SAVE_IMAGE_PATH + "/" + saveFileName + "." + extName);
				os.write(fileData);
				os.close();

				url = IMAGE_URL + "/" + saveFileName;
			}

		} catch (IOException e) {
			throw new RuntimeException("Fileupload ERROR: " + e);
		}

		return ResponseEntity.status(HttpStatus.OK).body(JSONResult2.success(imageList));
	}

	private String generateSaveFileName(String extName) {
		Calendar calendar = Calendar.getInstance();
		String filename = String.format("%s%s%s%s%s%s%s%s", 
				calendar.get(Calendar.YEAR), 
				calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DATE),
				calendar.get(Calendar.HOUR),
				calendar.get(Calendar.MINUTE),
				calendar.get(Calendar.SECOND),
				calendar.get(Calendar.MILLISECOND),
				UUID.randomUUID().toString()).replace("-", "");

		return filename;
	}
}

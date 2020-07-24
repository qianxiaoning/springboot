package com.qxn.controller;

import com.qxn.pojo.ProductDesc;
import com.qxn.service.FileService;
import com.qxn.service.ProductCategoryService;
import com.qxn.service.ProductService;
import com.qxn.vo.ProductAndDesc;
import com.qxn.vo.SysResult;
import com.qxn.vo.UITable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@RestController
public class FileController {
	@Autowired
	private FileService fileService;

	@PostMapping("/fileTest")
	//spring图片上传接口，spring.MultipartFile
	public SysResult fileTest(MultipartFile fileTest) throws IOException {
		//1.图片名字
		String fileName = fileTest.getOriginalFilename();
		//2.文件对象上传目录
		File dir = new File("D:/qxn/file/testFileFolder/");
		if(!dir.exists()) {
			//如果目录不存在，创建目录
			dir.mkdirs();
		}
		String path = "D:/qxn/file/testFileFolder/"+fileName;
		File file = new File(path);
		//3.文件输出
		fileTest.transferTo(file);
		return SysResult.success("文件上传成功");
	}
	@PostMapping("/image/upload")
	//spring图片上传接口，spring.MultipartFile
	public SysResult imageUpload(MultipartFile[] fileImages){
		return fileService.imageUpload(fileImages);
	}

}

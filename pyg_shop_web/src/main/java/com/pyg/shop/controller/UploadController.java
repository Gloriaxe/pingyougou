package com.pyg.shop.controller;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import entity.Result;
import util.FastDFSClient;

@RestController

public class UploadController {
	@Value("${FILE_SERVER_URL}")
	private String FILE_SERVER_URL;//文件服务器地址
	
	@RequestMapping("/upload")
	public Result upload(MultipartFile file) {
		//獲取擴展名
		String filename = file.getOriginalFilename();
		String extName = filename.substring(filename.lastIndexOf(".")+1);
		//創建一個FastDFS客戶端
		try {
			FastDFSClient fastDFSClient=new FastDFSClient("classpath:config/fdfs_client.conf");
			String path = fastDFSClient.uploadFile(file.getBytes(),extName);
			String url=FILE_SERVER_URL+path;
			return new Result(true, url);
			
		} catch (Exception e) {
			return new Result(false,"上傳失敗");
		}
		
		
	}
}

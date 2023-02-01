package com.test.controller;


import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/upload")
public class UploadController {
	private String uploadDir = "c:\\upload";
	
	@GetMapping("/form")
	public void func1() {
		System.out.println("Upload form...");
	}
	
	@PostMapping("/reqUpload")
	public void func2(@RequestParam("files") MultipartFile[] uploadfiles, Model model) throws Exception{
		
		System.out.println("UPLOAD FILE : " + uploadfiles.length);
		
		//uploaddir / UUID.random() 폴더 생성
		String path = uploadDir+File.separator+UUID.randomUUID();
		File dir = new File(path);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		for(MultipartFile part : uploadfiles) {
			System.out.println("------------------------------------------");
			System.out.println("file name : "  + part.getOriginalFilename());
			System.out.println("file size : " + part.getSize());
			System.out.println("------------------------------------------");
			
			//파일명 추출
			String filename =part.getOriginalFilename(); 
			//파일객체 생성
			File savefile = new File(path,filename);
			//UPLOAD
			part.transferTo(savefile);
		}
		
	}
	
	
}

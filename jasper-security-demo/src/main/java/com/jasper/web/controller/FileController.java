package com.jasper.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jasper.dto.FileInfo;

@RestController
@RequestMapping("/file")
public class FileController {

	String path = System.getProperty("user.dir");

	@PostMapping
	public FileInfo upload(MultipartFile file) throws IllegalStateException, IOException {
		System.out.println("FileController : upload : " + file.getName());
		System.out.println("FileController : upload : " + file.getOriginalFilename());
		System.out.println("FileController : upload : " + file.getSize());

		File localFile = new File(path, new Date().getTime() + ".txt");
		file.transferTo(localFile);

		return new FileInfo(localFile.getAbsolutePath());
	}

	@GetMapping("/{id}")
	public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) {
		// JDK 1.7 will help you close the stream if you put them in the try brackets
		try (InputStream inputStream = new FileInputStream(new File(path, id + ".txt"));
				OutputStream outputStream = response.getOutputStream();) {
			response.setContentType("application/x-download");
			response.addHeader("content-disposition", "attachment;filename=downloadtest.txt");

			IOUtils.copy(inputStream, outputStream);
			outputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

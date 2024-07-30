package com.Execution2.models;

import org.springframework.web.multipart.MultipartFile;

public class CarouselDto {

	private MultipartFile imageFile;

	public MultipartFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}
	
}

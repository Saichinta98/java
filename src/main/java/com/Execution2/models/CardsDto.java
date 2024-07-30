package com.Execution2.models;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotEmpty;

public class CardsDto {

	 @NotEmpty(message = "The name is  required")
	 private String mname;
	 
	 @NotEmpty(message = "This field is mandatory")
	 private String price;
	 
	 private MultipartFile imageFile;

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public MultipartFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}
	 
}

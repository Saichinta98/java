package com.Execution2.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="carousel")
public class Carousel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String imageFile;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getImageFile() {
		return imageFile;
	}
	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}
	public Carousel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Carousel(int id, String imageFile) {
		super();
		this.id = id;
		this.imageFile = imageFile;
	}
	@Override
	public String toString() {
		return "Carousel [id=" + id + ", imageFile=" + imageFile + "]";
	}
	
}


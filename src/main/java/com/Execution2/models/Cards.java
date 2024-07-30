package com.Execution2.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="cards")
public class Cards {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String mname;
	private String price;
	private String imageFile;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public String getImageFile() {
		return imageFile;
	}
	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}
	public Cards() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Cards(int id, String mname, String price, String imageFile) {
		super();
		this.id = id;
		this.mname = mname;
		this.price = price;
		this.imageFile = imageFile;
	}
	@Override
	public String toString() {
		return "Cards [id=" + id + ", mname=" + mname + ", price=" + price + ", imageFile=" + imageFile + "]";
	}
}
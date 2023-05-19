package com.phone.model;

public class Category {
	private int id;
	private String name;
	private String description;
	private int status;
	private String image;
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Category(int id, String name, String description, int status, String image) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.status = status;
		this.image = image;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
}

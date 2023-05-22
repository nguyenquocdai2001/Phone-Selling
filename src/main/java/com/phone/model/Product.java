package com.phone.model;

public class Product {
	private int id;
	private int category_id;
	private String name;
	private String description;
	private double og_price;
	private double selling_price;
	private String image;
	private int quantity;
	private int status;
	private int trending;
	private String categoryName;
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(int id, int category_id, String name, String description, double og_price, double selling_price,
			String image, int quantity, int status, int trending, String categoryName) {
		super();
		this.id = id;
		this.category_id = category_id;
		this.name = name;
		this.description = description;
		this.og_price = og_price;
		this.selling_price = selling_price;
		this.image = image;
		this.quantity = quantity;
		this.status = status;
		this.trending = trending;
		this.categoryName = categoryName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
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
	public double getOg_price() {
		return og_price;
	}
	public void setOg_price(double og_price) {
		this.og_price = og_price;
	}
	public double getSelling_price() {
		return selling_price;
	}
	public void setSelling_price(double selling_price) {
		this.selling_price = selling_price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getTrending() {
		return trending;
	}
	public void setTrending(int trending) {
		this.trending = trending;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
}

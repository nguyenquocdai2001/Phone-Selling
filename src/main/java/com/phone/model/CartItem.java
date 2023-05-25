package com.phone.model;

public class CartItem {
	private Integer id;
	private int userID;
	private int productID;
	private int qty = 1;
	private String name;
	private double price;
	
	
	public CartItem(Integer id, int userID, int productID, int qty, String name, double price) {
		super();
		this.id = id;
		this.userID = userID;
		this.productID = productID;
		this.qty = qty;
		this.name = name;
		this.price = price;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CartItem() {
		super();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
}

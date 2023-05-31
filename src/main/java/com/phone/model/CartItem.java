package com.phone.model;

public class CartItem {
	private Integer id;
	private int user_id;
	private int prod_id;
	private int qty ;
	private String name;
	private double price;
	
	
	public CartItem(Integer id, int user_id, int prod_id, int qty, String name, double price) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.prod_id = prod_id;
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
		return user_id;
	}
	public void setUserID(int user_id) {
		this.user_id = user_id;
	}
	public int getProductID() {
		return prod_id;
	}
	public void setProductID(int prod_id) {
		this.prod_id = prod_id;
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

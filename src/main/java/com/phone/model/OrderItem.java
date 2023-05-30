package com.phone.model;

import java.time.LocalDateTime;

public class OrderItem {
	private int id;
	private String orderID;
	private int productID;
	private int orderItemQty;
	private double price;
	private String created_at = LocalDateTime.now().toString();
	private LocalDateTime updated_at;
	
	private String nameProduct;
	private String nameCategory;
	



	public OrderItem(int id, String orderID, int productID, int orderItemQty, double price, String created_at, LocalDateTime updated_at){
		super();
		this.id = id;
		this.orderID = orderID;
		this.productID = productID;
		this.orderItemQty = orderItemQty;
		this.price = price;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}

	public OrderItem() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrderID() {
		return orderID;
	}
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public int getOrderItemQty() {
		return orderItemQty;
	}
	public void setOrderItemQty(int orderItemQty) {
		this.orderItemQty = orderItemQty;
	}
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public LocalDateTime getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(LocalDateTime updated_at) {
		this.updated_at = updated_at;
	}

	public String getNameProduct() {
		return nameProduct;
	}

	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}

	public String getNameCategory() {
		return nameCategory;
	}

	public void setNameCategory(String nameCategory) {
		this.nameCategory = nameCategory;
	}
	
	
	
	
	
}

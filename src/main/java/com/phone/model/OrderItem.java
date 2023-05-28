package com.phone.model;

import java.time.LocalDateTime;

public class OrderItem {
	private int id;
	private Order orderID;
	private Product productID;
	private int orderItemQty;
	private double price;
	private LocalDateTime created_at = LocalDateTime.now();
	private LocalDateTime updated_at;
	

	public OrderItem(int id, Order orderID, Product productID, int orderItemQty, double price, LocalDateTime created_at,
			LocalDateTime updated_at) {
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
	public Order getOrderID() {
		return orderID;
	}
	public void setOrderID(Order orderID) {
		this.orderID = orderID;
	}
	public Product getProductID() {
		return productID;
	}
	public void setProductID(Product productID) {
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
	public LocalDateTime getCreated_at() {
		return created_at;
	}
	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}
	public LocalDateTime getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(LocalDateTime updated_at) {
		this.updated_at = updated_at;
	}
	
	
	
}

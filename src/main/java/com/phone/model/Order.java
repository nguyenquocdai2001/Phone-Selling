package com.phone.model;

import java.util.Set;

public class Order {
	private int id;
	private int userID;
	private String name;
	private String email;
	private String phone;
	private String address;
	private String paymentMode;
	private int status;
	private double totalPrice;
	private Set<OrderItem> orderItem;
	
	
	public Order(int id, int userID, String name, String email, String phone, String address, String paymentMode,
			int status, double totalPrice, Set<OrderItem> orderItem) {
		super();
		this.id = id;
		this.userID = userID;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.paymentMode = paymentMode;
		this.status = status;
		this.totalPrice = totalPrice;
		this.orderItem = orderItem;
	}
	
	public Order() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Set<OrderItem> getOrderItem() {
		return orderItem;
	}
	public void setOrderItem(Set<OrderItem> orderItem) {
		this.orderItem = orderItem;
	}
	
	
}

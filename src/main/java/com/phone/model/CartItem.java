package com.phone.model;

public class CartItem {
	private Integer id;
	private int user_id;
	private int prod_id;
	private int qty ;
	private String name;
	private double price;
	
	public CartItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CartItem(Integer id, int user_id, int prod_id, int qty, String name, double price) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.prod_id = prod_id;
		this.qty = qty;
		this.name = name;
		this.price = price;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getProd_id() {
		return prod_id;
	}
	public void setProd_id(int prod_id) {
		this.prod_id = prod_id;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "CartItem [id=" + id + ", user_id=" + user_id + ", prod_id=" + prod_id + ", qty=" + qty + ", name="
				+ name + ", price=" + price + "]";
	}
	
	
	
	
	
}

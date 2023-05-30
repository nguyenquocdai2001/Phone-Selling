package com.phone.model;

public class Rate_prod {
	private int id;
	private int user_id;
	private int prod_id;
	private int stars_rated;
	public Rate_prod() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Rate_prod(int id, int user_id, int prod_id, int stars_rated) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.prod_id = prod_id;
		this.stars_rated = stars_rated;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public int getStars_rated() {
		return stars_rated;
	}
	public void setStars_rated(int stars_rated) {
		this.stars_rated = stars_rated;
	}
	
	
	
}

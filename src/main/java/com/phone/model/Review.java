package com.phone.model;

import java.time.LocalDateTime;

public class Review {
	private int id;
	private int user_id;
	private int prod_id;
	private String user_review;
	private LocalDateTime created_at;
	private String name;
	private int stars_rated;
	private String prod_name;
	public Review() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Review(int id, int user_id, int prod_id, String user_review, LocalDateTime created_at) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.prod_id = prod_id;
		this.user_review = user_review;
		this.created_at = created_at;
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
	public String getUser_review() {
		return user_review;
	}
	public void setUser_review(String user_review) {
		this.user_review = user_review;
	}
	public LocalDateTime getCreated_at() {
		return created_at;
	}
	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStars_rated() {
		return stars_rated;
	}
	public void setStars_rated(int stars_rated) {
		this.stars_rated = stars_rated;
	}
	public String getProd_name() {
		return prod_name;
	}
	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}
	
	
}

package com.phone.DAO;

import java.util.List;

import com.phone.model.Review;

public interface ReviewDAO {
	void addReview(Review reivew);
	void updateReview(Review reivew);
	Review findReview(int id);
	List<Review> getAllReview(int prod_id);
}

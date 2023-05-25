package com.phone.DAO;

import java.util.List;

import com.phone.model.Rate_prod;

public interface RatingDAO {
	void addRating(Rate_prod rating);
	List<Rate_prod> rateList();
	List<Integer> getRateByProductId(int id);
	void updateRatingByUserId(Rate_prod rating);
	Rate_prod findCliendId(int id, int prod_id);
}

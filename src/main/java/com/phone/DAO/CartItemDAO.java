package com.phone.DAO;

import java.util.Collection;

import com.phone.model.CartItem;

public interface CartItemDAO {
	void add(CartItem item);
	
	CartItem update(int prodID, int qty);
	
	void clear();
	
	void remove(int id);
	
	Collection<CartItem> getAllItems();
	
	int getCount();
	
	double getAmount();
	
}

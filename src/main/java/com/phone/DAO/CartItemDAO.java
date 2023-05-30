package com.phone.DAO;

import java.util.Collection;
import java.util.List;

import com.phone.model.CartItem;

public interface CartItemDAO {
	void add(CartItem item);
	
	void updateQty(int prodID, int qty, int user_id);
	
	
	void clear(int idUser);
	
	void clearAfterLogout(int idUser);
	
	void remove(int idProd, int idUser);
	
	List<CartItem> getAllItemsByUserID(int idUser);
	
	int getCount();
	
	double getAmount(int idUser);
	
	void save(CartItem item);
	
	boolean checkItemIsAdded (int userID, int prodID);
	
	int getQtyItemByProdIDAndUserID(int prodID, int userID);
	
}

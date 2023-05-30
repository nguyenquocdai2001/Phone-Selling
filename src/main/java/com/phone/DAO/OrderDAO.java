package com.phone.DAO;

import java.util.List;

import com.phone.model.Order;
import com.phone.model.User;

public interface OrderDAO {
	void saveOrder(Order order);
	List<Order> getAllOrderByUserID(int userID);
	List<Order> getAllOrder();
}

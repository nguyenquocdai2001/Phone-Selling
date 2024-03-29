package com.phone.DAO;

import java.util.Date;
import java.util.List;

import com.phone.model.OrderItem;

public interface OrderItemDAO {
	void saveOrderItem(OrderItem orderItem);	
	List<OrderItem> getAllOrderItemByOrderID(String orderID);
	OrderItem checkOrder(int user_id, int prod_id);
	List<OrderItem> getIncome();
	List<OrderItem> getIncomeByDate(String startdate, String enddate);
}

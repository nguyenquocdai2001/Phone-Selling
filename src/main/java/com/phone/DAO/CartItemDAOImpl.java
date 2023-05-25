package com.phone.DAO;

import java.util.Collection;

import com.phone.model.CartItem;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class CartItemDAOImpl implements CartItemDAO{
	
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("IoC.xml");
	DataSource dataSource = (DataSource) context.getBean("myDataSource");

	Map<Integer, CartItem> maps = new HashMap<>(); //gio hang
	@Override
	public void add(CartItem item) {
		CartItem cartItem = maps.get(item.getProductID());
		if(cartItem == null) {
			maps.put(item.getProductID(), item);
		}
		else {
			cartItem.setQty(cartItem.getQty() + 1);
		}
		
	}

	@Override
	public CartItem update(int prodID, int qty) {
		CartItem cartItem = maps.get(prodID);
		cartItem.setQty(qty);
		
		return cartItem;
	}

	@Override
	public void clear() {
		maps.clear();
		
	}

	@Override
	public void remove(int id) {
		maps.remove(id);
		
	}

	@Override
	public Collection<CartItem> getAllItems() {
		return maps.values();
	}

	@Override
	public int getCount() {
		return maps.values().size();
	}

	@Override
	public double getAmount() {
		return maps.values().stream()
				.mapToDouble(item -> item.getQty() * item.getPrice())
				.sum();
	}

}

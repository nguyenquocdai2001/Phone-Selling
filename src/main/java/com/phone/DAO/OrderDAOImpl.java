package com.phone.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.phone.model.CartItem;
import com.phone.model.Order;

public class OrderDAOImpl implements OrderDAO{
	
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("IoC.xml");
	DataSource dataSource = (DataSource) context.getBean("myDataSource");

	@Override
	public void saveOrder(Order order) {
		try (Connection connection = dataSource.getConnection();
	             PreparedStatement statement = connection.prepareStatement("INSERT INTO orders (id, user_id, name, email, phone, address,"
	             		+ "payment_mode, status, total_price) "
	             		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
				statement.setString(1, order.getId());
	            statement.setInt(2, order.getUserID());
	            statement.setString(3, order.getName());
	            statement.setString(4, order.getEmail());
	            statement.setString(5, order.getPhone());
	            statement.setString(6, order.getAddress());
	            statement.setString(7, order.getPaymentMode());
	            statement.setInt(8, order.getStatus());
	            statement.setDouble(9, order.getTotalPrice());
	            statement.executeUpdate();
	            System.out.println("Order thanh cong");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		
	}

	@Override
	public List<Order> getAllOrderByUserID(int userID) {
		List<Order> orderList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders WHERE user_id = ?")) {
            statement.setInt(1, userID);
            
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
            	Order order = new Order();
            	order.setId(resultSet.getString("id"));
            	order.setUserID(resultSet.getInt("user_id"));
            	order.setName(resultSet.getString("name"));
            	order.setEmail(resultSet.getString("email"));
            	order.setPhone(resultSet.getString("phone"));
            	order.setAddress(resultSet.getString("address"));
            	order.setPaymentMode(resultSet.getString("payment_mode"));
            	order.setStatus(resultSet.getInt("status"));
            	order.setTotalPrice(resultSet.getDouble("total_price"));
            	orderList.add(order);
            	System.out.println("get order thanh cong");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
	}

	@Override
	public List<Order> getAllOrder() {
		List<Order> orderList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders")) {
            
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
            	Order order = new Order();
            	order.setId(resultSet.getString("id"));
            	order.setUserID(resultSet.getInt("user_id"));
            	order.setName(resultSet.getString("name"));
            	order.setEmail(resultSet.getString("email"));
            	order.setPhone(resultSet.getString("phone"));
            	order.setAddress(resultSet.getString("address"));
            	order.setPaymentMode(resultSet.getString("payment_mode"));
            	order.setStatus(resultSet.getInt("status"));
            	order.setTotalPrice(resultSet.getDouble("total_price"));
            	orderList.add(order);
            	
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
	}

	@Override
	public void saveStatus(String orderID) {
		try (Connection connection = dataSource.getConnection();
	             PreparedStatement statement = connection.prepareStatement("UPDATE orders SET status = ? WHERE id = ?")) {
				statement.setInt(1, 1);
	            statement.setString(2, orderID);
	           
	            statement.executeUpdate();
	            System.out.println("Update status thanh cong");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		
	}

}

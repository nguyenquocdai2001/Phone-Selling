package com.phone.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.phone.model.Order;

public class OrderDAOImpl implements OrderDAO{
	
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("IoC.xml");
	DataSource dataSource = (DataSource) context.getBean("myDataSource");

	@Override
	public void saveOrder(Order order) {
		try (Connection connection = dataSource.getConnection();
	             PreparedStatement statement = connection.prepareStatement("INSERT INTO orders (user_id, name, email, phone, address,"
	             		+ "payment_mode, status, total_price) "
	             		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {
	            statement.setInt(1, order.getUserID());
	            statement.setString(2, order.getName());
	            statement.setString(3, order.getEmail());
	            statement.setString(4, order.getPhone());
	            statement.setString(5, order.getAddress());
	            statement.setString(6, order.getPaymentMode());
	            statement.setInt(7, order.getStatus());
	            statement.setDouble(8, order.getTotalPrice());
	            statement.executeUpdate();
	            System.out.println("Order thanh cong");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		
	}

}

package com.phone.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.phone.model.OrderItem;

public class OrderItemDAOImpl implements OrderItemDAO{
	
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("IoC.xml");
	DataSource dataSource = (DataSource) context.getBean("myDataSource");

	@Override
	public void saveOrderItem(OrderItem orderItem) {
		try (Connection connection = dataSource.getConnection();
	             PreparedStatement statement = connection.prepareStatement("INSERT INTO order_details "
	             		+ "(order_id, prod_id, qty, price, created_at,"
	             		+ "updated_at) "
	             		+ "VALUES (?, ?, ?, ?, ?, ?)")) {
	            statement.setObject(1, orderItem.getOrderID());
	            statement.setObject(2, orderItem.getProductID());
	            statement.setInt(3, orderItem.getOrderItemQty());
	            statement.setDouble(4, orderItem.getPrice());
	            statement.setString(5, orderItem.getCreated_at().toString());
	            statement.setString(6, "chua co");
	            statement.executeUpdate();
	            System.out.println("Save order_details thanh cong");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		
	}

}

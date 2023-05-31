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

import com.phone.model.Order;
import com.phone.model.OrderItem;
import com.phone.model.Product;

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
	            statement.setString(1, orderItem.getOrderID());
	            statement.setInt(2, orderItem.getProductID());
	            statement.setInt(3, orderItem.getOrderItemQty());
	            statement.setDouble(4, orderItem.getPrice());
	            statement.setString(5, orderItem.getCreated_at());
	            statement.setString(6, "");
	            statement.executeUpdate();
	            System.out.println("Save order_details thanh cong");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		
	}

	@Override
	public List<OrderItem> getAllOrderItemByOrderID(String orderID) {
		List<OrderItem> orderItemList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT oi.prod_id, oi.order_id, oi.id, oi.qty, oi.price, oi.created_at, "
             		+ "products.name AS nameProduct FROM products "
             		+ "INNER JOIN order_details oi ON products.id = oi.prod_id WHERE oi.order_id = ?")) {
            statement.setString(1, orderID);
            
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
            	OrderItem orderItem = new OrderItem();
            	orderItem.setId(resultSet.getInt("id"));
            	orderItem.setNameProduct(resultSet.getString("nameProduct"));
            	orderItem.setOrderID(resultSet.getString("order_id"));
            	orderItem.setProductID(resultSet.getInt("prod_id"));
            	orderItem.setOrderItemQty(resultSet.getInt("qty"));	
            	orderItem.setPrice(resultSet.getDouble("price"));
            	orderItem.setCreated_at(resultSet.getString("created_at"));
            	orderItemList.add(orderItem);
            	System.out.println("get item order thanh cong");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItemList;
	}

	@Override
	public OrderItem checkOrder(int user_id, int prod_id) {
		OrderItem orderItem = null;
		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT r.*"
						+ "FROM order_details r "
						+ "JOIN orders p ON r.order_id = p.id where p.user_id = ? and r.prod_id= ?")) {
			preparedStatement.setInt(1, user_id);
			preparedStatement.setInt(2, prod_id);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					orderItem = new OrderItem();
					orderItem.setId(resultSet.getInt("id"));            	
	            	orderItem.setOrderID(resultSet.getString("order_id"));
	            	orderItem.setProductID(resultSet.getInt("prod_id"));
	            	orderItem.setOrderItemQty(resultSet.getInt("qty"));	
	            	orderItem.setPrice(resultSet.getDouble("price"));
	            	orderItem.setCreated_at(resultSet.getString("created_at"));          	
	            	System.out.println("get item order thanh cong");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderItem;
	}

}

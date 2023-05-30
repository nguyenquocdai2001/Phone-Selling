package com.phone.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.phone.model.CartItem;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.support.ClassPathXmlApplicationContext;


public class CartItemDAOImpl implements CartItemDAO{
	
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("IoC.xml");
	DataSource dataSource = (DataSource) context.getBean("myDataSource");

	Map<Integer, CartItem> maps = new HashMap<>(); //gio hang
	
	@Override
	public void add(CartItem item) {
		CartItem cartItem = maps.get(item.getProductID());
		if(checkItemIsAdded(item.getUserID(), item.getProductID()) == false) { //product chưa đc add vào giỏ hàng
			maps.put(item.getProductID(), item);
			//sau khi thêm item vào giỏ hàng thì insert item đó vào database luôn
			save(item);
		}
		else {//trả về true là product đã đc add vào giỏ hàng trước đó 
			//cartItem.setQty(cartItem.getQty() + 1);
			updateQty(item.getProductID(), 
					getQtyItemByProdIDAndUserID(item.getProductID(), item.getUserID()) + 1, 
					item.getUserID());
		}
		
	}
	
	@Override
	public void updateQty(int prodID, int qty, int user_id) {
		try (Connection connection = dataSource.getConnection();
	             PreparedStatement statement = connection.prepareStatement("UPDATE cart_items SET qty = ? WHERE prod_id = ? AND user_id =?")) {
				statement.setInt(1, qty);
	            statement.setInt(2, prodID);
	            statement.setInt(3, user_id);
	            statement.executeUpdate();
	            System.out.println("Update qty cart items thanh cong");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		
	}


	@Override
	public void clear(int idUser) {
		try (Connection connection = dataSource.getConnection();
	            PreparedStatement statement = connection.prepareStatement("DELETE FROM cart_items WHERE user_id = ?")) {
	            statement.setInt(1, idUser);
	            statement.executeUpdate();
	            System.out.println("Clear item thanh cong");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		
	}

	@Override
	public void remove(int idProd, int idUser) {
		maps.remove(idProd);
		
		try (Connection connection = dataSource.getConnection();
	            PreparedStatement statement = connection.prepareStatement("DELETE FROM cart_items WHERE prod_id = ? AND user_id = ?")) {
	            statement.setInt(1, idProd);
	            statement.setInt(2, idUser);
	            statement.executeUpdate();
	            System.out.println("Remove item thanh cong");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		
	}

	@Override
	public List<CartItem> getAllItemsByUserID(int idUser) {
		List<CartItem> itemList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM cart_items WHERE user_id = ?")) {
            statement.setInt(1, idUser);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
            	CartItem item = new CartItem();
            	item.setId(resultSet.getInt("id"));
            	item.setUserID(resultSet.getInt("user_id"));
            	item.setProductID(resultSet.getInt("prod_id"));
            	item.setName(resultSet.getString("name"));
            	item.setQty(resultSet.getInt("qty"));
            	item.setPrice(resultSet.getDouble("price"));
            	itemList.add(item);
            	System.out.println("Get item co id " + item.getId() + " thanh cong");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itemList;
	}

	@Override
	public int getCount() {
		return maps.values().size();
	}

	@Override
	public double getAmount(int idUser) {
		double amount = 0;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM cart_items WHERE user_id = ?")) {
            statement.setInt(1, idUser);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
            	CartItem item = new CartItem();
            	item.setQty(resultSet.getInt("qty"));
            	item.setPrice(resultSet.getDouble("price"));
            	
            	amount = amount + (item.getQty() * item.getPrice());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Get amount thanh cong ");
        return amount;
	}

	@Override
	public void save(CartItem item) {
		try (Connection connection = dataSource.getConnection();
	             PreparedStatement statement = connection.prepareStatement("INSERT INTO cart_items (user_id, prod_id, name, qty, price)"
	             		+ "VALUES (?, ?, ?, ?, ?)")) {
	            statement.setInt(1, item.getUserID());
	            statement.setInt(2, item.getProductID());
	            statement.setString(3, item.getName());
	            statement.setInt(4, item.getQty());
	            statement.setDouble(5, item.getPrice());
	            statement.executeUpdate();
	            System.out.println("Save cart items thanh cong");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	}

	@Override
	public void clearAfterLogout(int idUser) {
		System.out.println(maps);
		maps.clear();
		System.out.println(maps);
		System.out.println("Clear item thanh cong khi logout");
	}

	@Override
	public boolean checkItemIsAdded(int userID, int prodID) {

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM cart_items WHERE user_id = ? AND prod_id = ?")) {
            statement.setInt(1, userID);
            statement.setInt(2, prodID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
            	System.out.println("True");
            	return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

		return false;
	}

	@Override
	public int getQtyItemByProdIDAndUserID(int prodID, int userID) {
		try (Connection connection = dataSource.getConnection();
	             PreparedStatement statement = connection.prepareStatement("SELECT qty FROM cart_items WHERE user_id = ? AND prod_id = ?")) {
	            statement.setInt(1, userID);
	            statement.setInt(2, prodID);
	            ResultSet resultSet = statement.executeQuery();
	            
	            int qty = 0;
	            if (resultSet.next()) {
	            	qty = qty + resultSet.getInt("qty");
	            	return qty;
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		return 0;
	}

}

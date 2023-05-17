package com.phone.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;

import com.phone.model.User;

public class UserDAOImpl implements UserDAO{
	
	@Autowired
	@Lazy
    private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	@Lazy
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void saveUser(User user) {
		try (Connection connection = dataSource.getConnection();
	             PreparedStatement statement = connection.prepareStatement("INSERT INTO users (name, email, password, phone, address,"
	             		+ "role, state) "
	             		+ "VALUES (?, ?, ?, ?, ?, ?, ?)")) {
	            statement.setString(1, user.getName());
	            statement.setString(2, user.getEmail());
	            statement.setString(3, user.getPassword());
	            statement.setString(4, user.getPhone());
	            statement.setString(5, user.getAddress());
	            statement.setInt(6, user.getRole());
	            statement.setString(7, user.getState());
	            statement.executeUpdate();
	            System.out.println("Register thanh cong");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		
	}

	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		
	}

}

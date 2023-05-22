package com.phone.DAO;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;

import com.phone.model.User;
import com.phone.validator.Message;

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
	            statement.setString(6, "client");
	            statement.setString(7, "actived");
	            statement.executeUpdate();
	            System.out.println("Register thanh cong");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	}

	@Override
	public User getUserById(int id) {
		User user = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id = ?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setPhone(resultSet.getString("phone"));
                user.setAddress(resultSet.getString("address"));
                user.setRole(resultSet.getString("role"));
                user.setState(resultSet.getString("state"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
	}
	
	@Override
	public Optional<User> getUserByEmail(String email) {
		String sql = "SELECT * FROM users WHERE email = ?";
	    try (Connection conn = dataSource.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setString(1, email);
	        try (ResultSet resultSet = ps.executeQuery()) {
	            if (resultSet.next()) {
	                User user = new User();
	                user.setId(resultSet.getInt("id"));
	                user.setName(resultSet.getString("name"));
	                user.setEmail(resultSet.getString("email"));
	                user.setPassword(resultSet.getString("password"));
	                user.setPhone(resultSet.getString("phone"));
	                user.setAddress(resultSet.getString("address"));
	                user.setRole(resultSet.getString("role"));
	                user.setState(resultSet.getString("state"));
	                return Optional.of(user);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return Optional.empty();
	}

	@Override
	public List<User> getAllUsers() {
		List<User> userList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM users")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setPhone(resultSet.getString("phone"));
                user.setAddress(resultSet.getString("address"));
                user.setRole(resultSet.getString("role"));
                user.setState(resultSet.getString("state"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
	}

	@Override
	public void updateUser(User user) {
		try (Connection connection = dataSource.getConnection();
	             PreparedStatement statement = connection.prepareStatement("UPDATE users SET name = ?, email = ?, "
	             		+ "phone =?, address = ? WHERE id = ?")) {
	            statement.setString(1, user.getName());
	            statement.setString(2, user.getEmail());
	            statement.setString(3, user.getPhone());
	            statement.setString(4, user.getAddress());
	            statement.setInt(5, user.getId());
	            statement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		
	}

	@Override
	public void deleteUser(int id) {
		try (Connection connection = dataSource.getConnection();
	             PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {
	            statement.setInt(1, id);
	            statement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		
	}

	@Override
	public boolean checkRegister(String name, String email, String password, String confirmPassword, String phone,
			String address, Model model) {
		
		Optional<User> optionalUser = getUserByEmail(email);
		
		
		if(email.length() < 1) {
			model.addAttribute("message", new Message("warning", "Please enter email"));
		}
		else if(password.length() < 1) {
			model.addAttribute("message", new Message("warning", "Please enter password"));
		}
		else if(password.length() < 6) {
			model.addAttribute("message", new Message("warning", "Please enter password at least 6 digits"));
		}
		else if(!(password.equals(confirmPassword))) {
			model.addAttribute("message", new Message("warning", "Password is different from Confirm Password"));
		}
		else if(confirmPassword.length() < 1) {
			model.addAttribute("message", new Message("warning", "Please enter confirm password"));
		}
		else if(name.length() < 1) {
			model.addAttribute("message", new Message("warning", "Please enter fullname"));
		}
		else if(phone.length() < 1) {
			model.addAttribute("message", new Message("warning", "Please enter phone number"));
		}
		else if(phone.length() != 10) {
			model.addAttribute("message", new Message("warning", "Wrong type of phone number"));
		}
		else if(address.length() < 1) {
			model.addAttribute("message", new Message("warning", "Please enter address"));
		}
		else if(optionalUser.isPresent()) {
			model.addAttribute("message", new Message("warning", "Email is existed"));
		}
		else {
			model.addAttribute("message", new Message("success", "Register successfully"));
			return true;
		}
		return false;
	}

	@Override
	public boolean checkLogin(String email, String password, Model model) {
		String encryptedpassword = null;  
		try   
        {  
            /* MessageDigest instance for MD5. */  
            MessageDigest m = MessageDigest.getInstance("MD5");  
              
            /* Add plain-text password bytes to digest using MD5 update() method. */  
            m.update(password.getBytes());  
              
            /* Convert the hash value into bytes */   
            byte[] bytes = m.digest();  
              
            /* The bytes array has bytes in decimal form. Converting it into hexadecimal format. */  
            StringBuilder s = new StringBuilder();  
            for(int i=0; i< bytes.length ;i++)  
            {  
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));  
            }  
              
            /* Complete hashed password in hexadecimal format */  
            encryptedpassword = s.toString();  
        }   
        catch (NoSuchAlgorithmException e)   
        {  
            e.printStackTrace();  
        }  
		// TODO Auto-generated method stub
		Optional<User> optionalUser = getUserByEmail(email);
		if(email.length() < 1) {
			model.addAttribute("message", new Message("warning", "Please enter email"));
		}
		else if(password.length() < 1) {
			model.addAttribute("message", new Message("warning", "Please enter password"));
		}
		else if(optionalUser.isPresent() && !(optionalUser.get().getPassword().equals(encryptedpassword))) {
			model.addAttribute("message", new Message("warning", "Email or Password is not correct"));
		}
		else if(!(optionalUser.isPresent())) {
			model.addAttribute("message", new Message("warning", "Email is not exist"));
		}
		if(optionalUser.isPresent() && optionalUser.get().getPassword().equals(encryptedpassword)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean editProfile(String name, String email, String phone, String address, Model model) {
		Optional<User> optionalUser = getUserByEmail(email);
		if(name.length() < 1) {
			model.addAttribute("message", new Message("warning", "Please do not empty fullname"));
		}
		else if(email.length() < 1) {
			model.addAttribute("message", new Message("warning", "Please do not empty email"));
		}
		else if(phone.length() < 1) {
			model.addAttribute("message", new Message("warning", "Please do not empty phone number"));
		}
		else if(phone.length() != 10) {
			model.addAttribute("message", new Message("warning", "Wrong type of phone number"));
		}
		else if(address.length() < 1) {
			model.addAttribute("message", new Message("warning", "Please do not empty address"));
		}
		else {
			model.addAttribute("message", new Message("success", "Edit successfully"));
			return true;
		}
		return false;
	}

	@Override
	public void saveChangePassword(User user) {
		try (Connection connection = dataSource.getConnection();
	             PreparedStatement statement = connection.prepareStatement("UPDATE users SET password = ? WHERE id = ?")) {
	            statement.setString(1, user.getPassword());
	            statement.setInt(2, user.getId());
	            statement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		
	}

	@Override
	public boolean changePassword(String OldPassword, String NewPassword, String confirmNewPassword, String email,
			Model model) {
		String encryptedpassword = null;  
		try   
        {  
            /* MessageDigest instance for MD5. */  
            MessageDigest m = MessageDigest.getInstance("MD5");  
              
            /* Add plain-text password bytes to digest using MD5 update() method. */  
            m.update(OldPassword.getBytes());  
              
            /* Convert the hash value into bytes */   
            byte[] bytes = m.digest();  
              
            /* The bytes array has bytes in decimal form. Converting it into hexadecimal format. */  
            StringBuilder s = new StringBuilder();  
            for(int i=0; i< bytes.length ;i++)  
            {  
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));  
            }  
              
            /* Complete hashed password in hexadecimal format */  
            encryptedpassword = s.toString();  
        }   
        catch (NoSuchAlgorithmException e)   
        {  
            e.printStackTrace();  
        }  

		Optional<User> optionalUser = getUserByEmail(email);
		
		if(email.length() < 1) {
			model.addAttribute("message", new Message("warning", "Please enter email"));
		}
		else if(OldPassword.length() < 1) {
			model.addAttribute("message", new Message("warning", "Please enter old password"));
		}
		else if(NewPassword.length() < 1) {
			model.addAttribute("message", new Message("warning", "Please enter new password"));
		}
		else if(NewPassword.length() < 6) {
			model.addAttribute("message", new Message("warning", "Please enter new password at least 6 digits"));
		}
		else if(confirmNewPassword.length() < 1) {
			model.addAttribute("message", new Message("warning", "Please enter confirm new password"));
		}
		else if(optionalUser.isPresent() && !(optionalUser.get().getEmail().equals(email))) {
			model.addAttribute("message", new Message("warning", "Email is not correct"));
		}
		else if(optionalUser.isPresent() && !(optionalUser.get().getPassword().equals(encryptedpassword))) {
			model.addAttribute("message", new Message("warning", "Email or Old password is not correct"));
		}
		else if(!(NewPassword.equals(confirmNewPassword))) {
			model.addAttribute("message", new Message("warning", "New Password is different from Confirm Password"));
		}
		else if(!(optionalUser.isPresent())) {
			model.addAttribute("message", new Message("warning", "Email is not exist"));
		}
		else {
			model.addAttribute("message", new Message("success", "Change password successfully"));
			return true;
		}
		return false;
	}

}

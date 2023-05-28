package com.phone.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.LocalDate;


import javax.sql.DataSource;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.phone.model.Review;

public class ReviewDAOImpl implements ReviewDAO{
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("IoC.xml");
	DataSource dataSource = (DataSource) context.getBean("myDataSource");
	@Override
	public void addReview(Review review) {
	       try (Connection connection = dataSource.getConnection();
	               PreparedStatement statement = connection.prepareStatement(
	                       "INSERT INTO reviews (user_id, prod_id, user_review, created_at) " +
	                               "VALUES (?, ?, ?, ?)")) {
	              statement.setInt(1, review.getUser_id());
	              statement.setInt(2, review.getProd_id());
	              statement.setString(3, review.getUser_review());
	              statement.setString(4,LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
	              statement.executeUpdate();
	          } catch (SQLException e) {
	              e.printStackTrace();
	          }
		
	}

	@Override
	public void updateReview(Review review) {
		   String query = "UPDATE reviews SET  user_review = ?, created_at = ? WHERE id = ?";
		 try {
	            Connection connection = dataSource.getConnection();
	            PreparedStatement statement = connection.prepareStatement(query);                
	            statement.setString(1, review.getUser_review());
	            statement.setString(2, LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
	            statement.setInt(3, review.getId());
	            statement.executeUpdate();	          
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		
	}


	@Override
	public List<Review> getAllReview(int prod_id) {
		  List<Review> reviews = new ArrayList<>();
		  
	        try (Connection connection = dataSource.getConnection();
	             PreparedStatement statement = connection.prepareStatement("SELECT r.*, u.name, rt.stars_rated"
	            		 +" FROM reviews r JOIN users u"
	            		 +" ON r.user_id = u.id"
	            		 +" join rate_prod rt on r.user_id = rt.user_id and r.prod_id = rt.prod_id"
	            		 +" where r.prod_id = ?")){
	            statement.setInt(1,prod_id);
	             ResultSet resultSet = statement.executeQuery();
	            while (resultSet.next()) {
	            	 String createdAtString = resultSet.getString("created_at");
	            	 LocalDateTime createdAt = LocalDate.parse(createdAtString, DateTimeFormatter.ofPattern("yyyy-MM-dd")).atStartOfDay();
	                Review review = new Review();
	                review.setId(resultSet.getInt("id"));
	                review.setUser_id(resultSet.getInt("user_id"));
	                review.setProd_id(resultSet.getInt("prod_id"));
	                review.setUser_review(resultSet.getString("user_review"));               
	                review.setCreated_at(createdAt);
	                review.setName(resultSet.getString("name"));
	                review.setStars_rated(resultSet.getInt("stars_rated"));
	                reviews.add(review);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return reviews;
	}

	@Override
	public Review findReview(int id) {
		Review review = null;
		String query = "SELECT r.*, p.name FROM reviews r JOIN products p ON r.prod_id = p.id where r.id =? ";
        try {Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                review = new Review();
                review.setId(resultSet.getInt("id"));
                review.setUser_id(resultSet.getInt("user_id"));
                review.setProd_id(resultSet.getInt("prod_id"));
                review.setUser_review(resultSet.getString("user_review"));
                review.setProd_name(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return review;
	}



}

package com.phone.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.phone.model.Rate_prod;

public class RatingDAOImpl implements RatingDAO {

	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("IoC.xml");
	DataSource dataSource = (DataSource) context.getBean("myDataSource");

	@Override
	public void addRating(Rate_prod rating) {

		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO rate_prod (user_id, prod_id, stars_rated) VALUES (?, ?, ?)")) {
			statement.setInt(1, rating.getUser_id());
			statement.setInt(2, rating.getProd_id());
			statement.setInt(3, rating.getStars_rated());
			System.out.println("rating successlly");
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Rate_prod> rateList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateRatingByUserId(Rate_prod rating) {
		String sql = "UPDATE rate_prod SET stars_rated = ? WHERE user_id = ? AND prod_id = ?";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, rating.getUser_id());
			statement.setInt(2, rating.getProd_id());
			statement.setInt(3, rating.getStars_rated());
			System.out.println("update rating successfully");
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Rate_prod findCliendId(int id, int prod_id) {
		String query = "select * from rate_prod where user_id = ? and prod_id = ?";
		Rate_prod rateProd = null;

		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, id);
			statement.setInt(2, prod_id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				rateProd = new Rate_prod();
				rateProd.setId(resultSet.getInt("id"));
				rateProd.setUser_id(resultSet.getInt("user_id"));
				rateProd.setProd_id(resultSet.getInt("prod_id"));
				rateProd.setStars_rated(resultSet.getInt("stars_rated"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rateProd;
	}

	@Override
	public List<Integer> getRateByProductId(int id) {
		List<Integer> list = new ArrayList<>();
		String query = "select stars_rated from rate_prod where  prod_id = ?";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int starsRated = resultSet.getInt("stars_rated");
				list.add(starsRated);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}

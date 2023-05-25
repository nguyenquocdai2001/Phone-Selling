package com.phone.DAO;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.sql.DataSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.web.multipart.MultipartFile;

import com.phone.model.Category;

public class CateDAOImpl implements CateDAO {

	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("IoC.xml");
	 DataSource dataSource = (DataSource) context.getBean("myDataSource");

	@Override
	public void addCategory(Category category) {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"INSERT INTO categories (name, description, status, image) VALUES (?, ?, ?, ?)")) {
			statement.setString(1, category.getName());
			statement.setString(2, category.getDescription());
			statement.setInt(3, category.getStatus());			
			statement.setString(4, category.getImage());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Category> getAllCategory() {
		List<Category> list = new ArrayList<>();
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement("select * from categories");
				ResultSet rs = statement.executeQuery()) {
			while (rs.next()) {
				Category cate = new Category();
				cate.setId(rs.getInt("id"));
				cate.setName(rs.getString("name"));
				cate.setDescription(rs.getString("description"));
				cate.setStatus(rs.getInt("status"));
				cate.setImage(rs.getString("image"));
				list.add(cate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Category getCategoryByID(int id) {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM categories WHERE id = ?")) {
			statement.setInt(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					Category cate = new Category();
					cate.setId(resultSet.getInt("id"));
					cate.setName(resultSet.getString("name"));
					cate.setDescription(resultSet.getString("description"));
					cate.setStatus(resultSet.getInt("status"));
					cate.setImage(resultSet.getString("image"));
					return cate;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateCategory(Category category) {
		String query = "UPDATE categories SET name = ?, description = ?,status = ?, image= ? WHERE id = ?";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, category.getName());
			statement.setString(2, category.getDescription());
			statement.setInt(3, category.getStatus());
			statement.setString(4, category.getImage());
			statement.setInt(5, category.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteCategory(int id) {
		String query = "DELETE FROM categories WHERE id = ?";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void upLoadImage(String nameFile, String dirFile, MultipartFile file) {
		System.out.println(dirFile);
		File fileDir = new File(dirFile);
		if (!fileDir.exists()) {
			fileDir.mkdir();
		}
		try {
			// commonsMultipartFiles.transferTo(new File(fileDir+File.separator+nameFile));
			File savedFile = new File(fileDir + File.separator + nameFile);
			file.transferTo(savedFile);
			System.out.println("Upload file thành công!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Upload file thất bại!");
		}

	}

	@Override
	public List<Category> getLimitCategory() {
		List<Category> list = new ArrayList<>();
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT TOP 4 * FROM categories WHERE status=1");
				ResultSet rs = statement.executeQuery()) {
			while (rs.next()) {
				Category cate = new Category();
				cate.setId(rs.getInt("id"));
				cate.setName(rs.getString("name"));
				cate.setDescription(rs.getString("description"));
				cate.setStatus(rs.getInt("status"));
				cate.setImage(rs.getString("image"));
				list.add(cate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}

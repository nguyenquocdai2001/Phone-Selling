package com.phone.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.phone.model.Product;

public class ProductDAOImpl implements ProductDAO {
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("IoC.xml");
	DataSource dataSource = (DataSource) context.getBean("myDataSource");

	
	@Override
	public void addProduct(Product product) {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO products"
						+ "(cate_id, name, description, og_price, selling_price, image, qty, status, trending)"
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
			preparedStatement.setInt(1, product.getCategory_id());
			preparedStatement.setString(2, product.getName());
			preparedStatement.setString(3, product.getDescription());
			preparedStatement.setDouble(4, product.getOg_price());
			preparedStatement.setDouble(5, product.getSelling_price());
			preparedStatement.setString(6, product.getImage());
			preparedStatement.setInt(7, product.getQuantity());
			preparedStatement.setInt(8, product.getStatus());
			preparedStatement.setInt(9, product.getTrending());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Product getProductById(int id) {
		Product product = new Product();
		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT p.*, c.name AS category_name "
						+ "FROM products p " + "INNER JOIN categories c ON p.cate_id = c.id" + " Where p.id = ?")) {
			preparedStatement.setInt(1, id);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					product = extractProductFromResultSet(resultSet);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}

	@Override
	public void updateProduct(Product product) {

		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement("UPDATE products SET cate_id = ?, name = ?, description = ?, og_price = ?,"
								+ " selling_price = ?, image = ?, qty = ?, status = ?, trending = ? WHERE id = ?")) {
			preparedStatement.setInt(1, product.getCategory_id());
			preparedStatement.setString(2, product.getName());
			preparedStatement.setString(3, product.getDescription());
			preparedStatement.setDouble(4, product.getOg_price());
			preparedStatement.setDouble(5, product.getSelling_price());
			preparedStatement.setString(6, product.getImage());
			preparedStatement.setInt(7, product.getQuantity());
			preparedStatement.setInt(8, product.getStatus());
			preparedStatement.setInt(9, product.getTrending());
			preparedStatement.setInt(10, product.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteProduct(int id) {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement("DELETE FROM products WHERE id = ?")) {

			preparedStatement.setInt(1, id);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Product> getAllProducts() {
		List<Product> products = new ArrayList<>();
		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT p.*, c.name AS category_name "
						+ "FROM products p " + "INNER JOIN categories c ON p.cate_id = c.id ");
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				Product product = extractProductFromResultSet(resultSet);
				products.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return products;
	}

	private Product extractProductFromResultSet(ResultSet resultSet) throws SQLException {
		Product product = new Product();
		product.setId(resultSet.getInt("id"));
		product.setCategory_id(resultSet.getInt("cate_id"));
		product.setName(resultSet.getString("name"));
		product.setDescription(resultSet.getString("description"));
		product.setOg_price(resultSet.getDouble("og_price"));
		product.setSelling_price(resultSet.getDouble("selling_price"));
		product.setImage(resultSet.getString("image"));
		product.setQuantity(resultSet.getInt("qty"));
		product.setStatus(resultSet.getInt("status"));
		product.setTrending(resultSet.getInt("trending"));
		product.setCategoryName(resultSet.getString("category_name"));
		return product;
	}

	@Override
	public List<Product> getTrendingProduct() {
		List<Product> products = new ArrayList<>();
		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT TOP 4 p.*, c.name AS category_name "
		+"FROM products p "
		+"INNER JOIN categories c "
		+"ON p.cate_id = c.id "
		+"where p.trending = 1 and c.status = 1");
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				Product product = extractProductFromResultSet(resultSet);
				products.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}

	@Override
	public void updateProductAfterSelling(Product product) {
		
		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement("UPDATE products SET qty = ? WHERE id = ?")) {
			preparedStatement.setInt(1, product.getQuantity());
			preparedStatement.setInt(2, product.getId());
			preparedStatement.executeUpdate();
			System.out.println("So luong product có id: "+product.getId() +" con lai "+product.getQuantity());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Product> getPhone() {
		List<Product> products = new ArrayList<>();
		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT p.*, c.name AS category_name "
						+ " FROM products p "
						+ "INNER JOIN categories c"
						+ " ON p.cate_id = c.id"
						+ " Where p.cate_id = 21 ");
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				Product product = extractProductFromResultSet(resultSet);
				products.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return products;
	}

	@Override
	public List<Product> getOther() {
		List<Product> products = new ArrayList<>();
		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT p.*, c.name AS category_name "
						+ " FROM products p "
						+ "INNER JOIN categories c"
						+ " ON p.cate_id = c.id"
						+ " Where p.cate_id <> 21"
						+ " and c.status = 1");
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				Product product = extractProductFromResultSet(resultSet);
				products.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return products;
	}

	@Override
	public List<Product> getByCateID(int id) {
		List<Product> products = new ArrayList<>();
		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT p.*, c.name AS category_name "
						+ " FROM products p "
						+ "INNER JOIN categories c ON p.cate_id = c.id"
						+ " Where c.status=1 and p.cate_id = ? ")) {
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Product product = extractProductFromResultSet(resultSet);
				products.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return products;
	}

	@Override
	public List<Product> getAllProductsClient() {
		List<Product> products = new ArrayList<>();
		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT p.*, c.name AS category_name "
						+ "FROM products p " + "INNER JOIN categories c ON p.cate_id = c.id where c.status = 1");
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				Product product = extractProductFromResultSet(resultSet);
				products.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return products;
	}


//	@Override
//	public List<String> search(String keyword) {
//		 List<String> suggestions = new ArrayList<>();
//	        
//	        try (Connection connection = dataSource.getConnection();
//	             PreparedStatement statement = connection.prepareStatement("SELECT name FROM products WHERE name LIKE ?")) {
//	            
//	            statement.setString(1, keyword + "%");
//	            ResultSet resultSet = statement.executeQuery();
//	            
//	            while (resultSet.next()) {
//	                suggestions.add(resultSet.getString("name"));
//	            }
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	        }
//	        
//	        return suggestions;
//	}


}

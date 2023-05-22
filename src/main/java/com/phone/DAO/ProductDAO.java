package com.phone.DAO;

import java.util.*;

import com.phone.model.Product;

public interface ProductDAO {
	   void addProduct(Product product);
	    Product getProductById(int id);
	    void updateProduct(Product product);
	    void deleteProduct(int id);
	    List<Product> getAllProducts();
}

package com.phone.DAO;

import java.util.*;

import com.phone.model.Product;

public interface ProductDAO {
	   	void addProduct(Product product);
	    Product getProductById(int id);
	    void updateProduct(Product product);
	    void deleteProduct(int id);
	    List<Product> getAllProducts();
	    List<Product> getAllProductsClient();
	    List<Product> getTrendingProduct();
	    void updateProductAfterSelling(Product product);
	    List<Product> getPhone();
	    List<Product> getOther();
	    List<Product> getByCateID(int id);
}

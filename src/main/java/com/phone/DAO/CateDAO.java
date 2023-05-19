package com.phone.DAO;

import java.util.List;

import com.phone.model.Category;


public interface CateDAO {
	
	public void addCategory(Category category);
    List<Category> getAllCategory();
    public Category getCategoryByID(int id);
    public void updateCategory(Category category);
    public void deleteCategory(int id);
}

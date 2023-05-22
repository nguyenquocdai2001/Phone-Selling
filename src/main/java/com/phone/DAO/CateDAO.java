package com.phone.DAO;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;


import com.phone.model.Category;


public interface CateDAO {
	
	public void addCategory(Category category);
    List<Category> getAllCategory();
    public Category getCategoryByID(int id);
    public void updateCategory(Category category);
    public void deleteCategory(int id);
    public void upLoadImage(String imageName, String path,MultipartFile  file);
}

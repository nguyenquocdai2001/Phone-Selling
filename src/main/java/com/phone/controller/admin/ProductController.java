package com.phone.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.phone.DAO.CateDAO;
import com.phone.DAO.ProductDAO;
import com.phone.model.Product;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import javax.servlet.ServletContext;

import com.phone.model.Category;

@Controller(value = "productControllerOfAdmin")
public class ProductController {
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("IoC.xml");
	CateDAO category = (CateDAO) context.getBean("CateDAOImpl");
	ProductDAO product = (ProductDAO) context.getBean("ProductDAOImpl");

    @Autowired
	@Lazy
    private ProductDAO productDAO;
    @Autowired
	@Lazy
    private CateDAO cateDAO;
	@Autowired
	private ServletContext servletContext;

    // Mapping cho addProductForm, getProductById, updateProductForm, deleteProduct

    @GetMapping("/product")
    public String showAddProductForm(ModelMap modelMap) {
    	List<Category> listCate = new ArrayList<Category>();
    	listCate = cateDAO.getAllCategory();
        modelMap.addAttribute("product", new Product());
        modelMap.addAttribute("listCate", listCate);
        return "admin/product/addProduct";
    }

    @PostMapping("/addProduct")
    public String addProduct(@ModelAttribute("product") Product product,@RequestParam("file") MultipartFile file) {
    	String nameFile = file.getOriginalFilename();		
		saveImageInProject(file);
		product.setImage(nameFile);
        productDAO.addProduct(product);
        return "redirect:/productandcategory";
    }

    // Mapping cho getProductList, updateProduct, deleteProduct

    @GetMapping("edit-product/{id}")
    public String showUpdateProductForm(@PathVariable("id") int id, ModelMap modelMap) {
        Product product = productDAO.getProductById(id);
        List<Category> listCate = new ArrayList<Category>();
    	listCate = cateDAO.getAllCategory(); 
        modelMap.addAttribute("listCate", listCate);
        modelMap.addAttribute("product", product);
        return "admin/product/editProduct";
    }

    @PostMapping("edit-product/product/{id}/edit")
    public String updateProduct(@ModelAttribute("product") Product product,@RequestParam("file") MultipartFile file) {
    	String nameFile = file.getOriginalFilename();		
		saveImageInProject(file);
		product.setImage(nameFile);
        productDAO.updateProduct(product);
        return "redirect:/productandcategory";
    }

    @GetMapping("delete-product/{id}")
    public String deleteProduct(@PathVariable("id") int id) {
        productDAO.deleteProduct(id);
        return "redirect:/productandcategory";
    }

	private String saveImageInProject(MultipartFile file) {
		// Lưu file vào thư mục trong project

		String uploadDir = servletContext.getRealPath("/template/admin/upload/");
		String fileName = file.getOriginalFilename();
		String filePath = uploadDir + File.separator + fileName;
		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(filePath);
			Files.write(path, bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return filePath;
	}
}

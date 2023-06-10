package com.phone.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import javax.servlet.ServletContext;

import com.phone.DAO.CateDAO;
import com.phone.DAO.ProductDAO;
import com.phone.model.Category;
import com.phone.model.Product;

@Controller(value = "homeControllerOfAdmin")
public class CategoryController {
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("IoC.xml");
	CateDAO cateDAO = (CateDAO) context.getBean("CateDAOImpl");
	ProductDAO productDAO = (ProductDAO) context.getBean("ProductDAOImpl");

	@Autowired
	private ServletContext servletContext;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView homePage() {
		ModelAndView mav = new ModelAndView("admin/home");
		return mav;
	}

	@RequestMapping(value = "/addCategory", method = RequestMethod.GET)
	public ModelAndView adddCategoryPage() {
		ModelAndView mav = new ModelAndView("admin/category/addCategory");
		return mav;
	}

	@PostMapping("addCategory")
	public String addCategory(@ModelAttribute("category") Category cate, @RequestParam("file") MultipartFile file) {
		// Lưu file vào thư mục trong project
		String nameFile = file.getOriginalFilename();
		saveImageInProject(file);
		cate.setImage(nameFile);
		cateDAO.addCategory(cate);
		return "redirect:/productandcategory";
	}

	@RequestMapping(value = "/productandcategory", method = RequestMethod.GET)
	public ModelAndView productPage(ModelMap modelmap) {
		List<Category> listCate = new ArrayList<Category>();
		listCate = cateDAO.getAllCategory();
		//get product
		List<Product> listProduct = new ArrayList<Product>();
		listProduct = productDAO.getAllProducts();
		modelmap.addAttribute("listCate", listCate);
		modelmap.addAttribute("listProduct", listProduct);
		ModelAndView mav = new ModelAndView("admin/Category");
		return mav;
	}

	@GetMapping("/edit-category/{id}")
	public String showEditForm(@PathVariable("id") int idCate, Model model) {
		Category category = cateDAO.getCategoryByID(idCate);
		// Kiểm tra xem sản phẩm có tồn tại không
		if (category == null) {
			return "error";
		}
		// Đưa sản phẩm vào model để hiển thị trong biểu mẫu
		model.addAttribute("category", category);
		// Trả về tên của file view để hiển thị biểu mẫu chỉnh sửa
		return "admin/category/editCategory";
	}

	@PostMapping("edit-category/category/{id}/edit")
	public String editProduct(@ModelAttribute("category") Category category, @RequestParam("file") MultipartFile file) {
		String nameFile = file.getOriginalFilename();
		if(!(nameFile.isEmpty())) {
			saveImageInProject(file);
			category.setImage(nameFile);
		}else
		{
			category.setImage(category.getImage());
		}
		cateDAO.updateCategory(category);
		return "redirect:/productandcategory";
	}

	@GetMapping("delete-category/{id}")
	public String deleteProduct(@PathVariable("id") int id) {
		cateDAO.deleteCategory(id);
		return "redirect:/productandcategory";
	}

	private String saveImageInProject(MultipartFile file) {
		// Lưu file vào thư mục trong project
		String uploadDir = servletContext.getRealPath("/template/admin/upload/");
		String fileName = file.getOriginalFilename();
		String filePath = uploadDir + File.separator + fileName;
		System.out.println(uploadDir);
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

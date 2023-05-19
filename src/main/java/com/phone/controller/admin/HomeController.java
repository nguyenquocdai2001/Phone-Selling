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
import org.springframework.web.servlet.ModelAndView;
import java.util.*;

import com.phone.DAO.CateDAO;
import com.phone.model.Category;

@Controller(value = "homeControllerOfAdmin")
public class HomeController {
	 ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("IoC.xml");
	 
	 CateDAO category = (CateDAO) context.getBean("CateDAOImpl");
	 
	 @Autowired
	 @Lazy
	 private CateDAO cateDAO;
	 
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView homePage() {
		ModelAndView mav = new ModelAndView("admin/home");
		return mav;
	}
	
	@RequestMapping(value = "/addCategory", method = RequestMethod.GET)
	public ModelAndView adddCategoryPage() {
		ModelAndView mav = new ModelAndView("admin/addCategory");
		return mav;
	}
	
	@GetMapping(value="addCategoryPost")
	public String addCategory(@ModelAttribute Category cate) {
		cateDAO.addCategory(cate);
		return "redirect:/";
	}
	
	
	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public ModelAndView productPage(ModelMap modelmap) {
		List<Category> listCate = new ArrayList<Category>();
		listCate = cateDAO.getAllCategory();
		modelmap.addAttribute("listCate",listCate);
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
	    return "admin/editCategory";
	}
	@PostMapping("edit-category/category/{id}/edit")
    public String editProduct(@ModelAttribute("category") Category category) {
        cateDAO.updateCategory(category);
        return "redirect:/product" ;
    }
	 @GetMapping("delete-category/{id}")
	    public String deleteProduct(@PathVariable("id") int id) {
	        cateDAO.deleteCategory(id);
	        return "redirect:/product";
	    }
}

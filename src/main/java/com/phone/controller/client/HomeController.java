package com.phone.controller.client;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.phone.DAO.CateDAO;
import com.phone.DAO.ProductDAO;
import com.phone.model.Product;
import com.phone.model.Category;

@Controller(value = "homeControllerOfClient")
public class HomeController {
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("IoC.xml");
	CateDAO category = (CateDAO) context.getBean("CateDAOImpl");
	ProductDAO product = (ProductDAO) context.getBean("ProductDAOImpl");

    @Autowired
	@Lazy
    private ProductDAO productDAO;
    @Autowired
	@Lazy
    private CateDAO cateDAO;
    
	@RequestMapping(value = "/clienthome", method = RequestMethod.GET)
	public ModelAndView homePage(ModelMap modelmap) {
		ModelAndView mav = new ModelAndView("client/home");
		List<Product> listProduct = new ArrayList<>(); 
		List<Category> listCate = new ArrayList<>();
		listCate = cateDAO.getLimitCategory();
		listProduct = productDAO.getTrendingProduct();
		modelmap.addAttribute("listProduct", listProduct);
		modelmap.addAttribute("listCate", listCate);
		return mav;
	}
	
    @GetMapping("info/{id}")
    public String showUpdateProductForm(@PathVariable("id") int id, ModelMap modelMap) {
        Product product = productDAO.getProductById(id);      
        modelMap.addAttribute("product", product);
        return "client/product/view";
    }
}

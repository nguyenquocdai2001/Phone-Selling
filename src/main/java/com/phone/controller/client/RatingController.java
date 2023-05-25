package com.phone.controller.client;


import java.util.List;

import javax.servlet.http.HttpServletRequest;


import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.phone.DAO.CartItemDAO;
import com.phone.DAO.CateDAO;
import com.phone.DAO.OrderDAO;
import com.phone.DAO.OrderItemDAO;
import com.phone.DAO.ProductDAO;
import com.phone.DAO.RatingDAO;
import com.phone.DAO.UserDAO;
import com.phone.model.Product;
import com.phone.model.Rate_prod;

@Controller(value = "ratingControllerOfClient")
public class RatingController {
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("IoC.xml");
	CateDAO cateDAO = (CateDAO) context.getBean("CateDAOImpl");
	ProductDAO productDAO = (ProductDAO) context.getBean("ProductDAOImpl");
	CartItemDAO cartItemDAO = (CartItemDAO) context.getBean("CartItemDAOImpl");
	OrderDAO orderDAO = (OrderDAO) context.getBean("OrderDAOImpl");
	OrderItemDAO orderItemDAO = (OrderItemDAO) context.getBean("OrderItemDAOImpl");
	UserDAO userDAO = (UserDAO) context.getBean("UserDAOImpl");  
	RatingDAO ratingDAO = (RatingDAO) context.getBean("RatingDAOImpl");


	@GetMapping("info/{id}")
	public String showUpdateProductForm(@PathVariable("id") int id, ModelMap modelMap) {
		Product product = productDAO.getProductById(id);
		List<Integer> list = ratingDAO.getRateByProductId(id);	
		double average = list.stream()
        .mapToInt(Integer::intValue)
        .average()
        .orElse(0);
		modelMap.addAttribute("product", product)
		.addAttribute("rateNum", list.size())
		.addAttribute("rate", average);
		return "client/product/view";
	}

	@PostMapping("/add-rating")
	public String ratingProduct(@ModelAttribute("rate_prod") Rate_prod rating, RedirectAttributes redirectAttributes,
			HttpServletRequest request) {
		int id = rating.getUser_id();
		int prod_id = rating.getProd_id();
		if (ratingDAO.findCliendId(id, prod_id) == null) {
			ratingDAO.addRating(rating);
			redirectAttributes.addFlashAttribute("successMessage", "Lưu thành công");
			// Chuyển hướng ngược trở lại (redirect back) bằng referer
			String referer = request.getHeader("Referer");
			return "redirect:" + referer;
		} else {
			ratingDAO.updateRatingByUserId(rating);
			redirectAttributes.addFlashAttribute("successMessage", "Lưu thành công");
			// Chuyển hướng ngược trở lại (redirect back) bằng referer
			String referer = request.getHeader("Referer");
			return "redirect:" + referer;
		}
	}


}

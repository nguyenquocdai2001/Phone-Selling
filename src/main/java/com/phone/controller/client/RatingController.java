package com.phone.controller.client;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.phone.DAO.ReviewDAO;
import com.phone.DAO.UserDAO;
import com.phone.model.Product;
import com.phone.model.Rate_prod;
import com.phone.model.Review;
import com.phone.model.User;

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
	ReviewDAO reviewDAO = (ReviewDAO) context.getBean("ReviewDAOImpl");

	@GetMapping("info/{id}")
	public String showUpdateProductForm(@PathVariable("id") int id, ModelMap modelMap, HttpSession httpSession) {

		if (httpSession.getAttribute("userSession") != null) {
	    	User loggedInUser = (User) httpSession.getAttribute("userSession");
			if (loggedInUser.getRole().equals("admin")) {
				return "redirect:/home";
			} else {
				
				Product product = productDAO.getProductById(id);
				List<Integer> list = ratingDAO.getRateByProductId(id);
				List<Review> listReview=reviewDAO.getAllReview(id);
				int rateNum = list.size();
				double average = list.stream().mapToInt(Integer::intValue).average().orElse(0);
				modelMap.addAttribute("product", product)
				.addAttribute("rateNum", rateNum)
				.addAttribute("rate", average)
				.addAttribute("listReview", listReview);
				return "client/product/view";
			}
    	}
		Product product = productDAO.getProductById(id);
		List<Integer> list = ratingDAO.getRateByProductId(id);
		List<Review> listReview=reviewDAO.getAllReview(id);
		int rateNum = list.size();
		double average = list.stream().mapToInt(Integer::intValue).average().orElse(0);
		modelMap.addAttribute("product", product)
		.addAttribute("rateNum", rateNum)
		.addAttribute("rate", average)
		.addAttribute("listReview", listReview);
		return "client/product/view";
	}

	@PostMapping("/add-rating")
	public String ratingProduct(@ModelAttribute("rate_prod") Rate_prod rating, RedirectAttributes redirectAttributes		) {		
		int id = rating.getUser_id();
		int prod_id = rating.getProd_id();
		if (ratingDAO.findCliendId(id, prod_id) == null) {
			ratingDAO.addRating(rating);
		} else {
			ratingDAO.updateRatingByUserId(rating);
		}
		redirectAttributes.addFlashAttribute("successMessage", "Lưu thành công");
		String url = "/info/"+prod_id;
		return "redirect:" + url;

	}
///////////////////////	Review///////////////////////////
	@GetMapping("/add-review/{id}/review")
	public String reviewProduct(@PathVariable("id") int id, ModelMap modelMap) {
		Product product = productDAO.getProductById(id);	         
		modelMap.addAttribute("product", product);		
		return "client/product/review";
	}	
	@PostMapping("/add-review")
	public String postReview(@ModelAttribute("reviews") Review review) {
		int prod_id = review.getProd_id(); 
		reviewDAO.addReview(review);	
		String url = "/info/"+prod_id; 
		return "redirect:" + url;
	}
	
	@GetMapping("/edit-review/{id}/userreview")
	public String editReviewProduct(@PathVariable("id") int id, ModelMap modelMap) {
		Review review = reviewDAO.findReview(id);
		modelMap.addAttribute("review", review);		
		return "client/product/edit";
	}
	@PostMapping("/update-review")
	public String updateReview(@ModelAttribute("reviews") Review review) {
		reviewDAO.updateReview(review);	       
		int prod_id = review.getProd_id(); 
		String url = "/info/"+prod_id; 
		return "redirect:" + url;
	}
}

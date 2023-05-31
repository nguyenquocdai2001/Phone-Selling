package com.phone.controller.client;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

import java.text.ParseException;
import java.text.SimpleDateFormat;

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
				List<Review> listReview = reviewDAO.getAllReview(id);
				int rateNum = list.size();
				double average = list.stream().mapToInt(Integer::intValue).average().orElse(0);
				modelMap.addAttribute("product", product).addAttribute("rateNum", rateNum).addAttribute("rate", average)
						.addAttribute("listReview", listReview);
				return "client/product/view";
			}
		}
		Product product = productDAO.getProductById(id);
		List<Integer> list = ratingDAO.getRateByProductId(id);
		List<Review> listReview = reviewDAO.getAllReview(id);
		int rateNum = list.size();
		double average = list.stream().mapToInt(Integer::intValue).average().orElse(0);
		modelMap.addAttribute("product", product).addAttribute("rateNum", rateNum).addAttribute("rate", average)
				.addAttribute("listReview", listReview);
		return "client/product/view";
	}

	@PostMapping("/add-rating")
	public String ratingProduct(@ModelAttribute("rate_prod") Rate_prod rating, RedirectAttributes redirectAttributes)
			throws ParseException {
		int id = rating.getUser_id();
		int prod_id = rating.getProd_id();	
		
		if (orderItemDAO.checkOrder(id, prod_id) != null ) {
			String date = orderItemDAO.checkOrder(id, prod_id).getCreated_at();		
			if(dateCompare(date).equals("Valid")) {
				if (ratingDAO.findCliendId(id, prod_id) == null) {
					ratingDAO.addRating(rating);
				} else {
					ratingDAO.updateRatingByUserId(rating);
				}
				redirectAttributes.addFlashAttribute("status", "Rating success");
				String url = "/info/" + prod_id;
				return "redirect:" + url;
			}else if(dateCompare(date).equals("Outvalid")){
				
				redirectAttributes.addFlashAttribute("status", "Fail to rating, out of range");
				String url = "/info/" + prod_id;
				return "redirect:" + url;
			}
			else 
			{	
				redirectAttributes.addFlashAttribute("status", "Please try it 7 day before rating");
				String url = "/info/" + prod_id;
				return "redirect:" + url;
			}
		
		} else {		
			redirectAttributes.addFlashAttribute("status", "Please try it 7 day before rating");
			String url = "/info/" + prod_id;
			return "redirect:" + url;
		}

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
		String url = "/info/" + prod_id;
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
		String url = "/info/" + prod_id;
		return "redirect:" + url;
	}

	private String dateCompare(String d1) throws ParseException {
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		Date day1 = sdformat.parse(d1);
		Date day2 = sdformat.parse(sdformat.format(date));
		long timeDiff = Math.abs(day2.getTime() - day1.getTime());
	    long daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
		if (daysDiff >= 7 && daysDiff <= 14) {
			System.out.println(daysDiff);
			return "Valid";
		} else if(daysDiff >= 14) {
			return "Outvalid";
		}else {
			return "Unvalid";
		}
	}
	
}

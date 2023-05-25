package com.phone.controller.client;

import java.util.*;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.phone.DAO.CartItemDAO;
import com.phone.DAO.CateDAO;
import com.phone.DAO.OrderDAO;
import com.phone.DAO.OrderItemDAO;
import com.phone.DAO.ProductDAO;
import com.phone.DAO.UserDAO;
import com.phone.model.Product;
import com.phone.model.User;
import com.phone.model.Order;
import com.phone.model.OrderItem;
import com.phone.model.Category;
import com.phone.model.CartItem;

@Controller(value = "homeControllerOfClient")
public class HomeController {
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("IoC.xml");
	CateDAO cateDAO = (CateDAO) context.getBean("CateDAOImpl");
	ProductDAO productDAO = (ProductDAO) context.getBean("ProductDAOImpl");
	CartItemDAO cartItemDAO = (CartItemDAO) context.getBean("CartItemDAOImpl");
	OrderDAO orderDAO = (OrderDAO) context.getBean("OrderDAOImpl");
	OrderItemDAO orderItemDAO = (OrderItemDAO) context.getBean("OrderItemDAOImpl");
	UserDAO userDAO = (UserDAO) context.getBean("UserDAOImpl");
    
    
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
    
  //-----------------------------------------------------------View cart---------------------------------------------------------
  	@GetMapping("views")
  	public String viewCarts(Model model, HttpSession session) {
  		model.addAttribute("CART_ITEMS", cartItemDAO.getAllItems());
  		model.addAttribute("TOTAL_PRICE", cartItemDAO.getAmount());
  		//System.out.println(session.getAttribute("idUser"));
  		
  		//model.addAttribute("helloUser", session.getAttribute("USERNAME"));
  		//model.addAttribute("idUser", userDAO.findByUsername(session.getAttribute("USERNAME").toString()).get());
  		return "./client/cart-items";
  	}
    
  //-----------------------------------------------------------Add cart---------------------------------------------------------	
  	@GetMapping("add/{id}")
  	public String addCart(@PathVariable("id") Integer id, Model model) {
  		Product product = productDAO.getProductById(id);
  		int tmpID = id;
  		if(product != null) {
  			CartItem item = new CartItem();
  			item.setProductID(product.getId());
  			item.setName(product.getName());
  			item.setPrice(product.getSelling_price());
  			item.setQty(1);
  			cartItemDAO.add(item);
  		}
  		
  		return "redirect:/info/" + tmpID;
  	}
  	
  //-----------------------------------------------------------Clear cart---------------------------------------------------------
  	@GetMapping("clear")
  	public String clearCart() {
  		cartItemDAO.clear();
  		return "redirect:/views";
  	}
  	
  //-----------------------------------------------------------Remove cart item---------------------------------------------------------
  		@GetMapping("delete/{id}")
  		public String removeCart(@PathVariable("id") Integer id) {
  			cartItemDAO.remove(id);
  			return "redirect:/views";
  		}
  //-----------------------------------------------------------Update quantity item---------------------------------------------------------
  		@PostMapping("update")
  		public String update(@RequestParam("id") Integer id, @RequestParam("qty") Integer qty) {
  			cartItemDAO.update(id, qty);
  			return "redirect:/views";
  		}
  		
  	//-----------------------------------------------------------Checkout views----------------------------------------------------------------------		
  			@GetMapping("checkout")
  			public String checkOut(HttpSession session, Model model) {
  			int id = (int) session.getAttribute("idUser");
  			model.addAttribute("TOTAL_PRICE", cartItemDAO.getAmount());
  			
  			User foundUser = userDAO.getUserById(id);
  			model.addAttribute("users", foundUser);
  			
  			return "./client/check-out";
  		}
  	//-----------------------------------------------------------Checkout confirm----------------------------------------------------------------------
  			@PostMapping("confirmCheckout")
  			public String confirmCheckout(@RequestParam("email") String email,  
  					@RequestParam("name") String name,
  					@RequestParam("phone") String phone,
  					@RequestParam("address") String address, 
  					@RequestParam("totalPrice") double totalPrice) {
  				Order order = new Order();
  				order.setName(name);
  				order.setEmail(email);
  				order.setPhone(phone);
  				order.setAddress(address);
  				order.setPaymentMode("Paypal");
  				order.setStatus(1);
  				order.setTotalPrice(totalPrice);
  				orderDAO.saveOrder(order);
  				
  				for (CartItem item:cartItemDAO.getAllItems()) {
  					OrderItem orderItem = new OrderItem();
  					orderItem.setProductID(productDAO.getProductById(item.getProductID()));    
  					orderItem.setOrderID(order);
  					orderItem.setPrice(item.getPrice());
  					orderItem.setOrderItemQty(item.getQty());
  					orderItemDAO.saveOrderItem(orderItem);
  				}
  				return "redirect:/clienthome";
  			}
}

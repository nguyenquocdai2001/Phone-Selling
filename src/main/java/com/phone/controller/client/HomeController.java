package com.phone.controller.client;

import java.util.*;

import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
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
import com.phone.model.Review;
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
	public ModelAndView homePage(ModelMap modelmap, HttpSession session) {
		
		if (session.getAttribute("userSession") != null) {
			User loggedInUser = (User) session.getAttribute("userSession");
			if (loggedInUser.getRole().equals("admin")) {
				ModelAndView mav = new ModelAndView("admin/home");
				return mav;
			} else {
				ModelAndView mav = new ModelAndView("client/home");
				List<Product> listProduct = new ArrayList<>(); 
				List<Category> listCate = new ArrayList<>();
				listCate = cateDAO.getLimitCategory();
				listProduct = productDAO.getTrendingProduct();
				modelmap.addAttribute("listProduct", listProduct);
				modelmap.addAttribute("listCate", listCate);
				return mav;
			}
		}
		ModelAndView mav = new ModelAndView("client/home");
		List<Product> listProduct = new ArrayList<>(); 
		List<Category> listCate = new ArrayList<>();
		listCate = cateDAO.getLimitCategory();
		listProduct = productDAO.getTrendingProduct();
		modelmap.addAttribute("listProduct", listProduct);
		modelmap.addAttribute("listCate", listCate);
		return mav;
	}

	

	/*
	 * @GetMapping("info/{id}") public String
	 * showUpdateProductForm(@PathVariable("id") int id, ModelMap modelMap,
	 * HttpSession session) { if (session.getAttribute("userSession") != null) {
	 * User loggedInUser = (User) session.getAttribute("userSession"); if
	 * (loggedInUser.getRole().equals("admin")) { return "redirect:/home"; } else {
	 * Product product = productDAO.getProductById(id);
	 * modelMap.addAttribute("product", product); return "client/product/view"; } }
	 * Product product = productDAO.getProductById(id);
	 * modelMap.addAttribute("product", product); return "client/product/view"; }
	 */
    

	@RequestMapping(value = "/allProduct", method = RequestMethod.GET)
	public ModelAndView productPage(ModelMap modelmap) {
		ModelAndView mav = new ModelAndView("client/product/index");
		List<Product> listProduct = new ArrayList<>(); 
		listProduct = productDAO.getAllProductsClient();
		modelmap.addAttribute("listProduct", listProduct);
		return mav;
	}
    

	@RequestMapping(value = "/allCategory", method = RequestMethod.GET)
	public ModelAndView categoryPage(ModelMap modelmap) {
		ModelAndView mav = new ModelAndView("client/product/allCategory");
		List<Category> listCate = new ArrayList<>(); 
		listCate = cateDAO.getAllCategory();
		modelmap.addAttribute("listCate", listCate);
		return mav;
	}
	
	@RequestMapping(value = "/phone", method = RequestMethod.GET)
	public ModelAndView phonePage(ModelMap modelmap) {
		ModelAndView mav = new ModelAndView("client/product/index");
		List<Product> listProduct = new ArrayList<>(); 
		listProduct = productDAO.getPhone();
		modelmap.addAttribute("listProduct", listProduct);
		return mav;
	}
	@RequestMapping(value = "/accessories", method = RequestMethod.GET)
	public ModelAndView accessoriesPage(ModelMap modelmap) {
		ModelAndView mav = new ModelAndView("client/product/index");
		List<Product> listProduct = new ArrayList<>(); 
		listProduct = productDAO.getOther();
		modelmap.addAttribute("listProduct", listProduct);
		return mav;
	}
	
	@RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
	public ModelAndView getProductByCategoryID(@PathVariable("id") int id, ModelMap modelmap) {
		ModelAndView mav = new ModelAndView("client/product/index");
		List<Product> listProduct = new ArrayList<>(); 
		listProduct = productDAO.getByCateID(id);
		modelmap.addAttribute("listProduct", listProduct);
		return mav;
	}

	/*
	 * <<<<<<< HEAD
	 * 
	 * @RequestMapping(value = "/search", method = RequestMethod.GET)
	 * 
	 * @ResponseBody public List<String> searchAutocomplete(@RequestParam("keyword")
	 * String keyword) { return productDAO.search(keyword); } =======
	 * 
	 * >>>>>>> 3fa268643ded1dc3841fc05e2f59d1e8ac515664
	 */
  //-----------------------------------------------------------View cart---------------------------------------------------------
  	@GetMapping("views")
  	public String viewCarts(Model model, HttpSession session) {
  		
  		if (session.getAttribute("userSession") != null) {
	    	User loggedInUser = (User) session.getAttribute("userSession");
			if (loggedInUser.getRole().equals("admin")) {
				return "redirect:/home";
			} else {
				model.addAttribute("CART_ITEMS", cartItemDAO.getAllItemsByUserID(loggedInUser.getId()));
		  		model.addAttribute("TOTAL_PRICE", cartItemDAO.getAmount(loggedInUser.getId()));
		  		
		  		return "./client/cart-items";
			}
    	}
  		return "./client/users/login";
  	}
    
  //-----------------------------------------------------------Add cart---------------------------------------------------------	
//  	@GetMapping("add/{id}")
//  	public String addCart(@PathVariable("id") Integer id, Model model, HttpSession session) {
//  		
//  		if (session.getAttribute("userSession") != null) {
//	    	User loggedInUser = (User) session.getAttribute("userSession");
//			if (loggedInUser.getRole().equals("admin")) {
//				return "redirect:/home";
//			} else {
//				Product product = productDAO.getProductById(id);
//		  		int tmpID = id;
//		  		if(product != null) {
//		  			CartItem item = new CartItem();
//		  			item.setProd_id(product.getId());
//		  			item.setName(product.getName());
//		  			item.setPrice(product.getSelling_price());
//		  			item.setUser_id(loggedInUser.getId());
//		  			item.setQty(1);
//		  			cartItemDAO.add(item);		  			
//		  		}
//		  		return "redirect:/info/" + tmpID;
//			}
//    	}
//  		return "./client/users/login";
//  		
//  	}
    @PostMapping("/addtoCart")
    public String addtoCart(@ModelAttribute("cart_items") CartItem cartItem,RedirectAttributes redirectAttributes) {
    	int prod_id = cartItem.getProd_id();
    	cartItemDAO.add(cartItem);		     
    	redirectAttributes.addFlashAttribute("status", "Add to cart successfully");
        return "redirect:/info/" + prod_id;
    }
  //-----------------------------------------------------------Clear cart---------------------------------------------------------
  	@GetMapping("clear")
  	public String clearCart(HttpSession session) {
  		
  		if (session.getAttribute("userSession") != null) {
	    	User loggedInUser = (User) session.getAttribute("userSession");
			if (loggedInUser.getRole().equals("admin")) {
				return "redirect:/home";
			} else {
				cartItemDAO.clear(loggedInUser.getId());
		  		return "redirect:/views";
			}
    	}
  		return "./client/users/login";
  		
  	}
  	
  //-----------------------------------------------------------Remove cart item---------------------------------------------------------
  		@GetMapping("delete/{id}")
  		public String removeCart(@PathVariable("id") Integer id, HttpSession session) {
  			
  			if (session.getAttribute("userSession") != null) {
  		    	User loggedInUser = (User) session.getAttribute("userSession");
  				if (loggedInUser.getRole().equals("admin")) {
  					return "redirect:/home";
  				} else {
  					//id là idProd
  					cartItemDAO.remove(id, loggedInUser.getId());
  		  			return "redirect:/views";
  				}
  	    	}
  	  		return "./client/users/login";
  			
  		}
  //-----------------------------------------------------------Update quantity item---------------------------------------------------------
  		@PostMapping("update")
  		public String update(@RequestParam("id") Integer id, @RequestParam("qty") Integer qty, Integer user_id, HttpSession session) {
  			
  			if (session.getAttribute("userSession") != null) {
  		    	User loggedInUser = (User) session.getAttribute("userSession");
  		    	user_id = loggedInUser.getId();
  		    	System.out.println(user_id);
  				if (loggedInUser.getRole().equals("admin")) {
  					return "redirect:/home";
  				} else {
  					cartItemDAO.updateQty(id, qty, user_id);
  		  			return "redirect:/views";
  				}
  	    	}
  	  		return "./client/users/login";
  			
  		}
  		
  	//-----------------------------------------------------------Checkout views----------------------------------------------------------------------		
  			@GetMapping("checkout")
  			public String checkOut(HttpSession session, Model model) {
  				
  				if (session.getAttribute("userSession") != null) {
  	  		    	User loggedInUser = (User) session.getAttribute("userSession");
  	  				if (loggedInUser.getRole().equals("admin")) {
  	  					return "redirect:/home";
  	  				} else {
	  	  				int id = (int) session.getAttribute("idUser");
	  	    			model.addAttribute("TOTAL_PRICE", cartItemDAO.getAmount(loggedInUser.getId()));
	  	    			
	  	    			User foundUser = userDAO.getUserById(id);
	  	    			model.addAttribute("users", foundUser);
	  	    			
	  	    			return "./client/check-out";
  	  				}
  	  	    	}
  	  	  		return "./client/users/login";
  		}
  	//-----------------------------------------------------------Checkout confirm----------------------------------------------------------------------
  			@PostMapping("confirmCheckout")
  			public String confirmCheckout(@RequestParam("email") String email,  
  					@RequestParam("name") String name,
  					@RequestParam("phone") String phone,
  					@RequestParam("address") String address, 
  					@RequestParam("totalPrice") double totalPrice, HttpSession session, RedirectAttributes redirectAttributes) {
  				
  				if (session.getAttribute("userSession") != null) {
  	  		    	User loggedInUser = (User) session.getAttribute("userSession");
  	  				if (loggedInUser.getRole().equals("admin")) {
  	  					return "redirect:/home";
  	  				} else {
  	  					//id của user đang login vào trang
  	    				int idUser = (int) session.getAttribute("idUser");
  	    				
  	    				//tạo unique id cho order
  	    				String id = UUID.randomUUID().toString();
  	    				
  	    				Order order = new Order();
  	    				order.setId(id);
  	    				order.setUserID(idUser);
  	    				order.setName(name);
  	    				order.setEmail(email);
  	    				order.setPhone(phone);
  	    				order.setAddress(address);
  	    				order.setPaymentMode("Cash");
  	    				order.setStatus(0);
  	    				order.setTotalPrice(totalPrice);
  	    				orderDAO.saveOrder(order);
  	    				
  	    				for (CartItem item:cartItemDAO.getAllItemsByUserID(loggedInUser.getId())) {
  	    					OrderItem orderItem = new OrderItem();
  	    					orderItem.setProductID(item.getProd_id());    
  	    					orderItem.setOrderID(id);
  	    					orderItem.setPrice(item.getPrice());
  	    					orderItem.setOrderItemQty(item.getQty());
  	    					orderItemDAO.saveOrderItem(orderItem);
  	    					
  	    					//số lượng product trước khi bán
  	    					int qtyProd = productDAO.getProductById(item.getProd_id()).getQuantity();
  	    					
  	    					//cập nhật lại số lượng product sau khi bán
  	    					Product prod = new Product();
  	    					prod.setQuantity(qtyProd - item.getQty());
  	    					prod.setId(item.getProd_id());
  	    					productDAO.updateProductAfterSelling(prod);
  	    				}
  	    				//clear cart khi đã check out xong
  	    				cartItemDAO.clear(loggedInUser.getId());
  	    				
  	    				redirectAttributes.addFlashAttribute("status", "Check out successfully");
  	    				return "redirect:/checkout";
  	  				}
  	  	    	}
  	  	  		return "./client/users/login";
  			}
  			
  		//-------------------------------------------History view (history customer view)---------------------------------------------------------------------
  			@RequestMapping(value = "/historyUser", method = RequestMethod.GET)
  		    public String getAllHistoryByUserID(ModelMap modelMap, HttpSession session) {
  				
  				if (session.getAttribute("userSession") != null) {
  	  		    	User loggedInUser = (User) session.getAttribute("userSession");
  	  				if (loggedInUser.getRole().equals("admin")) {
  	  					return "redirect:/home";
  	  				} else {  			
	  	    			modelMap.addAttribute("userhistory", session.getAttribute("USERNAME"));
	  				    modelMap.addAttribute("OrderHistory", orderDAO.getAllOrderByUserID(loggedInUser.getId()));
	  				    
	  				    return "./client/history/history"; 
  	  				}
  	  	    	}
  	  	  		return "./client/users/login";
  		    		
  		    }
  		//------------------------------------------Detail history (Detail history customer view)--------------------------------------------------------------------------		
  			@RequestMapping(value = "/detailHistoryUser/{orderID}", method = RequestMethod.GET)
  		    public String getItemReceiptAdmin(ModelMap modelMap, HttpSession session, @PathVariable String orderID) {
  					
  				if (session.getAttribute("userSession") != null) {
  	  		    	User loggedInUser = (User) session.getAttribute("userSession");
  	  				if (loggedInUser.getRole().equals("admin")) {
  	  					return "redirect:/home";
  	  				} else {  			

	  	    			modelMap.addAttribute("OrderHistory", orderDAO.getAllOrderByUserID(loggedInUser.getId()));
	  					modelMap.addAttribute("AllOrderItemHistory", orderItemDAO.getAllOrderItemByOrderID(orderID));
	  				    
	  				    return "./client/history/history-detail"; 
  	  				}
  	  	    	}
  	  	  		return "./client/users/login";
  		    }
  			
  		//---------------------------------------------------Admin view history of each user--------------------------------------------------------------------------		
  			@RequestMapping(value = "/getAllOrderAdmin", method = RequestMethod.GET)
  		    public String getAllOrderAdmin(ModelMap modelMap, HttpSession session) {
  				
  				if (session.getAttribute("userSession") != null) {
  	  		    	User loggedInUser = (User) session.getAttribute("userSession");
  	  				if (loggedInUser.getRole().equals("admin")) {
  	  					
	  	  				modelMap.addAttribute("OrderHistory", orderDAO.getAllOrder());
	  	  				
  	  					return "./admin/history/history";
  	  				} else {  			
	  				    return "redirect:/clienthome"; 
  	  				}
  	  	    	}
  	  	  		return "./client/users/login";
  		    }
  			
  		//------------------------------------------Detail Order (Detail Order Admin view)--------------------------------------------------------------------------		
  			@RequestMapping(value = "/detailOrderUserByAdmin/{orderID}", method = RequestMethod.GET)
  		    public String detailOrderUserByAdmin(ModelMap modelMap, HttpSession session, @PathVariable String orderID) {
  					
  				if (session.getAttribute("userSession") != null) {
  	  		    	User loggedInUser = (User) session.getAttribute("userSession");
  	  				if (loggedInUser.getRole().equals("admin")) {
  	  					
	  	  				modelMap.addAttribute("OrderHistory", orderDAO.getAllOrderByUserID(loggedInUser.getId()));
	  					modelMap.addAttribute("AllOrderItemHistory", orderItemDAO.getAllOrderItemByOrderID(orderID));
	  	  				return "./admin/history/history-detail";
  	  				} else {  			
  	  					return "redirect:/clienthome";
  	  				}
  	  	    	}
  	  	  		return "./client/users/login";
  		    }
  			
  		//--------------------update status order--------------------------------------
  			@RequestMapping(value = "/UpdateStatusOrder/{orderID}", method = RequestMethod.GET)
  		    public String UpdateStatusOrder(ModelMap modelMap, HttpSession session, @PathVariable String orderID,
  		    		RedirectAttributes redirectAttributes) {
  				
  				if (session.getAttribute("userSession") != null) {
  	  		    	User loggedInUser = (User) session.getAttribute("userSession");
  	  				if (loggedInUser.getRole().equals("admin")) {
  	  					
	  	  				orderDAO.saveStatus(orderID);
	  	  				redirectAttributes.addFlashAttribute("status", "Update status successfully");
  	  					return "redirect:/getAllOrderAdmin";
  	  				} else {  			
	  				    return "redirect:/clienthome"; 
  	  				}
  	  	    	}
  	  	  		return "./client/users/login";
  		    }
}

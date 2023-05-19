package com.phone.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.phone.DAO.UserDAO;
import com.phone.model.User;

@Controller
public class UserController {
	
	 ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("IoC.xml");
	
	 UserDAO client = (UserDAO) context.getBean("UserDAOImpl");
	 
	 @Autowired
	 @Lazy
	 private UserDAO userDAO;

	 @GetMapping("/homepage")
	 public String showHomePage() {
		 return "index";
	 }
	 
	 @GetMapping("/addUser")
	 public String showAddUserForm(Model model) {
	     model.addAttribute("user", new User());
	     return "add-user";
	 }
	 
	 @PostMapping("/checkRegister")
	 public String addUser(@RequestParam(required=false, name = "name") String name, 
			 @RequestParam(required=false, name = "password") String password, 
				@RequestParam(required=false, name = "confirmPassword") String confirmPassword, 
				@RequestParam(required=false, name = "email") String email, 
				@RequestParam(required=false, name = "phone") String phone, 
				@RequestParam(required=false, name = "address") String address, Model model) {
		 
		 String encryptedpassword = null;  
			try   
	        {  
	            /* MessageDigest instance for MD5. */  
	            MessageDigest m = MessageDigest.getInstance("MD5");  
	              
	            /* Add plain-text password bytes to digest using MD5 update() method. */  
	            m.update(password.getBytes());  
	              
	            /* Convert the hash value into bytes */   
	            byte[] bytes = m.digest();  
	              
	            /* The bytes array has bytes in decimal form. Converting it into hexadecimal format. */  
	            StringBuilder s = new StringBuilder();  
	            for(int i=0; i< bytes.length ;i++)  
	            {  
	                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));  
	            }  
	              
	            /* Complete hashed password in hexadecimal format */  
	            encryptedpassword = s.toString();  
	        }   
	        catch (NoSuchAlgorithmException e)   
	        {  
	            e.printStackTrace();  
	        }  
			if(userDAO.checkRegister(name, email, password, confirmPassword, phone, address, model)) {
				System.out.println("Register thanh cong");
				//session.setAttribute("NEWUSER", username);
				User u = new User(name, email, encryptedpassword, phone, address);
				userDAO.saveUser(u);
				model.addAttribute("USER", u);
				return "add-user";
			}
			else {
				System.out.println("Register that bai");
			}
			return "add-user";
	 }
}

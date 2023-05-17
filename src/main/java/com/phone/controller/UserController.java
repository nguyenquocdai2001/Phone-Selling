package com.phone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
	 
	 @PostMapping("/saveUser")
	 public String addUser(@ModelAttribute("user") User user) {
	     userDAO.saveUser(user);
	     return "redirect:/";
	 }
}

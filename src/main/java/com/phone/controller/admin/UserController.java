package com.phone.controller.admin;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.phone.DAO.CartItemDAO;
import com.phone.DAO.UserDAO;
import com.phone.model.User;
import com.phone.validator.valid;

@Controller
public class UserController {

	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("IoC.xml");

	UserDAO client = (UserDAO) context.getBean("UserDAOImpl");
	CartItemDAO cartItemDAO = (CartItemDAO) context.getBean("CartItemDAOImpl");

	@Autowired
	@Lazy
	private UserDAO userDAO;

	@RequestMapping(value = "/homepage", method = RequestMethod.GET)
	public ModelAndView homePage() {
		ModelAndView mav = new ModelAndView("admin/home");
		return mav;
	}

	// ----------------------------------Register----------------------------------------------------
	@GetMapping("/addUser")
	public String showAddUserForm(Model model) {
		model.addAttribute("user", new User());
		//model.addAttribute("valid", new valid("no"));
		return "./admin/users/add-user";
	}

	@PostMapping("/checkRegister")
	public String addUser(@RequestParam(required = false, name = "name") String name,
			@RequestParam(required = false, name = "password") String password,
			@RequestParam(required = false, name = "confirmPassword") String confirmPassword,
			@RequestParam(required = false, name = "email") String email,
			@RequestParam(required = false, name = "phone") String phone,
			@RequestParam(required = false, name = "address") String address, Model model) {

		String encryptedpassword = null;
		try {
			/* MessageDigest instance for MD5. */
			MessageDigest m = MessageDigest.getInstance("MD5");

			/* Add plain-text password bytes to digest using MD5 update() method. */
			m.update(password.getBytes());

			/* Convert the hash value into bytes */
			byte[] bytes = m.digest();

			/*
			 * The bytes array has bytes in decimal form. Converting it into hexadecimal
			 * format.
			 */
			StringBuilder s = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}

			/* Complete hashed password in hexadecimal format */
			encryptedpassword = s.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		if (userDAO.checkRegister(name, email, password, confirmPassword, phone, address, model)) {
			System.out.println("Register thanh cong");
			User u = new User(name, email, encryptedpassword, phone, address);
			userDAO.saveUser(u);
			model.addAttribute("USER", u);
			//model.addAttribute("valid", new valid("yes"));
			return "./admin/users/add-user";
		} else {
			model.addAttribute("valid", "is-invalid");
			//luu value khi register failed
			model.addAttribute("check", new User(name, email, password, phone, address));
			System.out.println("Register that bai");
		}
		return "./admin/users/add-user";
	}

	// ----------------------------------Login----------------------------------------------------
	@GetMapping("/login")
	public String showLoginForm(@ModelAttribute("user") User user, HttpSession session) {
		if (session.getAttribute("userSession") != null) {
			User loggedInUser = (User) session.getAttribute("userSession");
			if (loggedInUser.getRole().equals("admin")) {
				return "redirect:/home";
			} else {
				return "./client/index";
			}
		}
		return "./admin/users/login";
	}

	// annotation @ModelAttribute("user") để tạo một đối tượng User trống, nếu
	// session chưa được tạo,
	// thì vẫn có một đối tượng User để truyền vào phương thức
	@PostMapping("/checklogin")
	public String checkLogin(Model model, @RequestParam("email") String email,
			@RequestParam("password") String password, HttpSession session, @ModelAttribute("user") User user) {

		if (userDAO.checkLogin(email, password, model)) {
			// khởi tạo user thông qua id của tài khoản email đã nhập
			User existingUser = userDAO.getUserById(userDAO.getUserByEmail(email).get().getId());

			System.out.println("login thanh cong");
			// set session
			session.setAttribute("userSession", existingUser);
			session.setAttribute("idUser", userDAO.getUserById(userDAO.getUserByEmail(email).get().getId()).getId());
			session.setAttribute("USERNAME", email);
			
			// session này dùng cho hiển thị fullname của người dùng ở header
			session.setAttribute("helloUser", existingUser.getName().toUpperCase());
			
			// session này dùng cho hiển thị các chức năng của người dùng admin hoặc client ở sidebars
			session.setAttribute("userRole", existingUser.getRole());

			if (existingUser.getRole().equals("client")) {
				System.out.println(session.getAttribute("userSession"));
				System.out.println("login voi tu cach khach hang");
				return "redirect:/clienthome";
			} else if (existingUser.getRole().equals("admin")) {
				System.out.println(session.getAttribute("userSession"));
				System.out.println("login voi tu cach admin");
				session.setAttribute("listUser", userDAO.getAllUsers());
				// model.addAttribute("users", userDAO.getAllUsers());
				// System.out.println(userDAO.getAllUsers());
				return "redirect:/home";
			} else {
				return "./admin/users/login";
			}
		} else {
			System.out.println("login that bai");
		}
		return "./admin/users/login";
	}

	// ----------------------------------------------------------------Logout----------------------------------------------------------------------
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		System.out.println("Logout thanh cong");
		return "redirect:/clienthome";
	}

	// ----------------------------------------------------Detail user------------------------------------------------------------
	@GetMapping("/detailUser/{id}")
	public String editUser(@PathVariable("id") int id, Model model, HttpSession session) {
		if (session.getAttribute("userSession") != null) {
			User loggedInUser = (User) session.getAttribute("userSession");
			if (loggedInUser.getRole().equals("admin")) {
				User user = userDAO.getUserById(id);
				model.addAttribute("users", user);
				return "./admin/users/detail-user";
			}
		}
		return "./admin/users/login";
	}

//--------------------------------------------------------Profile----------------------------------------------------------------------	
	@GetMapping("/editProfile/{id}")
	public String editProfle(@PathVariable("id") int id, Model model, HttpSession session) {
		if (session.getAttribute("userSession") != null) {
			User user = userDAO.getUserById(id);
			model.addAttribute("users", user);
			return "./admin/users/edit-profile";
		}
		return "./admin/users/login";
	}

	@PostMapping("/editProfile")
	public String updateUser(@RequestParam(required = false, name = "name") String name,
			@RequestParam(required = false, name = "email") String email,
			@RequestParam(required = false, name = "phone") String phone,
			@RequestParam(required = false, name = "address") String address, @ModelAttribute("user") User user,
			Model model, HttpSession session) {

		if (session.getAttribute("userSession") != null) {
			User foundUser = userDAO.getUserByEmail(user.getEmail()).get();

			// Lưu email vào biến email2
			String email2 = foundUser.getEmail();

			if (userDAO.editProfile(name, email, phone, address, model)) {
				System.out.println("Edit thanh cong");
				foundUser.setName(name);
				foundUser.setEmail(email);
				foundUser.setPhone(phone);
				foundUser.setAddress(address);

				userDAO.updateUser(foundUser);

				
				Optional<User> users = userDAO.getUserByEmail(email2);
				if (users.isPresent()) {
					// truyền lại dữ liệu thông qua "users" vì nó sẽ trả về lại trang 1 lần nữa
					model.addAttribute("users", users.get());
				}
				// session này dùng cho hiển thị fullname của người dùng ở header
				session.setAttribute("helloUser", users.get().getName().toUpperCase());

				return "./admin/users/edit-profile";
			} else {
				System.out.println("Edit that bai");
			}

			// truyền lại dữ liệu thông qua "users" vì nó sẽ trả về lại trang 1 lần nữa
			Optional<User> users = userDAO.getUserByEmail(email2);
			if (users.isPresent()) {
				model.addAttribute("users", users.get());
			}
			return "./admin/users/edit-profile";
		}
		return "./admin/users/login";
	}
	
	//--------------------------------------------------------Profile User view----------------------------------------------------------------------	
		@GetMapping("/editProfileUser/{id}")
		public String editProfleUser(@PathVariable("id") int id, Model model, HttpSession session) {
			if (session.getAttribute("userSession") != null) {
				User user = userDAO.getUserById(id);
				model.addAttribute("users", user);
				return "./client/users/edit-profile";
			}
			return "./client/users/login";
		}

		@PostMapping("/editProfileUser")
		public String editProfleUser(@RequestParam(required = false, name = "name") String name,
				@RequestParam(required = false, name = "email") String email,
				@RequestParam(required = false, name = "phone") String phone,
				@RequestParam(required = false, name = "address") String address, @ModelAttribute("user") User user,
				Model model, HttpSession session) {

			if (session.getAttribute("userSession") != null) {
				User foundUser = userDAO.getUserByEmail(user.getEmail()).get();

				// Lưu email vào biến email2
				String email2 = foundUser.getEmail();

				if (userDAO.editProfile(name, email, phone, address, model)) {
					System.out.println("Edit thanh cong");
					foundUser.setName(name);
					foundUser.setEmail(email);
					foundUser.setPhone(phone);
					foundUser.setAddress(address);

					userDAO.updateUser(foundUser);

					
					Optional<User> users = userDAO.getUserByEmail(email2);
					if (users.isPresent()) {
						// truyền lại dữ liệu thông qua "users" vì nó sẽ trả về lại trang 1 lần nữa
						model.addAttribute("users", users.get());
					}
					// session này dùng cho hiển thị fullname của người dùng ở header
					session.setAttribute("helloUser", users.get().getName().toUpperCase());

					return "./client/users/edit-profile";
				} else {
					System.out.println("Edit that bai");
				}

				// truyền lại dữ liệu thông qua "users" vì nó sẽ trả về lại trang 1 lần nữa
				Optional<User> users = userDAO.getUserByEmail(email2);
				if (users.isPresent()) {
					model.addAttribute("users", users.get());
				}
				return "./client/users/edit-profile";
			}
			return "./client/users/login";
		}


	// ---------------------------------------Change password---------------------------------------------------------------
	@GetMapping("/changePassword/{id}")
	public String changePassword(@PathVariable("id") int id, Model model, HttpSession session) {

		if (session.getAttribute("userSession") != null) {
			return "./admin/users/change-password";
		}
		return "./admin/users/login";
	}

	@PostMapping("/checkChangePassword")
	public String checkChangePassword(@RequestParam(required = false, name = "OldPassword") String OldPassword,
			@RequestParam(required = false, name = "ConfirmNewPassword") String confirmNewPassword,
			@RequestParam(required = false, name = "NewPassword") String NewPassword, @ModelAttribute("user") User user,
			@RequestParam(required = false, name = "email") String email, Model model, HttpSession session) {
		if (session.getAttribute("userSession") != null) {
			
			String NewEncryptedpassword = null;
			try {
				/* MessageDigest instance for MD5. */
				MessageDigest m = MessageDigest.getInstance("MD5");

				/* Add plain-text password bytes to digest using MD5 update() method. */
				m.update(NewPassword.getBytes());

				/* Convert the hash value into bytes */
				byte[] bytes = m.digest();

				/*
				 * The bytes array has bytes in decimal form. Converting it into hexadecimal
				 * format.
				 */
				StringBuilder s = new StringBuilder();
				for (int i = 0; i < bytes.length; i++) {
					s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
				}

				/* Complete hashed password in hexadecimal format */
				NewEncryptedpassword = s.toString();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}

			if (userDAO.changePassword(OldPassword, NewPassword, confirmNewPassword, email, model)) {
				System.out.println("change pass thanh cong");

				User foundUser = userDAO.getUserByEmail(user.getEmail()).get();
				foundUser.setPassword(NewEncryptedpassword);

				userDAO.saveChangePassword(foundUser);

				return "./admin/users/change-password";
			} else {
				System.out.println("change pass that bai");
			}
			return "./admin/users/change-password";
		}
		return "./admin/users/login";

	}
	
	// ---------------------------------------Change password User view---------------------------------------------------------------
		@GetMapping("/changePasswordUser/{id}")
		public String changePasswordUser(@PathVariable("id") int id, Model model, HttpSession session) {

			if (session.getAttribute("userSession") != null) {
				return "./client/users/change-password";
			}
			return "./client/users/login";
		}

		@PostMapping("/checkChangePasswordUser")
		public String checkChangePasswordUser(@RequestParam(required = false, name = "OldPassword") String OldPassword,
				@RequestParam(required = false, name = "ConfirmNewPassword") String confirmNewPassword,
				@RequestParam(required = false, name = "NewPassword") String NewPassword, @ModelAttribute("user") User user,
				@RequestParam(required = false, name = "email") String email, Model model, HttpSession session) {
			if (session.getAttribute("userSession") != null) {
				
				String NewEncryptedpassword = null;
				try {
					/* MessageDigest instance for MD5. */
					MessageDigest m = MessageDigest.getInstance("MD5");

					/* Add plain-text password bytes to digest using MD5 update() method. */
					m.update(NewPassword.getBytes());

					/* Convert the hash value into bytes */
					byte[] bytes = m.digest();

					/*
					 * The bytes array has bytes in decimal form. Converting it into hexadecimal
					 * format.
					 */
					StringBuilder s = new StringBuilder();
					for (int i = 0; i < bytes.length; i++) {
						s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
					}

					/* Complete hashed password in hexadecimal format */
					NewEncryptedpassword = s.toString();
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}

				if (userDAO.changePassword(OldPassword, NewPassword, confirmNewPassword, email, model)) {
					System.out.println("change pass thanh cong");

					User foundUser = userDAO.getUserByEmail(user.getEmail()).get();
					foundUser.setPassword(NewEncryptedpassword);

					userDAO.saveChangePassword(foundUser);

					return "./client/users/change-password";
				} else {
					System.out.println("change pass that bai");
				}
				return "./client/users/change-password";
			}
			return "./client/users/login";

		}
		
		
}

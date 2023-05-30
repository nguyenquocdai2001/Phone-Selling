package com.phone.controller.client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

public class AuthController {
	 @Autowired
	    private HttpServletRequest request;

	    public String checkSession() {
	        // Kiểm tra session
	        HttpSession session = request.getSession(false);
	        if (session != null && session.getAttribute("userSession") != null) {
	            //Session tồn tại
	            String userId = (String) session.getAttribute("userSession");	       
	            return "session_valid";
	        } else {
	            // Session không tồn tại hoặc không có thuộc tính "userId"
	            return "session_invalid";
	        }
	    }
}

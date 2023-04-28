package com.luv2JavaCode.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.luv2JavaCode.Entity.User;
import com.luv2JavaCode.Service.UserService;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String getIndex() {
		return "index";
	}
	@GetMapping("/signin")
	public String login() {
		return "login";
	}
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	@PostMapping("/createUser")
	public String createuser(@ModelAttribute User user, HttpSession session) {

		// System.out.println(user);
		
		boolean f = userService.checkEmail(user.getEmail());
//
		if (f) {
			session.setAttribute("msg", "Email Id alreday exists");
		}

		else {
			User userDtls = userService.createUser(user);
			if (userDtls != null) {
				session.setAttribute("msg", "Register Sucessfully");
			} else {
				session.setAttribute("msg", "Something wrong on server");
			}
		}

		return "redirect:/register";
	}
}

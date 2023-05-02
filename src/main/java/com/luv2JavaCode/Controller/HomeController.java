package com.luv2JavaCode.Controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2JavaCode.Entity.User;
import com.luv2JavaCode.Repository.UserRepository;
import com.luv2JavaCode.Service.UserService;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	private UserRepository userRepo;
	
	@ModelAttribute
	private void userDetails(Model model, Principal p) {
		if(p!=null) {
			String email =  p.getName();
			User user =  userRepo.findByEmail(email);
			model.addAttribute("user", user);
		}
	}

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
	
	@GetMapping("/loadForgotPassword")
	public String loadForgotPassword() {
		return "forgot_password";
	}
	
	@GetMapping("/loadRestPassword")
	public String loadRestPassword() {
		return "rest_password";
	}
	
//	@PostMapping("/forgotPassword")
//	public String forgotPassword(@RequestParam String email, @RequestParam long mobileNumber, HttpSession session) {
//		User user = userRepo.findEmailAndMobileNumber(email, mobileNumber);
//		if(user!=null) {
//			return "reset_password";
//		}else {
//			session.setAttribute("msg", "invalid email & mobile number");
//			return "forgot_password";
//		} 
//		return "";
//	}
	
}

package com.luv2JavaCode.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luv2JavaCode.Entity.User;
import com.luv2JavaCode.Repository.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepo;
	
	@ModelAttribute
	private void userDetails(Model model, Principal p) {
		String email =  p.getName();
		User user =  userRepo.findByEmail(email);
		model.addAttribute("user", user);
	}

	@GetMapping("/")
	public String home() {
		
		return "user/home";
	}
	
}

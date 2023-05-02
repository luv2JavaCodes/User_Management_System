package com.luv2JavaCode.Controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2JavaCode.Entity.User;
import com.luv2JavaCode.Repository.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder; 
	
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
	
	@GetMapping("/changePass")
	public String loadChangePassword() {
		
		return "user/change_password";
	}
	 
	@PostMapping("/updatePass")
	public String changePassword(Principal p, @RequestParam("oldPass") String oldPass, @RequestParam("newPass") String newPass, HttpSession session) {
		
		String email = p.getName(); 
		User loginUser  =   userRepo.findByEmail(email);
		boolean f =  passwordEncoder.matches(oldPass, loginUser.getPassword());
		if(f) {
			loginUser.setPassword(passwordEncoder.encode(newPass));
			User updatePasswordUser = userRepo.save(loginUser);
			if(updatePasswordUser != null) {
				session.setAttribute("msg", "Password Change Successfully");
			}else {
				session.setAttribute("msg", "Something wrong on server" );
			}
		}else {
			session.setAttribute("msg", "old password incorrect." );
		}
		return "redirect:/user/changePass";
	}
	
}

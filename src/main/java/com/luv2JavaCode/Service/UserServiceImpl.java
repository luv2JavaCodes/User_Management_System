package com.luv2JavaCode.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.luv2JavaCode.Entity.User;
import com.luv2JavaCode.Repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncode;
	
	@Override
	public User createUser(User user) {

//		user.setPassword(passwordEncode.encode(user.getPassword()));
//		user.setRole("ROLE_USER");
		user.setPassword(passwordEncode.encode(user.getPassword()));
		user.setRole("ROLE_USER");

		return userRepository.save(user);
	}
	@Override
	public boolean checkEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.existsByEmail(email);
	}

}

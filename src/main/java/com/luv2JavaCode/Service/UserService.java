package com.luv2JavaCode.Service;

import com.luv2JavaCode.Entity.User;

public interface UserService {

	public User createUser(User user);
	
	public boolean checkEmail(String email);
}

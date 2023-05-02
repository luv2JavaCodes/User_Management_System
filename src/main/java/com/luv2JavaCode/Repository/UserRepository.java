package com.luv2JavaCode.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luv2JavaCode.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public boolean existsByEmail(String email);
	
	public User findByEmail(String email);
		
}

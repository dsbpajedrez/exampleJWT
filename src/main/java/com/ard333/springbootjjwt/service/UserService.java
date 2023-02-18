package com.ard333.springbootjjwt.service;

import com.ard333.springbootjjwt.entity.User;
import com.ard333.springbootjjwt.security.model.Role;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author ard333
 */
@Service
public class UserService {
	
	// this is just an example, you can load the user from the database from the repository

	private Map<String, User> data;
	
	@PostConstruct
	public void init(){

		data = new HashMap<>();
		
		//username:passwowrd -> user:user
		data.put("user", new User(null,"user", "cBrlgyL2GI2GINuLUUwgojITuIufFycpLG4490dhGtY=",  Arrays.asList(Role.ROLE_USER)));

		//username:passwowrd -> admin:admin
		data.put("admin", new User(null,"admin", "dQNjUIMorJb8Ubj2+wVGYp6eAeYkdekqAcnYp+aRq5w=", Arrays.asList(Role.ROLE_ADMIN)));
	}
	
	public User findByUsername(String username) {
		if (data.containsKey(username)) {
			return data.get(username);
		} else {
			return null;
		}
	}
	
}

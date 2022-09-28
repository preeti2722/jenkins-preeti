package com.sony.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sony.model.User;

@Service
public class UserManager {

	private Map<String ,User> users;
	
	public UserManager() {
		users =new HashMap<>();
		User user;
		
		user =new User("Preeti","Manakattimath","preeti@xmpl.com","Bangalore");
		users.put(user.getId(), user);
		
		user =new User("John","Doe","john@xmpl.com","Texas");
		users.put(user.getId(), user);
		
		user =new User("Jane","Doe","jane@xmpl.com","Dubai");
		users.put(user.getId(), user);
	}
	public Iterable<User> getAllUsers(){
		return this.users.values();
	}
	
	public User getUserById(String id) {
		return this.users.get(id);
		
	}
}

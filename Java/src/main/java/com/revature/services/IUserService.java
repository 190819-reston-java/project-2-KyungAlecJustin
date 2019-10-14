package com.revature.services;

import java.util.List;

import com.revature.model.User;

public interface IUserService {
	

	public List<User> findAll(); //List all users
	
	public User findOne(String username); //Find by username
	
	User getUserLogin(String username, String password); //For login verification
	
	User createUser(User user); //Register a user

}

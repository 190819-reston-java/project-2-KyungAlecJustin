package com.revature.repositories;

import java.util.List;

import com.revature.model.User;

public interface IUserDAO {

	public List<User> findAll();
	
	public User findOne(int userId);
	
	public User create(User u);
	
	public User getLogin(String username, String userpwd);
	

	
	
}

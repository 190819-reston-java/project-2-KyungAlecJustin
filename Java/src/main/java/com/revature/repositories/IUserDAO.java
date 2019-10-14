package com.revature.repositories;

import java.util.List;

import com.revature.model.User;

public interface IUserDAO {

	public List<User> findAll();
	
	public User findOne(int userId);
	
	User getUserLogin(String username, String password);
	
	User createUser(User user);

	
}

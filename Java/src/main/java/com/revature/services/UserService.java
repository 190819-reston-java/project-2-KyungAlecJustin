package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.model.User;
import com.revature.repositories.UserDAO;

@Service
public class UserService {
	
	UserDAO userDao = new UserDAO();


	public List<User> findAll() {
		List<User> users = userDao.findAll();
		return users;
	}
	
	public User createUser(User u) {
		userDao.create(u);
		return u;
	}
	
	public User getLogin() {
		return null;
	}
	
	

}

package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.revature.model.User;
import com.revature.repositories.IUserDAO;
import com.revature.repositories.UserDAO;

@Service
public class UserService {
	
	@Autowired
	private IUserDAO userDao;


	public List<User> findAll() {
		List<User> users = userDao.findAll();
		return users;
	}
	
	public User createUser(User u) {
		userDao.create(u);
		return u;
	}
	
	public boolean getLogin(String username, String userpwd) {
		return userDao.getLogin(username, userpwd);
		
	}
	
	

}

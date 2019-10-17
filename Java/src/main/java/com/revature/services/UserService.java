package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.revature.model.User;
import com.revature.repositories.UserDAO;

@Service
public class UserService {
	
	// this needs to be managed by Spring...but how?
	UserDAO userDao;

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

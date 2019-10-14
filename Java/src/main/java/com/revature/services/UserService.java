package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.model.User;

@Service
public class UserService {

	
	private List<User> users = new ArrayList<User>();


	public List<User> findAll() {
		return users;
	}
	
	public User getLogin() {
		return null;
	}
	
	

}

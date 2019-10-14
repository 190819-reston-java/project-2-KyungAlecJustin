package com.revature.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.model.User;
import com.revature.repositories.IUserDAO;

@Service
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class UserService implements IUserService {
	
	private IUserDAO iUserDAO;

	@Override
	public List<User> findAll() {
		return iUserDAO.findAll();
	}

	@Override
	public User findOne(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserLogin(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User createUser(User user) {
		return iUserDAO.createUser(user);
	}

}

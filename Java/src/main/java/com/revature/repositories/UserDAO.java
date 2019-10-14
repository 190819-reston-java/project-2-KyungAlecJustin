package com.revature.repositories;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.model.User;


@Repository
public class UserDAO implements IUserDAO {

	@Autowired
	private SessionFactory sf;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<User> findAll() {
		Session s = sf.getCurrentSession();
		
		@SuppressWarnings("unchecked")
		List<User> users = s.createCriteria(User.class).list();
		
		return users;
		
	}

	@Override
	@Transactional
	public User findOne(int userId) {
		Session s = sf.getCurrentSession();
		
		User u = (User) s.get(User.class, userId);
		
		return u;
	}
	
	

	@Override
	public User getUserLogin(String username, String password) {
		return null;
	
	}

	@Override
	public User createUser(User user) {
		
		Session s = sf.getCurrentSession();
		
		User newUser = (User) s.save(User.class);
		
		return newUser;
	}

}

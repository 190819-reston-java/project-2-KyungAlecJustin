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
	SessionFactory sf;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<User> findAll() {
		System.out.println("reaching findAll in USER DAO");
		//Session s = sf.getCurrentSession();
		Session os = sf.openSession(); 
		os.beginTransaction();
		
		System.out.println(os);
		
		@SuppressWarnings("unchecked")
		List<User> users = os.createCriteria(User.class).list();
		
		System.out.println(users);
		
		os.getTransaction().commit();
		os.close();
		
		return users;
		
	}

	@Override
	@Transactional
	public User findOne(int userId) {
		Session s = sf.getCurrentSession();
		System.out.println(s);
		
		User u = (User) s.get(User.class, userId);
		
		return u;
	}
	

	@Override
	@Transactional
	public User create(User u) {
		//Session s = sf.getCurrentSession();
		System.out.println("reaching create in UserDAO");
		Session os = sf.openSession();
		System.out.println(os);

		os.beginTransaction();
		
		System.out.println(u);
		os.save(u);
		os.getTransaction().commit();
		os.close();
		return u;
	}

	@Override
	@Transactional
	public boolean getLogin(String username, String userpwd) {
		System.out.println("reaching getLogin in UserDAO");
		//Session s = sf.getCurrentSession();
		Session os = sf.openSession(); 
		os.beginTransaction();
		System.out.println(os);
		
		@SuppressWarnings("unchecked")
		List<User> users = os.createCriteria(User.class).list();
//		User u = (User) os.createCriteria(User.class);
		
		for(User u : users) {
			if (u.getUsername() == username) {
				if (u.getUsrpwd() == userpwd) {
					return true;
				}
			}
		}
		return false;
		
	}
	
	

}

package com.revature;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.model.User;
import com.revature.repositories.IUserDAO;

public class Driver {
	
	private static Configuration configuration;
	private static StandardServiceRegistryBuilder ssrb;
	private static SessionFactory sf;
	private static Session session;

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");

		IUserDAO userDAO = (IUserDAO) ac.getBean("userDAO");
		
		List<User> users = userDAO.findAll();
		
		for(User u : users) {
			System.out.println(u);
		}
	}

	private static void addUser() {
		
		
		// required session headers
		session = sf.openSession();
		session.beginTransaction();

		// testing adding new record
		User newUser = new User();
		newUser.setUsername("SpringTest");
		newUser.setUsrpwd("password");
		newUser.setEmail("spring@email.com");
		newUser.setFirstName("Spring");
		newUser.setLastName("Test");
		
		

		session.save(newUser);
		System.out.println(newUser);

		// required session footer
		session.getTransaction().commit();
		session.close();
		
		
	}
	

}

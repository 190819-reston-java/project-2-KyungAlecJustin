package com.revature;

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
		
//		// boilerplate for Hibernate
//		configuration = new Configuration().configure();
//		ssrb = new StandardServiceRegistryBuilder()
//				.applySettings(configuration.getProperties());
//		sf = configuration.buildSessionFactory(ssrb.build());
		// boilerplate for hibernate
		
		IUserDAO userDAO = (IUserDAO) ac.getBean("userDAO");
		
		addUser();
		
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

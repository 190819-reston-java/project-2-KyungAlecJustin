package com.revature;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.model.User;
import com.revature.model.Watchlist;
import com.revature.repositories.IUserDAO;
import com.revature.repositories.IWatchlistDAO;

public class Driver {
	
	private static Configuration configuration;
	private static StandardServiceRegistryBuilder ssrb;
	private static SessionFactory sf;
	//private static Session session;

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		configuration = new Configuration().configure();
		

		IUserDAO userDAO = (IUserDAO) ac.getBean("userDAO");
		
		IWatchlistDAO watchlistDao = (IWatchlistDAO) ac.getBean("watchlistDAO");
		
		
		System.out.println(watchlistDao.getUserWatchlists(5));
		
		System.out.println(watchlistDao.findAllWatchlist());
		

	}
	

}

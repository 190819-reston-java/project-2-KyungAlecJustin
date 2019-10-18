package com.revature.repositories;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.model.Movie;
import com.revature.model.Watchlist;
import com.revature.session.UserSession;

@Repository
public class WatchlistDAO implements IWatchlistDAO {
	
	@Autowired
	private SessionFactory sf;
	
	@Autowired
	private UserSession sessionUser;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Watchlist> findAllWatchlist() {
		//Session s = sf.getCurrentSession();
		
		Session os = sf.openSession();
		os.beginTransaction();
		
		@SuppressWarnings("unchecked")
		List<Watchlist> watchlists = os.createCriteria(Watchlist.class).list();
		
		System.out.println(os.isOpen());
		os.getTransaction().commit();
		os.close();
		System.out.println(os.isOpen());

		return watchlists;
		
	}

	@Override
	@Transactional
	public Watchlist findWatchlist(String watchlistName) {
		Session s = sf.getCurrentSession(); 
		
		Watchlist w = (Watchlist) s.createQuery("");
		
		return w;
	}


	@Override
	@Transactional
	public Watchlist create(Watchlist newWatchlist) {
		System.out.println("Reaching DAO watchlist");
		System.out.println("USER SESSION IN DAO: " + this.sessionUser.getCurrentUser());
		//Session s = sf.getCurrentSession();
		//System.out.println("Session in DAO " + os);
		Session os = sf.openSession();

		os.beginTransaction();
		
		System.out.println(newWatchlist);
		os.save(newWatchlist);
		os.getTransaction().commit();
		os.close();
		
		return newWatchlist;
	}

	@Override
	@Transactional
	public Watchlist getUserWatchlist(int ownerId) {
		
		//Remove later
		System.out.println("USER SESSION IN DAO GET WATCHLIST ID: " + this.sessionUser.getCurrentUser());
		System.out.println("USER ID FROM SESSION: " + this.sessionUser.getCurrentUser().getUserId());
		
		Session os = sf.openSession();
		
		os.beginTransaction();
		
		Watchlist userWatchlist = (Watchlist) os.createQuery("FROM watchlist WHERE user_id = :sessionId");
		int userSessionId = this.sessionUser.getCurrentUser().getUserId();
		
		System.out.println(os.isOpen());
		
		((Query) userWatchlist).setInteger("sessionId", userSessionId);
		
		

		
		//Remove later
		System.out.println(userWatchlist);
		
		os.getTransaction().commit();
		
		
		return userWatchlist;

	}

	@Override
	public Watchlist addMovieToWatchlist(Watchlist w, Movie m) {
		// TODO Auto-generated method stub
		return null;
	}

}

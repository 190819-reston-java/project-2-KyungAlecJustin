package com.revature.repositories;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
		//Will use for sessions:		
		//Session s = sf.getCurrentSession();
		
		Session os = sf.openSession();
		os.beginTransaction();
		
		@SuppressWarnings("unchecked")
		List<Watchlist> watchlists = os.createCriteria(Watchlist.class).list();
		
		os.getTransaction().commit();
		os.close();
		
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
		System.out.println(os);

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
		// TODO Auto-generated method stub
		return null;
	}

}

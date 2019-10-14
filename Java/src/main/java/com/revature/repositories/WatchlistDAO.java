package com.revature.repositories;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.model.Watchlist;

@Repository
public class WatchlistDAO implements IWatchlistDAO {
	
	@Autowired
	private SessionFactory sf;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Watchlist> findAll() {
		Session s = sf.getCurrentSession();
		
		@SuppressWarnings("unchecked")
		List<Watchlist> watchlists = s.createCriteria(Watchlist.class).list();
		
		return watchlists;
		
	}

	@Override
	@Transactional
	public Watchlist findOne(int watchlistId) {
		Session s = sf.getCurrentSession();
		
		Watchlist w = (Watchlist) s.get(Watchlist.class, watchlistId);
		
		return w;
	}

}

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
		//Session s = sf.getCurrentSession();
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

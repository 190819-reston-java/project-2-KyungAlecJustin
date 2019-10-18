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
import com.revature.model.User;
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
	public List<Movie> findWatchlist(String watchlistName) {
		System.out.println("reaching finWatchlist in WatchlistDAODAO");

		Session os = sf.openSession(); 
		os.beginTransaction();
		System.out.println(os);
		
		@SuppressWarnings("unchecked")
		List<Watchlist> watchlists = os.createCriteria(Watchlist.class).list();
		
		for(Watchlist w : watchlists) {
			if (w.getWatchlistName().equals(watchlistName)) {
				
				int wid = w.getWatchlistId();
				
				@SuppressWarnings("unchecked")
				List<Movie> movies = (List<Movie>) os.createQuery("FROM movies WHERE watchlist_id = :wid");
				
				((Query) movies).setInteger("wid", wid);
				
				return movies;
			}
		}
		
		return null;
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

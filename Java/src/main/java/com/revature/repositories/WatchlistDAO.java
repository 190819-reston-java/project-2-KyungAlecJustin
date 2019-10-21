package com.revature.repositories;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.model.Movie;
import com.revature.model.User;
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
	public List<Movie> findWatchlist(String watchlistName) {
		System.out.println("reaching finWatchlist in WatchlistDAODAO");

		Session os = sf.openSession(); 
		os.beginTransaction();
		System.out.println(os);
		
		@SuppressWarnings("unchecked")
		List<Watchlist> watchlists = os.createCriteria(Watchlist.class).list();
		//@SuppressWarnings("unchecked")
		//List<Movie> wlbn = os.createCriteria(Watchlist.class).add(Restrictions.eq("watchlistName", watchlistName)).list();
		
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
	public Watchlist addMovieToWatchlist(Watchlist w, Movie m) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Watchlist> getUserWatchlists(Integer ownerId){
		System.out.println("Reached WL DAO for USWL");
		Session s = sf.openSession();	
		System.out.println(s.isOpen());
		System.out.println(ownerId);
		
		return (List<Watchlist>) s.createCriteria(Watchlist.class).add(Restrictions.eq("watchlistOwner.userId", ownerId)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Movie> getMoviesInWatchlist(Integer watchlistId) {
		System.out.println("Reached WL DAO for Movies in WL");
		Session s = sf.openSession();	
		System.out.println(s.isOpen());
		System.out.println(watchlistId);
		
		return (List<Movie>) s.createCriteria(Movie.class).add(Restrictions.eq("watchlist.watchlistId", watchlistId)).list();
	}

}

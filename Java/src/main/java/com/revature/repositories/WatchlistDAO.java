package com.revature.repositories;

import java.util.ArrayList;
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
		
		Session os = sf.openSession();
		os.beginTransaction();
		
		@SuppressWarnings("unchecked")
		List<Watchlist> watchlists = os.createCriteria(Watchlist.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		
		System.out.println(os.isOpen());
		os.getTransaction().commit();
		os.close();
		System.out.println(os.isOpen());

		return watchlists;
		
	}


	@Override
	@Transactional
	public List<Movie> findWatchlist(String watchlistName) {
		System.out.println("==WatchlistDAO: reaching the method");

		Session os = sf.openSession(); 
		os.beginTransaction();
		System.out.println("==WatchlistDao open seesion: " + os);
		
		@SuppressWarnings("unchecked")
		List<Watchlist> watchlists = os.createCriteria(Watchlist.class).list();
		System.out.println("==WatchlistDAO: reaching after list and before forloop");

		//List<Watchlist> watchlists = os.createCriteria(Watchlist.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		
		//@SuppressWarnings("unchecked")
		//List<Movie> wlbn = os.createCriteria(Watchlist.class).add(Restrictions.eq("watchlistName", watchlistName)).list();
		
		System.out.println("==WatchlistDAO: input from frontend: " + watchlistName);
		
//		for (Watchlist w : watchlists) {
//			System.out.println("*****: " + watchlistName);
//			System.out.println("====WatchlistDAO: printling list of names of watchlist: " + w.getWatchlistName());
//			System.out.println("==== TO STRING: " + w.getWatchlistName().toString());
//			System.out.println("====WatchlistDAO: printing list of id: " + w.getWatchlistId());
//		}
		
//		for (Watchlist w : watchlists) {
//			if ((w.getWatchlistName().toString()) == (watchlistName.toString())) {
//				System.out.println("FUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUCK");
//			}
//			System.out.println("*****: " + watchlistName);
//			System.out.println("====WatchlistDAO: printling list of names of watchlist: " + w.getWatchlistName());
//			System.out.println("==== TO STRING: " + w.getWatchlistName().toString());
//			System.out.println("====WatchlistDAO: printing list of id: " + w.getWatchlistId());
//			System.out.println("no");
//		}

		
		for(Watchlist w : watchlists) {
			if (w.getWatchlistName().equals(watchlistName)) {
				System.out.println("=================can you come here?==================");
				
				int wid = w.getWatchlistId();
				
//				@SuppressWarnings("unchecked")
//				List<Movie> movies = (List<Movie>) os.createQuery("FROM Movie WHERE watchlistId = :wid");
//				
//				((Query) movies).setInteger("wid", wid);
				
				@SuppressWarnings("unchecked")
				List<Movie> movies = os.createCriteria(Movie.class).list();
				
				for (Movie m : movies) {
					if (m.getWatchlist() == wid) {
						return movies;
					}
				}
				
//				return movies;
			}
		}
		System.out.println("==WatchlistDAO: not completing for loop");
		return null;
	}


	@Override
	@Transactional
	public Watchlist create(Watchlist newWatchlist) {
		System.out.println("User Session in Session Dao: " + this.sessionUser.getCurrentUser().getUsername());
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
		Session s = sf.openSession();	
		System.out.println(s.isOpen());
		System.out.println(ownerId);
		
		return (List<Watchlist>) s.createCriteria(Watchlist.class).add(Restrictions.eq("watchlistOwner.userId", ownerId)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Movie> getMoviesInWatchlist(Integer watchlistId) {
		Session s = sf.openSession();	
		System.out.println(s.isOpen());
		System.out.println(watchlistId);
		
		return (List<Movie>) s.createCriteria(Movie.class).add(Restrictions.eq("watchlist.watchlistId", watchlistId)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}


	@Override
	public List<Watchlist> findWatchlist(String watchlistName) {
		// TODO Auto-generated method stub
		return null;
	}

}

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
		//Session s = sf.getCurrentSession();
		
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

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Watchlist> findWatchlist(String watchlistName) {
		System.out.println("reaching finWatchlist in WatchlistDAO");

		Session os = sf.openSession(); 
		os.beginTransaction();
		System.out.println(os.isConnected());
		
		System.out.println("REACHING BEFORE HIBERNATE CRITERIA");
		
		//@SuppressWarnings("unchecked")
		List<Watchlist> watchlists = os.createCriteria(Watchlist.class).add(Restrictions.eq("watchlistName", watchlistName)).list();
		//List<Watchlist> watchlists = os.createQuery("FROM Watchlist WHERE watchlistName = :wln").list();
		//Query watchlists = (Query) os.createQuery("FROM Watchlist WHERE watchlistName = :wln").setString("wln", watchlistName).list();

		
		System.out.println(watchlists);
		
		os.getTransaction().commit();
		//os.close();
		System.out.println(os.isOpen());
		System.out.println("reaching end");
		return watchlists;
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
		
		return (List<Watchlist>) s.createCriteria(Watchlist.class).add(Restrictions.eq("watchlistOwner.userId", ownerId)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Movie> getMoviesInWatchlist(Integer watchlistId) {
		System.out.println("Reached WL DAO for Movies in WL");
		Session s = sf.openSession();	
		System.out.println(s.isOpen());
		System.out.println(watchlistId);
		
		return (List<Movie>) s.createCriteria(Movie.class).add(Restrictions.eq("watchlist.watchlistId", watchlistId)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}

}

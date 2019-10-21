package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.Movie;
import com.revature.model.User;
import com.revature.model.Watchlist;
import com.revature.repositories.IWatchlistDAO;

@Service
public class WatchlistService {
	
	@Autowired
	private IWatchlistDAO watchlistDao;
	
	@Autowired 
	private UserService userService;
	
	public List<Watchlist> getAllWatchlist(){
		List<Watchlist> watchlists = watchlistDao.findAllWatchlist();
		return watchlists;
		
	}

	
//	public List<Movie> getWatchlistByName(String watchlistName) {
//		System.out.println("==WatchlistService: reaching method");
//		List<Movie> movies = watchlistDao.findWatchlist(watchlistName);
//		System.out.println("==WatchlistService: after list of movies " + movies);
//		return movies;
//	}

	public List<Watchlist> getWatchlistByUser(Integer ownerId) {
		return watchlistDao.getUserWatchlists(ownerId);

	}
	
	public List<Movie> getMoviesInWatchlist(Integer watchlistId) {
		return watchlistDao.getMoviesInWatchlist(watchlistId);

	}
	
	public Watchlist createWatchlist(Watchlist w) {
		watchlistDao.create(w);
		return w;
	}
	
	
	

}

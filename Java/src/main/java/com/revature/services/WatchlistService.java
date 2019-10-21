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
//	
//	public List<Movie> getWatchlistByName(String watchlistName) {
//		List<Movie> movies = watchlistDao.findWatchlist(watchlistName);
//		return movies;
//	}
	
//	
	public List<Watchlist> getWatchlistByName(String watchlistName) {
		List<Watchlist> watchlists = watchlistDao.findWatchlist(watchlistName);
		return watchlists;
	}

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

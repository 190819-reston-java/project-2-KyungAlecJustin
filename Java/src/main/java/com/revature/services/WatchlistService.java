package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.Watchlist;
import com.revature.repositories.IWatchlistDAO;

@Service
public class WatchlistService {
	
	@Autowired
	private IWatchlistDAO watchlistDao;
	
	public List<Watchlist> getAllWatchlist(){
		List<Watchlist> watchlists = watchlistDao.findAllWatchlist();
		return watchlists;
		
	}
	
	public Watchlist getWatchlistByName(String watchlistName) {
		Watchlist watchlist = watchlistDao.findWatchlist(watchlistName);
		return watchlist;
	}
	
	public Watchlist getWatchlistByUser(int ownerId) {
		Watchlist userWatchlist = watchlistDao.getUserWatchlist(ownerId);
		return userWatchlist;
	}
	
	public Watchlist createWatchlist(Watchlist w) {
		watchlistDao.create(w);
		return w;
	}
	
	
	

}

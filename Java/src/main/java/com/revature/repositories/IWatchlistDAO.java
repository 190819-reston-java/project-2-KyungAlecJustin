package com.revature.repositories;

import java.util.List;

import com.revature.model.Watchlist;

public interface IWatchlistDAO {
	
	public List<Watchlist> findAll();
	
	public Watchlist findOne(int watchlistId);

}

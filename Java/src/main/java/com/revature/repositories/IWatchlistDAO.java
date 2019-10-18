package com.revature.repositories;

import java.util.List;

import com.revature.model.Movie;
import com.revature.model.Watchlist;

public interface IWatchlistDAO {
	
	public List<Watchlist> findAllWatchlist();
	
	public Watchlist findWatchlist(String watchlistName);

	public Watchlist create(Watchlist w);
	
	public Watchlist getUserWatchlist(int ownerId);
	
	public Watchlist addMovieToWatchlist(Watchlist w, Movie m);
}

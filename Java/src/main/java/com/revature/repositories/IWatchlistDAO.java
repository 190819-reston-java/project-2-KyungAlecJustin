package com.revature.repositories;

import java.util.List;

import com.revature.model.Movie;
import com.revature.model.User;
import com.revature.model.Watchlist;

public interface IWatchlistDAO {
	
	public List<Watchlist> findAllWatchlist();
	
	public List<Movie> findWatchlist(String watchlistName);

	public Watchlist create(Watchlist w);
	
	public List<Watchlist> getUserWatchlists(int ownerId);
	
	public Watchlist addMovieToWatchlist(Watchlist w, Movie m);
}

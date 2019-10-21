package com.revature.repositories;

import java.util.ArrayList;
import java.util.List;

import com.revature.model.Movie;
import com.revature.model.User;
import com.revature.model.Watchlist;

public interface IWatchlistDAO {
	
	public List<Watchlist> findAllWatchlist();
	
	//public List<Movie> findWatchlist(String watchlistName);
	
	public List<Watchlist> findWatchlist(String watchlistName);


	public Watchlist create(Watchlist w);
	
	public List<Watchlist> getUserWatchlists(Integer ownerId);
	//public List<Watchlist> getUserWatchlists(User user);
	//public List<Watchlist> getUserWatchlists();
	
	public List<Movie> getMoviesInWatchlist(Integer watchlistId);

	
	public Watchlist addMovieToWatchlist(Watchlist w, Movie m);
}

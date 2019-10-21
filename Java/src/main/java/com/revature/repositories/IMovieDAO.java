package com.revature.repositories;

import java.util.List;

import com.revature.model.Movie;

public interface IMovieDAO {
	
	public List<Movie> listAll();
			
	public Movie addMovie(Movie m);

	Movie addMovieToDatabase(Movie newMovie);

}

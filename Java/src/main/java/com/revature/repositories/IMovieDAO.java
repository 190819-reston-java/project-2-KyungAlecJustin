package com.revature.repositories;

import java.util.List;

import com.revature.model.Movie;

public interface IMovieDAO {
	
	public List<Movie> findAll();
	
	public Movie findOne(int movieId);

}

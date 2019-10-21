package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.Movie;
import com.revature.repositories.IMovieDAO;

@Service
public class MovieService {
	
	@Autowired
	private IMovieDAO movieDao;
	
	public List<Movie> listAllMovies(){
		List<Movie> movies = movieDao.listAll();
		return movies;
		
	}
	
	public Movie addMovie(Movie newMovie) {
		movieDao.addMovie(newMovie);
		return newMovie;
	}
	
	public Movie addMovieToWatchlist(Movie newMovie) {
		movieDao.addMovieToDatabase(newMovie);
		return newMovie;
	}
	

}

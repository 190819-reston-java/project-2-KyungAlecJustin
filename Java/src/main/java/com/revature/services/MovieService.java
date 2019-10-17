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
		System.out.println("reaching movies in MovieService");
		List<Movie> movies = movieDao.listAll();
		return movies;
		
	}
	
	public Movie addMovie(Movie m) {
		movieDao.addMovie(m);
		return m;
	}
	

}

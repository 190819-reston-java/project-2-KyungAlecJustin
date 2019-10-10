package com.revature.repositories;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.model.Movie;

@Repository
public class MovieDAO implements IMovieDAO {
	
	@Autowired
	private SessionFactory sf;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Movie> findAll() {
		Session s = sf.getCurrentSession();
		
		@SuppressWarnings("unchecked")
		List<Movie> movies = s.createCriteria(Movie.class).list();
		
		return movies;
	}

	@Override
	@Transactional
	public Movie findOne(int movieId) {
		Session s = sf.getCurrentSession();
		
		Movie m = (Movie) s.get(Movie.class, movieId);
		
		return m;
	}

}

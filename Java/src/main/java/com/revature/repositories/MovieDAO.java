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
	public List<Movie> listAll() {
		//Session s = sf.getCurrentSession();
		
		Session os = sf.openSession();
		os.beginTransaction();
				
		@SuppressWarnings("unchecked")
		List<Movie> movies = os.createCriteria(Movie.class).list();	
				
		os.getTransaction().commit();
		os.close();
		
		return movies;
	}

	@Override
	@Transactional
	public Movie addMovie(Movie newMovie) {
		//Session s = sf.getCurrentSession();
		Session os = sf.openSession();

		os.beginTransaction();
		
		System.out.println(newMovie);
		os.save(newMovie);
		os.getTransaction().commit();
		os.close();
		
		return newMovie;
	}
	
	@Override
	@Transactional
	public Movie addMovieToDatabase(Movie newMovie) {
		//Session s = sf.getCurrentSession();
		Session os = sf.openSession();

		os.beginTransaction();
		
		System.out.println(newMovie);
		os.save(newMovie);
		os.getTransaction().commit();
		os.close();
		
		return newMovie;
	}



}

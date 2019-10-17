package com.revature.repositories;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.model.Movie;
import com.revature.model.User;

@Repository
public class MovieDAO implements IMovieDAO {
	
	@Autowired
	private SessionFactory sf;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Movie> listAll() {
		System.out.println("reaching movies in MovieDAO");

		Session os = sf.openSession();
		os.beginTransaction();
		
		System.out.println(os);
		
		@SuppressWarnings("unchecked")
		List<Movie> movies = os.createCriteria(Movie.class).list();	
		
		System.out.println(movies);
		
		os.getTransaction().commit();
		os.close();
		
		return movies;
	}

	@Override
	@Transactional
	public Movie addMovie(Movie m) {
		//Session s = sf.getCurrentSession();
		System.out.println("reached addMovie() in MovieDAO");
		Session os = sf.openSession();
		System.out.println(os);

		os.beginTransaction();
		
		System.out.println(m);
		os.save(m);
		os.getTransaction().commit();
		os.close();
		return m;
	}



}

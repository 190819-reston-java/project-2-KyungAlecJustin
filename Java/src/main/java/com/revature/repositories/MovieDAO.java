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

@Repository
public class MovieDAO implements IMovieDAO {
	
	@Autowired
	private SessionFactory sf;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Movie> listAll() {
		Session s = sf.getCurrentSession();
		
		@SuppressWarnings("unchecked")
		List<Movie> movies = s.createCriteria(Movie.class).list();
		
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

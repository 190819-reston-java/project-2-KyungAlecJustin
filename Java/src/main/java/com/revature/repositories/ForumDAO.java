package com.revature.repositories;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.model.Forum;
import com.revature.model.Movie;

@Repository
public class ForumDAO implements IForumDAO {
	
	@Autowired
	private SessionFactory sf;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Forum> getAllForums() {
		//Session s = sf.getCurrentSession();

		Session os = sf.openSession();
		os.beginTransaction();
		
		System.out.println(os);
		
		@SuppressWarnings("unchecked")
		List<Forum> forums = os.createCriteria(Forum.class).list();	
		
		System.out.println(forums);
		
		os.getTransaction().commit();
		os.close();
		
		return forums;
	}
	

	@Override
	@Transactional
	public Forum createForum(Forum f) {
		//Session s = sf.getCurrentSession();
		Session os = sf.openSession();
		System.out.println(os);

		os.beginTransaction();
		
		System.out.println(f);
		os.save(f);
		os.getTransaction().commit();
		os.close();
		return f;
	}
	
	

}

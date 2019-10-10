package com.revature.repositories;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.model.Forum;

@Repository
public class ForumDAO implements IForumDAO {
	
	@Autowired
	private SessionFactory sf;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Forum> findAll() {
		Session s = sf.getCurrentSession();
		
		@SuppressWarnings("unchecked")
		List<Forum> forums = s.createCriteria(Forum.class).list();
		
		return forums;
	}

	@Override
	@Transactional
	public Forum findOne(int forumId) {
		Session s = sf.getCurrentSession();
		
		Forum f = (Forum) s.get(Forum.class, forumId);
		
		return f;
	}

}

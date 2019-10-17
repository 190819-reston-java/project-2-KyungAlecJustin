package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.Forum;
import com.revature.repositories.IForumDAO;

@Service
public class ForumService {
	
	
	@Autowired
	private IForumDAO forumDao;
	
	public List<Forum> getAllForums(){
		List<Forum> forums = forumDao.getAllForums();
		return forums;
		
	}
	
	public Forum createForum(Forum f) {
		forumDao.createForum(f);
		return f;
	}

}

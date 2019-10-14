package com.revature.repositories;

import java.util.List;

import com.revature.model.Forum;

public interface IForumDAO {
	
	public List<Forum> findAll();
	
	public Forum findOne(int forumId);
	

}

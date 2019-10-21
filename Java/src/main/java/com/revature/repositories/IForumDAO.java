package com.revature.repositories;

import java.util.List;

import com.revature.model.Forum;
import com.revature.model.Movie;

public interface IForumDAO {
	
	public List<Forum> getAllForums();
	public Forum createForum(Forum f);
	

}

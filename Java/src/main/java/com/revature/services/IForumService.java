package com.revature.services;

import com.revature.model.Forum;

public interface IForumService {
	
	//Find forums by username
	
	//List all forums by user (writer ID)
	Forum findOne(int writerId);

}

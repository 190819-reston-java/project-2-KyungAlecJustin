package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Forum;
import com.revature.services.ForumService;
import com.revature.session.UserSession;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ForumController {

	@Autowired
	private ForumService forumService;
	
	@Autowired
	private UserSession sessionUser;

	@RequestMapping(value = "/forums", method = RequestMethod.GET)
	public List<Forum> getAllForums() {
		return forumService.getAllForums();
	}

	@PutMapping("/createforum")
	public String upsert(@RequestBody Forum f) throws JsonProcessingException {
		System.out.println("IN THE UPSERT");
		System.out.println(this.sessionUser.getCurrentUser());
		Forum newMessage = new Forum(
				f.getForumId(),
				f.getMessage(),
				this.sessionUser.getCurrentUser()
				);
		this.forumService.createForum(newMessage);
		ObjectMapper om = new ObjectMapper();
		String response = om.writeValueAsString(new ResponseEntity<String>(HttpStatus.ACCEPTED));
		return response;
	}

}

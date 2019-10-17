package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.Forum;
import com.revature.services.ForumService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ForumController {

	@Autowired
	private ForumService forumService;

	@RequestMapping(value = "/forums", method = RequestMethod.GET)
	public List<Forum> getAllForums() {

		return forumService.getAllForums();
	}

	@PutMapping("/createforum")
	public ResponseEntity<Forum> upsert(@RequestBody Forum f) {
		System.out.println("create reached");
		Forum response = forumService.createForum(f);

		return ResponseEntity.ok(response);
	}

}

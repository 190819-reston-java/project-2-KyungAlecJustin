package com.revature.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.User;
import com.revature.services.UserService;
import com.revature.session.UserSession;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserSession sessionUser;
	
	@RequestMapping(value = "/users", method=RequestMethod.GET)
	public List<User> findAll(){		
		return userService.findAll();
	}

	@PutMapping("/signup")
	public String upsert(@RequestBody User userCreate) throws JsonProcessingException{
		ObjectMapper om = new ObjectMapper();
		String response;
		
		try {
			this.userService.createUser(userCreate);
			response = om.writeValueAsString(new ResponseEntity<String>(HttpStatus.CREATED));
			return response;
		} catch (RuntimeException e) {
			response = om.writeValueAsString(new ResponseEntity<String>(HttpStatus.FORBIDDEN));
			return response;
		}
	}
	
	@PostMapping("/login")
	public String loginVerify(@RequestBody User userCreds) throws ServletException, IOException{
		ObjectMapper om = new ObjectMapper();
		String response;
		List<User> userList = this.userService.findAll();
		User currentUser = null;
		
		if (this.userService.getLogin(userCreds.getUsername(), userCreds.getUsrpwd())) {
			for (User u : userList) {
				if (u.getUsername().equals(userCreds.getUsername())) {
					if (u.getUsrpwd().equals(userCreds.getUsrpwd())) {
						currentUser = u;
					}
				}
			}
			
			this.sessionUser.setCurrentUser(currentUser);
			response = om.writeValueAsString(new ResponseEntity<String>(HttpStatus.ACCEPTED));
			return response;
		} else {
			response = om.writeValueAsString(new ResponseEntity<String>(HttpStatus.UNAUTHORIZED));
			return response;
		}
	}
	
	@GetMapping("/getSessionUser")
	public User getSessionUser() {
		return this.sessionUser.getCurrentUser();
	}
	
	@GetMapping("/logout")
	public void logout() {
		this.sessionUser.setCurrentUser(null);
	}
}

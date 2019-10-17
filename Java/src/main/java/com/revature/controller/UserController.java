package com.revature.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.User;
import com.revature.repositories.UserDAO;
import com.revature.services.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/users", method=RequestMethod.GET)
	public List<User> findAll(){		
		return userService.findAll();
	}

	@PutMapping("/createuser")
	public ResponseEntity<User> upsert(@RequestBody User userCreate){
		User response = userService.createUser(userCreate);
		
		System.out.println("STATUSCODEEE: " + ResponseEntity.ok(response));
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/login")
	public String loginVerify(@RequestBody User userCreds) throws ServletException, IOException{
		ObjectMapper om = new ObjectMapper();
		String response;
		if (userService.getLogin(userCreds.getUsername(), userCreds.getUsrpwd())) {
			response = om.writeValueAsString(new ResponseEntity<String>(HttpStatus.ACCEPTED));
			System.out.println(response);
			return response;
		} else {
			response = om.writeValueAsString(new ResponseEntity<String>(HttpStatus.UNAUTHORIZED));
			System.out.println(response);
			return response;
		}
		

	}
	

}

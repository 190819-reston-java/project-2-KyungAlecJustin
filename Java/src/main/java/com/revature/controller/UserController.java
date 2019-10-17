package com.revature.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	@PutMapping("/create")
	public ResponseEntity<User> upsert(@RequestBody User u){
		User response = userService.createUser(u);
		
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/login")
	public ResponseEntity<User> loginVerify(@RequestBody String loginform, HttpServletRequest req, HttpServletResponse resp){
		User signedInUser = userService.getLogin();
		userService.getLogin();
		System.out.println("Login reached in Spring");
		return null;
	}
	

}

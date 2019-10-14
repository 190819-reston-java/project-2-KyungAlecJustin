package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.User;
import com.revature.services.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/register")
	public User register(@RequestBody User user) {
		return userService.createUser(user);
	}
	
	@PostMapping("/register")
	@ResponseBody
	public ResponseEntity<User> upsert(@RequestBody User newUser){
		User response = userService.createUser(newUser);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/login")
	@ResponseBody
	public ResponseEntity<User> loginVerify(){
		System.out.println("login initialized");
		return null;
		
	}
	
	
	

}

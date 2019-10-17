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
	
	//temporary leaving it like this 
//	@PostMapping("/login")
//	public ResponseEntity<User> loginVerify(HttpServletRequest req, HttpServletResponse resp) throws IOException{
//		System.out.println("Login reached in Spring:UserController");
//		HttpSession session = req.getSession();
//		
//		String username = req.getParameter("username");
//		String userpwd = req.getParameter("password");
//		
//		if (userService.getLogin(username, userpwd)) {
//			//to where?
//			resp.sendRedirect("/main");
//		} else {
//			//to where?
//			resp.sendRedirect("/index");
//		}
//		
//		
//		return null;
//	}
	
//	@PostMapping("/login")
//	public void loginVerify(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
//		System.out.println("Login reached in Spring:UserController");
//		HttpSession session = req.getSession();
//		
//		String username = req.getParameter("username");
//		String userpwd = req.getParameter("password");
//		
//		System.out.println("user input is :" +username + userpwd);
//		
//		if (userService.getLogin(username, userpwd)) {
//			System.out.println("login succ");
//			//to where?
//			resp.sendRedirect("/main");
//		} else {
//			System.out.println("login fail");
//			//to where?
//			resp.sendRedirect("/index");
//		}
//	}
	
	@PostMapping("/login")

	public void loginVerify(@RequestBody String userCreds) throws ServletException, IOException{
		System.out.println("Login reached in Spring:UserController");
//		System.out.println(userCreds ["username"]);
//		HttpSession session = req.getSession();
//		
//		userCreds.
//		
//		System.out.println("user input is :" +username + userpwd);
//		
//		if (userService.getLogin(username, userpwd)) {
//			System.out.println("login succ");
//			//to where?
//			resp.sendRedirect("/main");
//		} else {
//			System.out.println("login fail");
//			//to where?
//			resp.sendRedirect("/index");
//		}

	}
	

}

package com.revature.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
	
	@RequestMapping(value="/hello", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> sayHello(){
		System.out.println("reaching hello in Test Controller");
		return new ResponseEntity<String>("Hello!", HttpStatus.OK);
	}
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String home() {
		
		return "home";
	}

}

package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.Movie;
import com.revature.services.MovieService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	
	@GetMapping("/movies")
	public List<Movie> listAllMovies(){
		System.out.println("reaching movies in MovieController");
		return movieService.listAllMovies();
	}
	
	@PutMapping("/addmovie")
	public ResponseEntity<Movie> upsert(@RequestBody Movie m){
		System.out.println("reached addmovie in MovieController");
		Movie response = movieService.addMovie(m);
		
		return ResponseEntity.ok(response);
	}
	
	

}

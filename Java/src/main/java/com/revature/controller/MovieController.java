package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.Movie;
import com.revature.services.MovieService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	
	@GetMapping("/movies")
	public List<Movie> listAllMovies(){
		return movieService.listAllMovies();
	}
	
	@PutMapping("/addmovie")
	public ResponseEntity<Movie> upsert(@RequestBody Movie m){
		System.out.println("Reaching addmovie");
		System.out.println(m);
		Movie response = movieService.addMovie(m);
		System.out.println(response);
		
		ResponseEntity.ok().body(null);
		
		return ResponseEntity.ok(response);
	}
	
	

}

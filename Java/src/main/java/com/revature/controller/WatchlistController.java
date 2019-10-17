package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.Watchlist;
import com.revature.services.WatchlistService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class WatchlistController {
	
	@Autowired
	private WatchlistService watchlistService;
	
	@GetMapping("/watchlists")
	public List<Watchlist> listAllWatchlists(){
		return watchlistService.getAllWatchlist();
		
	}
	
	@PutMapping("/createwatchlist")
	public ResponseEntity<Watchlist> upsert(@RequestBody Watchlist w){
		Watchlist response = watchlistService.createWatchlist(w);
		return ResponseEntity.ok(response);
	}
	

}

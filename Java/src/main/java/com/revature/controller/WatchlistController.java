package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Movie;
import com.revature.model.User;
import com.revature.model.Watchlist;
import com.revature.services.WatchlistService;
import com.revature.session.UserSession;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class WatchlistController {
	
	@Autowired
	private WatchlistService watchlistService;
	
	@Autowired
	private UserSession sessionUser;
	
	@GetMapping("/watchlists")
	public List<Watchlist> listAllWatchlists(){
		return watchlistService.getAllWatchlist();
		
	}
	
//	@PutMapping("/createwatchlist")
//	public ResponseEntity<Watchlist> upsert(@RequestBody Watchlist w){
//		System.out.println("reaching watchlist controller " + w);
//		
//		
//		Watchlist response = watchlistService.createWatchlist(w);
//		System.out.println("WL response: " + response);
//		return ResponseEntity.ok(response);
//	}
	
	@PutMapping("/createwatchlist")
	public Watchlist upsert(@RequestBody Watchlist watchlistCreate) throws JsonProcessingException{
		System.out.println("reaching create watchlist controller " + watchlistCreate);
		
		Watchlist newWatchlist = new Watchlist(
				watchlistCreate.getWatchlistId(),
				watchlistCreate.getWatchlistName(),
				this.sessionUser.getCurrentUser());
		this.watchlistService.createWatchlist(newWatchlist);
		
		return newWatchlist;
		
		
	}
	

	@SuppressWarnings("unchecked")
	@GetMapping("/getUserWatchlists")
	@ResponseBody 
	public List<Watchlist>  getUserWatchlists(Integer ownerId) {
		ownerId = sessionUser.getCurrentUser().getUserId();
		System.out.println("OWNER ID: " + ownerId);
		
		List<Watchlist> userWL = watchlistService.getWatchlistByUser(ownerId);
		
		return userWL;
	}
	
	
	@PostMapping("/watchlistbyname")
	public List<Movie> getWatchlistByName(@RequestBody String watchlistName) throws JsonProcessingException {
		System.out.println(watchlistName);
		ObjectMapper om = new ObjectMapper();
		String strWN = om.writeValueAsString(watchlistName);
		List<Movie> wlbn = watchlistService.getWatchlistByName(strWN);
		System.out.println(wlbn);
		
		try {
			
		}catch (RuntimeException e) {
			
		}
		
		return wlbn;
	}
	
	

}

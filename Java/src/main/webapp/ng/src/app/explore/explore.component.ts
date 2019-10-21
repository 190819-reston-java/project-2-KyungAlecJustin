import { Component, OnInit } from '@angular/core';
import { SessionUserService } from '../session-user.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-explore',
  templateUrl: './explore.component.html',
  styleUrls: ['./explore.component.css']
})
export class ExploreComponent implements OnInit {

  constructor(private http: HttpClient, private currentUser: SessionUserService) {}

  //ENDPOINTS
  sessionUserUri: String = "http://localhost:8080/cineplay/getSessionUser";
  allWatchlistsUri = "http://localhost:8080/cineplay/watchlists";
  moviesInWatchlistUri = "http://localhost:8080/cineplay/moviesinwatchlist";
  
  // //JENKINS ENDPOINTS
  // sessionUserUri: String = "http://ec2-3-92-47-77.compute-1.amazonaws.com:8080/cineplay/getSessionUser";
 
  watchlistByNameUri = "http://localhost:8080/cineplay/watchlistbyname";


  searchForWatchlist = function(event, watchlistSearch) {
    console.log("Search for Watchlist");
    event.preventDefault();
    console.log(watchlistSearch);
		if (watchlistSearch != "") {
			this.http.post(this.watchlistByNameUri, watchlistSearch).subscribe(
				(result => {
          console.log("reached method");
          console.log(result); 
					this.createdWatchlist = result;
        }))
		} else {
	  	alert("Name of the watchlist cannot be empty.");
		}
  } 
  


  allWatchlists: Object[] = [];
  matchingWatchlists: Object[] = [];
  movies: Object[] = [];

  findWatchlist = function(event, watchlistName, watchlists, matchedWatchlists) {
    event.preventDefault();
    this.matchingWatchlists = [];
    if (watchlistName.value != "") {
      for (let i = 0; i < this.allWatchlists.length; i++) {
        if (this.allWatchlists[i].watchlistName === watchlistName.value) {
          watchlists.hidden = true;
          matchedWatchlists.hidden = false;
          this.matchingWatchlists.push(this.allWatchlists[i]);
        }
      }
      watchlistName.value = "";
    } else {
      alert("Name of watchlist cannot be empty.");
    }
  }

  viewMovies = function (contentsOfWatchlist) {
    this.movies = [];
    contentsOfWatchlist.hidden = false;
    for (let i = 0; i < this.matchingWatchlists.length; i++) {
      this.http.post(this.moviesInWatchlistUri, this.matchingWatchlists[i].watchlistId).subscribe(
        (result => {
          for (let m in result) {
            this.movies.push(result[m]);
          }
        })
      )
    }
  }

  exit = function (event, searchMovies, everyWatchlists, matchedWatchlists) {
    event.preventDefault();
    searchMovies.hidden = true;
    everyWatchlists.hidden = false;
    matchedWatchlists.hidden = true;
  }

  ngOnInit() {
    this.http.get(`${this.sessionUserUri}`).subscribe(
      (response => {
        this.currentUser.setCurrentUser(response);
      })
    );

    this.allWatchlists = [];
    this.http.get(this.allWatchlistsUri).subscribe(
      (result => {
        for (let w in result) {
          this.allWatchlists.push(result[w]);
        }
      })
    )
  }

}
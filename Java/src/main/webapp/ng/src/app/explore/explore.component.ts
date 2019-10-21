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

  // //JENKINS ENDPOINTS
  // sessionUserUri: String = "http://ec2-3-92-47-77.compute-1.amazonaws.com:8080/cineplay/getSessionUser";
 
  watchlistByNameUri = "http://localhost:8080/cineplay/watchlistbyname";
  allWatchlistsUri = "http://localhost:8080/cineplay/watchlists";


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
  


  ngOnInit() {
    this.http.get(`${this.sessionUserUri}`).subscribe(
      (response => {
        this.currentUser.setCurrentUser(response);
      })
    );

    //display watchlists on feed
    let i = 0;
    this.http.get(`${this.allWatchlistsUri}`).subscribe(
      (result => {
        for (let w in result) {
        if (result[w].watchlistId % Math.round((Math.random() * 5) + 1) === 0) {
          this.allWatchlists.push(result[w].watchlistName);
          // this.allWatchlists.push(result[w].watchlistOwner.username + " created: " +result[w].watchlistName);
          i++;
          if (i > 5) {
             break;
             }
           }
         }
      })
     )
  }

}
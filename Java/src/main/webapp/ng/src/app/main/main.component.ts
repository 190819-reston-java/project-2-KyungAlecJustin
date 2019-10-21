import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {SessionUserService} from '../session-user.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})

export class MainComponent implements OnInit {

  constructor(private http: HttpClient, private currentUser: SessionUserService) {}

  featureFilm: any;
  day: any = String(new Date().getDate());
  month: any = String(new Date().getMonth() + 1);
  today: any = this.month + "/" + this.day;
  trailer: Object;
  // allWatchlists: String[] = [];

  //ENDPOINTS
  sessionUserUri: String = "http://localhost:8080/cineplay/getSessionUser";
  popularURI: String = "https://api.themoviedb.org/3/movie/now_playing?api_key=69464c49beeffbf72f4680011dafb90d&language=en-US&page=1";
  // allWatchlistsUri = "http://localhost:8080/cineplay/watchlists";

  // //JENKINS ENDPOINTS
  // sessionUserUri: String = "http://ec2-3-92-47-77.compute-1.amazonaws.com:8080/cineplay/getSessionUser";
  // allWatchlistsUri = "http://ec2-3-92-47-77.compute-1.amazonaws.com:8080/cineplay/watchlists";

  ngOnInit() {
    // set user information in current session
    this.http.get(`${this.sessionUserUri}`).subscribe(
      (response => {
        this.currentUser.setCurrentUser(response);
      })
    );

    // get currently trending/playing movie info
    let featureFilmAPICall = this.http.get(`${this.popularURI}`);
    featureFilmAPICall.subscribe((result => {
      this.featureFilm = result;
    }));

    //display watchlists on feed
    // let i = 0;
    // this.allWatchlists = [];
    // this.http.get(`${this.allWatchlistsUri}`).subscribe(
    //   (result => {
    //     for (let w in result) {
    //       if (result[w].watchlistId % Math.round((Math.random() * 5) + 1) === 0) {
    //         this.allWatchlists.push(result[w].watchlistOwner.username + " created: " + result[w].watchlistName);
    //         i++;
    //         if (i > 10) {
    //           break;
    //         }
    //       }
    //     }
    //   })
    // )
  }
}

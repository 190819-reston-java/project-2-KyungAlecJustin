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

  featureFilm: Object;
  day: String = String(new Date().getDate());
  month: String = String(new Date().getMonth() + 1);
  today: String = this.month + "/" + this.day;
  trailer: Object;
  popularURI: String = "https://api.themoviedb.org/3/movie/now_playing?api_key=69464c49beeffbf72f4680011dafb90d&language=en-US&page=1";
  sessionUserUri: String = "http://localhost:8080/cineplay/getSessionUser";

  ngOnInit() {
    this.http.get(`${this.sessionUserUri}`).subscribe(
      (response => {
        this.currentUser.setCurrentUser(response);
      })
    );

    let featureFilmAPICall = this.http.get(`${this.popularURI}`);
    featureFilmAPICall.subscribe((result => {
      console.log(result);
      this.featureFilm = result;
    }));
  }

}

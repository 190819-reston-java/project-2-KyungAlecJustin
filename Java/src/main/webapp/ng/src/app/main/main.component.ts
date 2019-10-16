import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http'

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})

export class MainComponent implements OnInit {

  constructor(private http: HttpClient) {}

  featureFilm: Object;
  day: String = String(new Date().getDate());
  month: String = String(new Date().getMonth() + 1);
  today: String = this.month + "/" + this.day;


  //Can use the following for feature film
  trendingURI: String = 'https://api.themoviedb.org/3/trending/movie/day?api_key=69464c49beeffbf72f4680011dafb90d';
  featuredURI: String = 'https://api.themoviedb.org/3/movie/popular?api_key=69464c49beeffbf72f4680011dafb90d&language=en-US&page=1'

  ngOnInit() {
      //TMDB feature film calls
      let featureFilmAPICall = this.http.get(`${this.trendingURI}`);
      featureFilmAPICall.subscribe((result => {
        this.featureFilm = result;
      }))
  }

}

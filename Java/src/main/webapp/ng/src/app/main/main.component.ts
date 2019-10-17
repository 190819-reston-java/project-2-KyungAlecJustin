import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { CONTEXT_NAME } from '@angular/compiler/src/render3/view/util';

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
  trailer: Object;

//  randomNumber: Number = Math.random * 20;

  //Can use the following for feature film
  popularURI: String = "https://api.themoviedb.org/3/movie/now_playing?api_key=69464c49beeffbf72f4680011dafb90d&language=en-US&page=1"

  ngOnInit() {
      //TMDB feature film calls
      let featureFilmAPICall = this.http.get(`${this.popularURI}`);
      featureFilmAPICall.subscribe((result => {
        console.log(result);
        this.featureFilm = result;

      // let movieId: Number = this.featureFilm.results[8].id;
      // console.log("MovieID " + movieId);

      //522938

      
      // let trailerAPI = this.http.get(`https://api.themoviedb.org/3/movie/${movieId}/videos?api_key=69464c49beeffbf72f4680011dafb90d&language=en-US`);
      // trailerAPI.subscribe((trailerresult => {
      //   console.log("trailerresults" + trailerresult);
      //      this.trailer = trailerresult;
      //     console.log("Trailer:" + this.trailer.results[1].keys);
      // }));



      
      }));

  
  }




}

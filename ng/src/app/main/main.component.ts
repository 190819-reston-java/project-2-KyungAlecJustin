import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http'

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

 // OMDBAPIURL: 'http://www.omdbapi.com/?apikey=63f27bd7&t=up'
//TheMOVIEDBURL: https://api.themoviedb.org/3/movie/550?api_key=69464c49beeffbf72f4680011dafb90d;
  OMDBbyTitle: Object;
  OMDBmovieTitle: String = 'up';

  TMDBbyMovieId: Object;
  testId: Number = 550;
  TMDBmovieId: Number = Math.random()*450000;

  featureFilm: Object;

  constructor(private http : HttpClient) { }

  ngOnInit() {
    let OMDB = this.http.get(`http://www.omdbapi.com/?apikey=63f27bd7&t=${this.OMDBmovieTitle}`)
    OMDB.subscribe((result=>{
      this.OMDBbyTitle = result;

    //TMDB call by TMDB ID
    let TMDB = this.http.get(`https://api.themoviedb.org/3/movie/${this.testId}?api_key=69464c49beeffbf72f4680011dafb90d&language=en-US`)
    TMDB.subscribe((result=>{
      this.TMDBbyMovieId = result;
    }))
    //TMDB feature film calls
    let featureFilmAPICall = this.http.get(`https://api.themoviedb.org/3/movie/popular?api_key=69464c49beeffbf72f4680011dafb90d&language=en-US&page=1
    `)
    featureFilmAPICall.subscribe((result=>{
      this.featureFilm = result;
    }))

    
    }));
  }

}

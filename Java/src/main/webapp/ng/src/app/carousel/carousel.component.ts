import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http'

@Component({
  selector: 'app-carousel',
  templateUrl: './carousel.component.html',
  styleUrls: ['./carousel.component.css']
})
export class CarouselComponent implements OnInit {

  // OMDBAPIURL: 'http://www.omdbapi.com/?apikey=63f27bd7&t=up'
  //TheMOVIEDBURL: https://api.themoviedb.org/3/movie/550?api_key=69464c49beeffbf72f4680011dafb90d;
  OMDBbyTitle: Object;
  OMDBmovieTitle: String = 'up';

  TMDBbyMovieId: Object;
  testId: Number = 550;
  TMDBmovieId: Number = Math.random() * 450000;

  featureFilm: Object;
  //Can use the following for feature film
  trendingURI: String = 'https://api.themoviedb.org/3/trending/movie/day?api_key=69464c49beeffbf72f4680011dafb90d';
  featuredURI: String = 'https://api.themoviedb.org/3/movie/popular?api_key=69464c49beeffbf72f4680011dafb90d&language=en-US&page=1'

  randomNumber: Number = Math.round(Math.random() * 19);

  constructor(private http: HttpClient) {}

  carouselArrow = function(event) {
    event.preventDefault();
  }



  ngOnInit() {
    let OMDB = this.http.get(`http://www.omdbapi.com/?apikey=63f27bd7&t=${this.OMDBmovieTitle}`)
    OMDB.subscribe((result => {
      this.OMDBbyTitle = result;

      //TMDB call by TMDB ID
      let TMDB = this.http.get(`https://api.themoviedb.org/3/movie/${this.testId}?api_key=69464c49beeffbf72f4680011dafb90d&language=en-US`)
      TMDB.subscribe((result => {
        this.TMDBbyMovieId = result;
      }))

      //TMDB feature film calls
      let featureFilmAPICall = this.http.get(`${this.trendingURI}`)
      featureFilmAPICall.subscribe((result => {
        this.featureFilm = result;
      }))


    }));

  }
}

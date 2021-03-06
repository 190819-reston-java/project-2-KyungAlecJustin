import { Component, OnInit } from '@angular/core';
import { MovieApiService } from '../movie-api.service';
import { HttpClient } from '@angular/common/http';

@Component({
	selector: 'app-watchlist',
	templateUrl: './watchlist.component.html',
	styleUrls: ['./watchlist.component.css']
})
export class WatchlistComponent implements OnInit {

	constructor(private movieApi: MovieApiService, private http: HttpClient) {}

	apiFilm: Object = {
		"Title": null,
		"Director": null,
		"Released": null,
		"Plot": null,
		"Poster": null
	}

	newFilm: Object = {
		"title": null,
		"director": null,
		"plot": null,
		"poster": null,
		"released": null
	}

	createdWatchlist: Object = {
		"watchlistName": null,
		"ownerId": null,
		"movie": null
	};

	movieUri = "http://localhost:8080/cineplay/addmovie";

	showCreate = function(createForm, viewForm) {
		createForm.hidden = false;
		viewForm.hidden = true;
	}

	showView = function(createForm, viewForm) {
		createForm.hidden = true;
		viewForm.hidden = false;
	}

	submitWatchlist = function(event, searchMovies) {
		event.preventDefault();
		console.log("Watchlist");
		let createWatchlistURI = "http://localhost:8080/cineplay/createwatchlist";
		this.http.put(createWatchlistURI).subscribe(
			(result => {
				console.log("subscribed")
				console.log(result);
				this.watchlistname = result;

			})
		)
		searchMovies.hidden = false;
	}

	submitMovie = function(event, title, movieTable, movieButton) {
		event.preventDefault();
		movieTable.hidden = false;
		movieButton.hidden = false;
		let uri = this.movieApi.getUri() + title;
		this.http.get(uri).subscribe(
			(result => {
				this.apiFilm = result;
			})
		);
	}

	addToWatchList = function(event) {
		event.preventDefault();

		this.newFilm.title = this.apiFilm.Title;
		this.newFilm.director = this.apiFilm.Director;
		this.newFilm.released = this.apiFilm.Released;
		this.newFilm.plot = this.apiFilm.Plot;
		this.newFilm.poster = this.apiFilm.Poster;
		console.log(this.newFilm);
		this.http.put(this.movieUri, this.newFilm).subscribe(
			(request => {
				console.log(request);
			})
		);
	}

	ngOnInit() {}
}
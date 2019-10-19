import { Component, OnInit } from '@angular/core';
import { MovieApiService } from '../movie-api.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { SessionUserService } from '../session-user.service';

@Component({
	selector: 'app-watchlist',
	templateUrl: './watchlist.component.html',
	styleUrls: ['./watchlist.component.css']
})
export class WatchlistComponent implements OnInit {

	constructor(private movieApi: MovieApiService, private http: HttpClient, private router: Router, private currentUser: SessionUserService) {}

	//ENDPOINTS
	sessionUserUri: String = "http://localhost:8080/cineplay/getSessionUser";

	apiFilm: any = {
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

	watchlistCreate: Object = {
		"watchlistName": null,
		"watchlistOwner": null
	};

	createdWatchlist: Object = {
		"watchlistId": null,
		"watchlistName": null,
		"watchlistOwner": null
	}

	//ENDPOINTS
	movieUri = "http://localhost:8080/cineplay/addmovie";
	createWatchlistURI = "http://localhost:8080/cineplay/createwatchlist"

	showCreate = function(createForm, viewForm) {
		createForm.hidden = false;
		viewForm.hidden = true;
	}

	showView = function(createForm, viewForm) {
		createForm.hidden = true;
		viewForm.hidden = false;
	}

	//Creates watchlist name and adds it DB
	submitWatchlist = function(event, searchMovies, createWL) {
		event.preventDefault();	
		console.log(createWL);	
		this.watchlistCreate.watchlistName = createWL;
		this.http.put(this.createWatchlistURI, this.watchlistCreate).subscribe(
			(result => {
				this.createdWatchlist = result;
				console.log(this.createdWatchlist);

			})
		)
		searchMovies.hidden = false;
	}

	//Searches and returns from API
	submitMovie = function(event, title, movieTable, movieButton, exitButton) {
		event.preventDefault();
		movieTable.hidden = false;
		movieButton.hidden = false;
		exitButton.hidden = false;
		let uri = this.movieApi.getUri() + title;
		this.http.get(uri).subscribe(
			(result => {
				this.apiFilm = result;
			})
		);
	}

	//Add to watchlist from Movie Search button
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

	exit = function(event) {
		event.preventDefault();
		window.location.reload();
	}

	ngOnInit() {
		this.http.get(`${this.sessionUserUri}`).subscribe(
			(response => {
				this.currentUser.setCurrentUser(response);
			})
		);
	}
}
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

	constructor(private movieApi: MovieApiService, private http: HttpClient, private router: Router, private currentUser: SessionUserService) { }

	//ENDPOINTS
	sessionUserUri: String = "http://localhost:8080/cineplay/getSessionUser";
	movieUri = "http://localhost:8080/cineplay/addmovie";
	createWatchlistURI = "http://localhost:8080/cineplay/createwatchlist";
	watchlistsByIdUri = "http://localhost:8080/cineplay/getUserWatchlists";
	moviesInWatchlistUri = "http://localhost:8080/cineplay/moviesinwatchlist";

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


	userWatchlists: Object[] = [];
	userWatchlistsDisplay: String[] =[];
	movies: Object[] = [];

	//Header Actions
	showCreate = function (createForm, viewLists) {
		createForm.hidden = false;
		viewLists.hidden = true;
	}

	showSearch = function (createForm, viewLists, moviesSearch) {
		createForm.hidden = true;
		viewLists.hidden = true;
		moviesSearch.hidden = false;
	}

	showView = function (createForm, viewLists) {
		createForm.hidden = true;
		viewLists.hidden = false;
		this.userWatchlists = [];
		this.userWatchlistsDisplay = [];
		if (this.currentUser.getCurrentUser() !== null) {
			this.http.get(this.watchlistsByIdUri).subscribe(
				result => {
					for (let w in result) {
						this.userWatchlists.push(result[w]);
						this.userWatchlistsDisplay.push(result[w].watchlistName);
					}
				}
			)
		} else {
			this.userWatchlistsDisplay.push("Login to see your watchlists.");
		}
	}

	//Creates watchlist name and adds it DB
	submitWatchlist = function (event, createWL) {
		event.preventDefault();
		if (this.currentUser.getCurrentUser() !== null) {
			if (createWL != "") {
				this.watchlistCreate.watchlistName = createWL;
				this.http.put(this.createWatchlistURI, this.watchlistCreate).subscribe(
					(result => {
						alert("Watchlist Created!")
						this.createdWatchlist = result;
					})
				)
			} else {
				alert("Name of the watchlist cannot be empty.");
			}
		} else {
			alert("Please login first to create a watchlist.");
		}
	}

	//Searches and returns from API
	moviesSearchBar = function (event, searchTitle, moviesSearchTable) {
		event.preventDefault();
		moviesSearchTable.hidden = false;
		let uri = this.movieApi.getUri() + searchTitle;
		this.http.get(uri).subscribe(
			(result => {
				this.apiFilm = result;
			})
		);
	}

	//Searches and returns from API
	submitMovie = function (event, title, movieTable, movieButton, exitButton) {
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

	//View movies inside watchlist
	viewMovies = function (certainWatchlist, contentsOfWatchlist) {
		console.log("View movies clicked");
		contentsOfWatchlist.hidden = false;
		this.movies = [];
		for (let i = 0; i < this.userWatchlists.length; i++) {
			if (this.userWatchlists[i].watchlistName === certainWatchlist.innerText) {
				this.http.post(this.moviesInWatchlistUri, this.userWatchlists[i].watchlistId).subscribe(
					(result => {
						for (let m in result) {
							this.movies.push(result[m]);
						}
					})
				)
			}
		}
	}

	//Add to watchlist from Movie Search button
	revealSearchMovies = function (event, searchMovies, contentsOfWatchlist) {
		console.log("Reveal Search Movies pressed")
		event.preventDefault();
		searchMovies.hidden = false;
		contentsOfWatchlist.hidden = true;
	}

	addToWatchlist = function(event) {

		event.preventDefault();
		this.newFilm.title = this.apiFilm.Title;
		this.newFilm.director = this.apiFilm.Director;
		this.newFilm.released = this.apiFilm.Released;
		this.newFilm.plot = this.apiFilm.Plot;
		this.newFilm.poster = this.apiFilm.Poster;
		console.log(this.newFilm);
		for (let i=0; i < this.userWatchlists[i].watchlistId, i++) {
			this.createdWatchlist.watchlistId = this.userWatchlists[i].watchlistId;
			this.http.put(this.movieUri, this.newFilm, this.createdWatchlist).subscribe(
				(request => {
					console.log(request);
					this.newFilm = request;
				})
			)
		}
	}



	exit = function (event, searchMovies) {
		event.preventDefault();
		searchMovies.hidden = true;
	}

	ngOnInit() {
		this.http.get(`${this.sessionUserUri}`).subscribe(
			(response => {
				this.currentUser.setCurrentUser(response);

			})

		);
	}
}
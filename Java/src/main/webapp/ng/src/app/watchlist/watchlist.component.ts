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
	movieUri = "http://localhost:8080/cineplay/addmovie";
	createWatchlistURI = "http://localhost:8080/cineplay/createwatchlist";
	watchlistsByIdUri = "http://localhost:8080/cineplay/getUserWatchlists";

	moviesInWatchlistURI = "http://localhost:8080/cineplay/moviesinwatchlist";
  //Duplicate?
 // moviesInWatchlistUri = "http://localhost:8080/cineplay/moviesinwatchlist";

	watchlistByIdUri = "http://localhost:8080/cineplay/getUserWatchlists";

	//NEW URI TO ADD MOVIE TO WATCHLIST --ALEC
	movieToWatchlistUri = "http://localhost:8080/cineplay/addmovietowatchlist";

	// //JENKINS BUILD ENDPOINTS
	// sessionUserUri: String = "http://ec2-3-92-47-77.compute-1.amazonaws.com:8080/cineplay/getSessionUser";
	// movieUri = "http://ec2-3-92-47-77.compute-1.amazonaws.com:8080/cineplay/addmovie";
	// createWatchlistURI = "http://ec2-3-92-47-77.compute-1.amazonaws.com:8080/cineplay/createwatchlist";
	// watchlistsByIdUri = "http://ec2-3-92-47-77.compute-1.amazonaws.com:8080/cineplay/getUserWatchlists";
	// moviesInWatchlistURI = "http://ec2-3-92-47-77.compute-1.amazonaws.com:8080/cineplay/moviesinwatchlist";
	// watchlistByIdUri = "http://ec2-3-92-47-77.compute-1.amazonaws.com:8080/cineplay/getUserWatchlists";

	// //NEW URI TO ADD MOVIE TO WATCHLIST --ALEC
	// movieToWatchlistUri = "http://ec2-3-92-47-77.compute-1.amazonaws.com:8080/cineplay/addmovietowatchlist";



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
	userWatchlistsDisplay: String[] = [];
	movies: Object[] = [];

	//Header Actions
	showCreate = function(createForm, viewLists) {
		createForm.hidden = false;
		viewLists.hidden = true;
	}

	showSearch = function(createForm, viewLists, moviesSearch) {
		createForm.hidden = true;
		viewLists.hidden = true;
		moviesSearch.hidden = false;
	}

	showView = function (createForm, viewLists) {
		createForm.hidden = true;
		viewLists.hidden = false;
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
	submitWatchlist = function(event, createWL) {
		event.preventDefault();
		if (this.currentUser.getCurrentUser() !== null) {
			if (createWL != "") {
				this.watchlistCreate.watchlistName = createWL;
				this.http.put(this.createWatchlistURI, this.watchlistCreate).subscribe(
					(result => {
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
	moviesSearchBar = function(event, searchTitle, moviesSearchTable) {
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

	//View movies inside watchlist
	viewMovies = function (certainWatchlist, contentsOfWatchlist) {
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
		console.log(this.movies);
	}
	
	//Add to watchlist from Movie Search button
	addToWatchList = function(event) {

		//Will remove later
		console.log("add to watchlist pressed")
		event.preventDefault();
		this.newFilm.title = this.apiFilm.Title;
		this.newFilm.director = this.apiFilm.Director;
		this.newFilm.released = this.apiFilm.Released;
		this.newFilm.plot = this.apiFilm.Plot;
		this.newFilm.poster = this.apiFilm.Poster;

		//CHANGED THIS CODE (HARDCODED)
		this.newFilm.watchlist = 1;
		console.log(this.newFilm);
		this.http.put(this.movieUri, this.newFilm).subscribe(
			(request => {

				this.newFilm = request;
				console.log(this.newFilm);
			})
		);
	}

	exit = function(event, searchMovies) {
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
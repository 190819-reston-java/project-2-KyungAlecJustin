import { Component, OnInit } from '@angular/core';
import { MovieApiService } from '../movie-api.service';
import { HttpClient } from '@angular/common/http'

@Component({
	selector: 'app-watchlist',
	templateUrl: './watchlist.component.html',
	styleUrls: ['./watchlist.component.css']
})
export class WatchlistComponent implements OnInit {

	constructor(private movieApi: MovieApiService, private http: HttpClient) {}

	film: Object = {
		"Title": null,
		"Released": null,
		"Plot": null
	}

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
		searchMovies.hidden = false;
	}

	submitMovie = function(event, title, movieTable, movieButton) {
		event.preventDefault();
		movieTable.hidden = false;
		movieButton.hidden = false;
		let uri = this.movieApi.getUri() + title;
		this.http.get(uri).subscribe(
			(result => {
				this.film = result;
			})
		);
	}

	ngOnInit() {}
}
import { Injectable } from '@angular/core';

@Injectable({
	providedIn: 'root'
})
export class MovieApiService {

	constructor() { }

	baseUri = "http://www.omdbapi.com/?apikey=63f27bd7&t=";

	getUri = function() {
		return this.baseUri;
	}
}
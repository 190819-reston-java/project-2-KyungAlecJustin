import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'

@Injectable({
	providedIn: 'root'
})
export class MovieApiService {

	constructor(private http: HttpClient) { }

	baseUri = "http://www.omdbapi.com/?apikey=63f27bd7&t=";

	getUri = function() {
		return this.baseUri;
	}
}
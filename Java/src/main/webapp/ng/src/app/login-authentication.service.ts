import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginAuthenticationService {

  constructor(private http: HttpClient) {}

  loginUri = "http://localhost:8080/login";
  getLogin() : Object {
    return this.loginUri;
  }
}

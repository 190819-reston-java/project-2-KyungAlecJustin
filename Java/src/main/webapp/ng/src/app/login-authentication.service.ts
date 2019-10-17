import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoginAuthenticationService {

  constructor() {}

  loginUri = "http://localhost:8080/login";
  getLogin() : Object {
    return this.loginUri;
  }
}
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SessionUserService {

  constructor() {
  }

  currentUser: Object = {
    "userId": null,
    "username": null,
    "usrpwd": null,
    "email": null,
    "firstName": null,
    "lastName": null
  }

  setCurrentUser = function(sessionUser) {
    this.currentUser = sessionUser;
  }

  getCurrentUser = function() {
    return this.currentUser;
  }
}

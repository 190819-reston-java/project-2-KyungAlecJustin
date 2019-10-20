import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http'
import { SessionUserService } from '../session-user.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private router: Router, private http: HttpClient, private currentUser: SessionUserService) {}

  //ENDPOINTS
  logoutUri = "http://localhost:8080/cineplay/logout"
  sessionUserUri: String = "http://localhost:8080/cineplay/getSessionUser";

  logout = function(event) {
    event.preventDefault();
    this.http.get(this.logoutUri).subscribe();
    this.router.navigate(['banner']);
  }

  ngOnInit() {
    // set user information in current session
    this.http.get(`${this.sessionUserUri}`).subscribe(
      (response => {
        this.currentUser.setCurrentUser(response);
      })
    );
  }
}

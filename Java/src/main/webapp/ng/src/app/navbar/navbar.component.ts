import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http'

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private router: Router, private http: HttpClient) {}

  //ENDPOINTS
  logoutUri = "http://localhost:8080/cineplay/logout"

  logout = function(event) {
    event.preventDefault();
    this.http.get(this.logoutUri).subscribe();
    this.router.navigate(['banner']);
  }

  ngOnInit() {}
}

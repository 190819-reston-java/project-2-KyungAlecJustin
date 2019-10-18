import { Component, OnInit } from '@angular/core';
import { SessionUserService } from '../session-user.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-explore',
  templateUrl: './explore.component.html',
  styleUrls: ['./explore.component.css']
})
export class ExploreComponent implements OnInit {

  constructor(private http: HttpClient, private currentUser: SessionUserService) {}

  sessionUserUri: String = "http://localhost:8080/cineplay/getSessionUser";

  ngOnInit() {
    this.http.get(`${this.sessionUserUri}`).subscribe(
      (response => {
        this.currentUser.setCurrentUser(response);
      })
    );
  }

}

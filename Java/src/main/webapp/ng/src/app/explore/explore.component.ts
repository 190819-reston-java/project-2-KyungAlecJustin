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

  //ENDPOINTS
  sessionUserUri: String = "http://localhost:8080/cineplay/getSessionUser";

  // //JENKINS ENDPOINTS
  // sessionUserUri: String = "http://ec2-3-92-47-77.compute-1.amazonaws.com:8080/cineplay/getSessionUser";

  ngOnInit() {
    this.http.get(`${this.sessionUserUri}`).subscribe(
      (response => {
        this.currentUser.setCurrentUser(response);
      })
    );
  }

}

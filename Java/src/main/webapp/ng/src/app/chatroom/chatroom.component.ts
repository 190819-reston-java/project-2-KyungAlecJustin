import { Component, OnInit, Renderer2, ViewChild, ElementRef } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { SessionUserService } from '../session-user.service';

@Component({
  selector: 'app-chatroom',
  templateUrl: './chatroom.component.html',
  styleUrls: ['./chatroom.component.css']
})
export class ChatroomComponent implements OnInit {

  constructor(private http: HttpClient, private currentUser: SessionUserService, private renderer: Renderer2) {}
  
  //ENDPOINTS
  allForumsUri = "http://localhost:8080/cineplay/forums";
  forumUri = "http://localhost:8080/cineplay/createforum";
  sessionUserUri: String = "http://localhost:8080/cineplay/getSessionUser";

  newMessage: Object = {
    "message": null,
    "writerId": null
  }

  pastMessages: String[] = [];
  allMessages: String[] = [];
  

  submitMessage = function(event, message, messageInput) {
    event.preventDefault();
    if (this.currentUser.getCurrentUser() !== null) {
      if (message != "") {
        this.newMessage.message = message;
        messageInput.value = "";
        this.http.put(this.forumUri, this.newMessage).subscribe(
          (response => {
            window.location.reload();

          })
        );
      } else {
        alert("Message cannot be empty.");
      }
    } else {
      alert("Please login first.");
    }
  }

  ngOnInit() {
    this.http.get(`${this.allForumsUri}`).subscribe(
      (result => {
        for (let m in result) {
          this.pastMessages.push(result[m].writerId.username + ": " + result[m].message);
        } 
      })
    );

    this.http.get(`${this.sessionUserUri}`).subscribe(
      (response => {
        this.currentUser.setCurrentUser(response);

        //Will remove later, used for testing
        console.log(response);
      })
    );

  }
}

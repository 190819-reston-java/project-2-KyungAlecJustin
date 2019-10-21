import { Component, OnInit, ElementRef, ViewChild, AfterViewChecked} from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { SessionUserService } from '../session-user.service';

@Component({
  selector: 'app-chatroom',
  templateUrl: './chatroom.component.html',
  styleUrls: ['./chatroom.component.css']
})
export class ChatroomComponent implements OnInit, AfterViewChecked {

  constructor(private http: HttpClient, private currentUser: SessionUserService) {}
  
  //ENDPOINTS
  allForumsUri = "http://localhost:8080/cineplay/forums";
  forumUri = "http://localhost:8080/cineplay/createforum";
  sessionUserUri: String = "http://localhost:8080/cineplay/getSessionUser";

    // //JENKINS ENDPOINTS
    // allForumsUri = "http://ec2-3-92-47-77.compute-1.amazonaws.com:8080/cineplay/forums";
    // forumUri = "http://ec2-3-92-47-77.compute-1.amazonaws.com:8080/cineplay/createforum";
    // sessionUserUri: String = "http://ec2-3-92-47-77.compute-1.amazonaws.com:8080/cineplay/getSessionUser";

  newMessage: Object = {
    "message": null,
    "writerId": null
  }
  pastMessages: String[] = [];
  allMessages: String[] = [];

  //trying to dynamically update the chatbox
  @ViewChild('displayMessage', {static: false}) displayMessage: ElementRef;
  
  submitMessage = function(event, message, messageInput) {
    event.preventDefault();
    if (this.currentUser.getCurrentUser() !== null) {
      if (message != "") {
        this.newMessage.message = message;
        messageInput.value = "";
        this.http.put(this.forumUri, this.newMessage).subscribe();
        this.pastMessages = [];
        this.http.get(this.allForumsUri).subscribe(
          (result => {
            for (let m in result) {
              this.pastMessages.push(result[m].writerId.username + ": " + result[m].message);
            }
            this.pastMessages.push(this.currentUser.getCurrentUser().username + ": " + this.newMessage.message);
          })
        );
        this.displayMessage.nativeElement.scrollTop = this.displayMessage.nativeElement.scrollHeight;
      } else {
        alert("Message cannot be empty.");
      }
    } else {
      alert("Please login first to send a message.");
    }
  }

  ngOnInit() {
    this.pastMessages = [];
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
      })
    );

    //this results in a console error, but still works?
    this.displayMessage.nativeElement.scrollTop = this.displayMessage.nativeElement.scrollHeight;
  }

 // this results in a console error, but still works?
  ngAfterViewChecked() {
    this.displayMessage.nativeElement.scrollTop = this.displayMessage.nativeElement.scrollHeight;
  }
}

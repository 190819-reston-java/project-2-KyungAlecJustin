import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http'

@Component({
  selector: 'app-chatroom',
  templateUrl: './chatroom.component.html',
  styleUrls: ['./chatroom.component.css']
})
export class ChatroomComponent implements OnInit {

  constructor(private http: HttpClient) {}
  message: Object;

  ngOnInit() {
    let forumUri = "http://localhost:8080/cineplay/forums"; //returns an array within an array
    this.http.get(forumUri).subscribe(
      (result => {
        this.message = result;
      })
    );
  }
}

import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-chatroom',
  templateUrl: './chatroom.component.html',
  styleUrls: ['./chatroom.component.css']
})
export class ChatroomComponent implements OnInit {

  constructor(private http: HttpClient) { }

  forumMessage: Object;


  ngOnInit() {

    let forumUri = "http://localhost:8080/cineplay/forums";
    this.http.get(forumUri).subscribe(
      (result => {
        this.forumMessage = result[0].message;
        console.log(result[0].message);
      })
    );

  }

}

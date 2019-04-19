import { Component, OnInit } from '@angular/core';

import * as Stomp from 'stompjs';
// import * as Stomp from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';



@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  serverUrl = 'http://localhost:8090/socket'
  stompClient;
  user: any;

  message = "Query";
  
  // initializeWebSocketConnection() {
  //   console.log("this method got invoked !!")
  //   let info = JSON.parse(localStorage.getItem("currentUser"));
  //   let ws = new SockJS(this.serverUrl);
  //   this.stompClient = Stomp.over(ws);
  //   let that = this;
  //   this.stompClient.connect({}, function (frame) {
  //      console.log("connected "+ frame);
  //      console.log(`/topic/${info.user.firstName}`);
  //      that.stompClient.subscribe(`/user/message`, (message) => {
  //       console.log(message);
  //       console.log("message coming from web socket   ",info.user.firstName+message);
  //     });
  //   });
  // }

 constructor(){
  // this.initializeWebSocketConnection();
 }



  admin: boolean = false;

  ngOnInit() {

    //console.log(localStorage.getItem("currentUser"), )
    let data = JSON.parse(localStorage.getItem("currentUser"));
    if (data.user.role === "ADMIN") {
      this.admin = true;
    } else {
      this.admin = false;
    }
  }

}

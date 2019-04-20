import { Injectable } from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';


@Injectable({
  providedIn: 'root'
})
export class InstantMessagingService {

  serverUrl = 'http://localhost:8090/socket'
  stompClient;
  user: any;

  message = "Query";

  constructor() {
    this.initializeWebSocketConnection();
  }

  initializeWebSocketConnection() {
    let info = JSON.parse(localStorage.getItem("currentUser"));
    let ws = new SockJS(this.serverUrl);
    this.stompClient = Stomp.over(ws);
    let that = this;
    this.stompClient.connect({}, function (frame) {
    that.stompClient.subscribe(`/user/${info.user.email}/reply`, (message) => {
      });
    });
  }

  sendMessage(query) {
    let info = JSON.parse(localStorage.getItem("currentUser"));
    this.stompClient.send("/app/message",{"userName": info.user.email}, query);  
  }
}

import { Injectable } from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import { Observable, Subject } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class InstantMessagingService {

  serverUrl = 'http://localhost:8090/socket'
  stompClient;
  user: any;

  message = "Query";
  messageList = new Subject();

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
        that.messageList.next(message.body);
        console.info("response" + message.body);
      });
    });
  }
  getMessage(): Observable<any> {
    return this.messageList.asObservable();
  }

  sendMessage(query) {
    let info = JSON.parse(localStorage.getItem("currentUser"));
    this.stompClient.send("/app/message", { "userName": info.user.email }, query);
  }
}

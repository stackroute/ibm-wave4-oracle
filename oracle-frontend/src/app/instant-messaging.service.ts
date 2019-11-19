import { Injectable } from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import {  Subject } from 'rxjs';
import {environment} from "../environments/environment";


@Injectable({
  providedIn: 'root'
})
export class InstantMessagingService {

  serverUrl = environment.botSocket;
  stompClient;
  user: any;

  message = "Query";
  messageList = new Subject();

  constructor() {

  }

  initializeWebSocketConnection() {
    var info = JSON.parse(localStorage.getItem("currentUser"));
    let ws = new SockJS(this.serverUrl);
    this.stompClient = Stomp.over(ws);
    let that = this;
    this.stompClient.connect({}, function (frame) {
      that.stompClient.subscribe(`/user/${info.user.email}/reply`, (message) => {
        that.messageList.next({ response: message.body });
        console.info("response" + message.body);
      });
    });
  }


  sendMessage(query) {
    let info = JSON.parse(localStorage.getItem("currentUser"));
    this.stompClient.send("/app/message", { "userName": info.user.email }, query);
  }

  closeConnection(){
    // this.stompClient.close();
  }
}

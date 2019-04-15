import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { HttpHeaders, HttpClient } from "@angular/common/http";

@Injectable({
  providedIn: "root"
})
export class ItChatServiceService {
  constructor(private http: HttpClient) {}
  //get chat response
  private options = {
    headers: new HttpHeaders()
      .set("Content-Type", "application/json")
      .append("Access-Control-Allow-Origin", "*")
  };
  getQuery(queryAnswer) {
    return this.http.post(
      "http://localhost:8090/api/v1/getanswer",
      queryAnswer,
      this.options
    );
  }
  saveQuery(queryAnswer) {
    return this.http.post(
      "http://localhost:8090/api/v1/saveanswer",
      queryAnswer,
      this.options
    );
  }
  var socket = new SockJS('/websocket-example');
   stompClient = Stomp.over(socket);
   stompClient.connect({}, function (frame) {
       setConnected(true);
       console.log('Connected: ' + frame);
       stompClient.subscribe('/topic/user', function (greeting) {
           showGreeting(JSON.parse(greeting.body).content);
       });
   });
function setConnected(connected) {
   $("#connect").prop("disabled", connected);
   $("#disconnect").prop("disabled", !connected);
   if (connected) {
       $("#conversation").show();
   }
   else {
       $("#conversation").hide();
   }
   $("#userinfo").html("");
}
}

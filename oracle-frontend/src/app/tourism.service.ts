import { Injectable } from "@angular/core";
//import { HttpClient, HttpParams, HttpHeaders } from "@angular/common/http";
import { environment } from "../environments/environment";
import { ApiAiClient } from "api-ai-javascript/es6/ApiAiClient";
import { BehaviorSubject, Subject } from "rxjs";

// Message class for displaying messages in the component
export class Message {
  constructor(public content: string, public sentBy: string) {}
}
@Injectable({
  providedIn: "root"
})
export class TourismService {
  readonly token = environment.dialogflow.angularBot;
  readonly client = new ApiAiClient({ accessToken: this.token });
  messages = new Subject();

  //BehaviorSubject that is an array of messages.
  conversation = new BehaviorSubject<Message[]>([]);

  constructor() {}

  /* Sends and receives messages via DialogFlow,
  the converse method adds a user message to the array, 
  then hits the API and updates the bot’s response in the same array.*/
  converse(msg: string) {
    const userMessage = new Message(msg, "user");
    this.update(userMessage);

    return this.client.textRequest(msg).then(res => {
      const speech = res.result.fulfillment["messages"];
      speech.map(e => {
        this.messages.next(e);
      });
      console.log(res, "data");
      const botMessage = new Message(speech, "bot");
      this.update(botMessage);
    });
  }
  // Adds message to source
  update(msg: Message) {
    this.conversation.next([msg]);
  }
  talk() {
    this.client.textRequest("who are you !!").then(res => console.log(res));
  }
}

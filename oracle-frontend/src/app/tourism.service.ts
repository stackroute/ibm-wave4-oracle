import { Injectable } from "@angular/core";
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

  constructor() {}

  /* Sends and receives messages via DialogFlow,
  the converse method adds a user message to the array, 
  then hits the API and updates the bot’s response in the same array.*/
  converse(msg: string) {
    return this.client.textRequest(msg).then(res => {
      const speech = res.result.fulfillment["messages"];
      speech.map(e => {
        this.messages.next(e);
      });
      console.log(res, "data");
    });
  }
}

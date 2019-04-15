import { Component, OnInit } from "@angular/core";
import { TourismService, Message } from "../tourism.service";
import { Observable } from "rxjs";
import { scan } from "rxjs/operators";

@Component({
  selector: "app-tourism-bot",
  templateUrl: "./tourism-bot.component.html",
  styleUrls: ["./tourism-bot.component.css"]
})
export class TourismBotComponent implements OnInit {
  messages: Observable<Message[]>;
  formValue: string;
  initialquestions = [];

  constructor(private chat: TourismService) {}

  ngOnInit() {
    // appends to array after each new message is added to feedSource
    this.messages = this.chat.conversation
      .asObservable()
      .pipe(scan((acc, val) => acc.concat(val)));
    this.chat.talk();
  }

  sendMessage() {
    this.chat.converse(this.formValue);
    this.formValue = "";
  }
}

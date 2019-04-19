import { Component, OnInit } from "@angular/core";
import { TourismService } from "../tourism.service";

@Component({
  selector: "app-tourism-bot",
  templateUrl: "./tourism-bot.component.html",
  styleUrls: ["./tourism-bot.component.css"]
})
export class TourismBotComponent implements OnInit {
  messages: any = [];
  formValue: string;
  mymessages: any;
  input: string;


  constructor(private chat: TourismService) {}

  ngOnInit() {

    let that = this;
    this.chat.messages.subscribe((data: any) => {
      data["query"] = this.input;
      that.messages.push(data);
      console.log(this.messages, "this is current message !!!");
    });
  }
  sendMessage() {
    this.input = this.formValue;
    this.chat.converse(this.formValue);
    this.messages.push({type: 0, ourText: this.formValue})
    this.formValue = "";
  }

  onClick(val) {
    this.chat.converse(val);
  }
}

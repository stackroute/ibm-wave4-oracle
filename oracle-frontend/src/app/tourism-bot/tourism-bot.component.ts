import { Component, OnInit, Input } from "@angular/core";
import { TourismService, Message } from "../tourism.service";
import { scan } from "rxjs/operators";

@Component({
  selector: "app-tourism-bot",
  templateUrl: "./tourism-bot.component.html",
  styleUrls: ["./tourism-bot.component.css"]
})
export class TourismBotComponent implements OnInit {
  messages: any = [];
  formValue: string;
  mymessages: any;

  constructor(private chat: TourismService) {}

  ngOnInit() {
    // appends to array after each new message is added to feedSource
    // this.messages = this.chat.conversation.asObservable()
    //   .pipe(scan((acc, val) => acc.concat(val)));

    let that = this;
    this.mymessages = this.chat.conversation;
    this.mymessages.asObservable().pipe();
    this.chat.talk();
    this.chat.messages.subscribe((data: any) => {
      that.messages.push(data);
      console.log(this.messages);
    });
  }
  sendMessage() {
    console.log(this.formValue)
    //this.input=this.formValue;
    this.chat.converse(this.formValue);
    this.formValue = "";
    
   
  }

  onClick(val) {
    console.log("val is ", val);
  }
}

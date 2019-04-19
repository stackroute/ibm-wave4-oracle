import {Component, HostListener, OnInit} from "@angular/core";
import {ItChatServiceService} from "../it-chat-service.service";
import {CdkDragDrop, moveItemInArray, transferArrayItem} from '@angular/cdk/drag-drop';
import { InstantMessagingService } from '../instant-messaging.service';


import * as Stomp from 'stompjs';
// import * as Stomp from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';
import * as $ from 'jquery';

interface Status {
  accepted: boolean;
  answered: boolean;
}

interface Query {
  question: string;
  answer: string;
}

interface UserQuery {
  queryAnswer: Query;
  status: Status;
}

@Component({
  selector: "app-chat-bot-homepage",
  templateUrl: "./chat-bot-homepage.component.html",
  styleUrls: ["./chat-bot-homepage.component.css"]
})
export class ChatBotHomepageComponent implements OnInit {
  stompClient: any;
  constructor(private chatService: ItChatServiceService, private im:InstantMessagingService) {
  }

  queryAnswer: Query = {
    question: "",
    answer: ""
  };
  status: Status = {
    accepted: false,
    answered: false
  };

  queryList: any = [];
  suggestionList: any = [];
  suggested:boolean=false;
  scrollableH;
  latestQuestion:string="";

  botItems:any=[
                  {"icon":"fas fa-cloud","name":"Weather", "rvalue":"abc"},
                  {"icon":"fas fa-globe-africa","name":"Tourism", "rvalue":"/tourism-bot"},
                  {"icon":"fas fa-film","name":"Movie", "rvalue":"xyz"}
                ];
  botBasket:any=[{"icon":"fas fa-cloud","name":"Weather"},
                {"icon":"fas fa-globe-africa","name":"Tourism", "rvalue":"/tourism-bot"},
                {"icon":"fas fa-film","name":"Movie"}
  ];


  ngOnInit() {

  }

  sendMessage(query) {
    // console.log("sending messages to websocket service");
    // console.log(queryAnswer);
    // this.stompClient.send("/app/message",{},queryAnswer);
    // console.log("messages sent to websocket service");
    this.im.sendMessage(JSON.stringify(query)); 
  }

  // chat sending and receiving
  onSubmit(value,scrollItem) {
    this.sendMessage({queryAnswer:this.queryAnswer,status:this.status});
    // // console.log("sending messages to bot service");
    // let jsonQuery = JSON.stringify({queryAnswer: this.queryAnswer, status: this.status});
    // // console.log(jsonQuery);
    // this.latestQuestion=this.queryAnswer.question;

    // // console.log("submitted" + jsonQuery);
    // this.queryList.push(JSON.parse(jsonQuery));
    // this.chatService.getQuery(jsonQuery).subscribe((value1: any) => {
    //   // console.log(value1);
    //   this.scrollableH=scrollItem.scrollHeight;
    //   value1.forEach((data) => {
    //     if(data.status.suggested){

    //      this.suggested=true;
    //       this.suggestionList.push(data);

    //     } else {
    //       this.suggestionList=[];
    //       this.queryList.push(data);
    //       this.suggested=false;

    //     }
    //   });
    // });
    // console.log(this.queryList);
    // console.log(this.suggestionList);
    value.reset();
  }

  // drag event handle
  drop(event: CdkDragDrop<any[]>) {
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {
      transferArrayItem(event.previousContainer.data,
        event.container.data,
        event.previousIndex,
        event.currentIndex);
    }
  }

  // after accepted answer
  submitAccepted(data) {
    data.status.accepted = true;
    data.queryAnswer.id = 10;
    this.chatService.saveQuery(data).subscribe((value1: any) => {
      // console.log(value1);
    });
  }

}

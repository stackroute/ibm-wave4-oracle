import { Component, OnInit } from "@angular/core";
import {ItChatServiceService} from "../it-chat-service.service";
import {CdkDragDrop, moveItemInArray, transferArrayItem} from '@angular/cdk/drag-drop';


interface Status {
  accepted: boolean;
  answered: boolean;
}
interface Query {
  question: string;
  answer: string;
}
interface UserQuery {
  query: Query;
  status: Status;
}

@Component({
  selector: "app-chat-bot-homepage",
  templateUrl: "./chat-bot-homepage.component.html",
  styleUrls: ["./chat-bot-homepage.component.css"]
})
export class ChatBotHomepageComponent implements OnInit {
  constructor(private chatService: ItChatServiceService) {}

  query: Query = {
    question: "",
    answer: ""
  };
  status: Status = {
    accepted: false,
    answered: false
  };

  queryList: any = [];
  botItems:any=[  {"icon":"fas fa-dollar-sign","name":"Stock"},
                  {"icon":"fas fa-train","name":"Train"},
                  {"icon":"fas fa-plane","name":"Air-Ticket"},
                  {"icon":"fas fa-cloud","name":"Weather"}
                ];
  botBasket:any=[{"icon":"fas fa-cloud","name":"Weather"}];

  ngOnInit() {

  }
  onSubmit(value) {
    let jsonQuery = JSON.stringify({ query: this.query, status: this.status });
    console.log("submitted" + jsonQuery);
    this.queryList.push(JSON.parse(jsonQuery));
    this.chatService.savedQuery(jsonQuery).subscribe(value1 => {
      console.log(value1);

      this.queryList.push(value1);
    });
    console.log(this.queryList);

    value.reset();
  }

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

}

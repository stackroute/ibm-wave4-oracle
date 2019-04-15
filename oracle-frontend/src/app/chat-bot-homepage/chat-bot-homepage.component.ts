import {Component, HostListener, OnInit} from "@angular/core";
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
  queryAnswer: Query;
  status: Status;
}

@Component({
  selector: "app-chat-bot-homepage",
  templateUrl: "./chat-bot-homepage.component.html",
  styleUrls: ["./chat-bot-homepage.component.css"]
})
export class ChatBotHomepageComponent implements OnInit {
  constructor(private chatService: ItChatServiceService) {}

  queryAnswer: Query = {
    question: "",
    answer: ""
  };
  status: Status = {
    accepted: false,
    answered: false
  };

  queryList: any = [];
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
  // chat sending and receiving
  onSubmit(value) {
    let jsonQuery = JSON.stringify({ queryAnswer: this.queryAnswer, status: this.status });
    console.log("submitted" + jsonQuery);
    this.queryList.push(JSON.parse(jsonQuery));
    this.chatService.getQuery(jsonQuery).subscribe((value1 :any) => {
      console.log(value1);
      value1.forEach(data=>{
      this.queryList.push(data)});
    });
    console.log(this.queryList);

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
  submitAccepted(data){
    data.status.accepted  =true;
    data.queryAnswer.id=10;
    this.chatService.saveQuery(data).subscribe((value1 :any) => {
      console.log(value1);
    });
  }

}

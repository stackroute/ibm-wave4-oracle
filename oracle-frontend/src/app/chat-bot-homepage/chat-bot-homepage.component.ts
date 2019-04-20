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
  constructor(private chatService: ItChatServiceService) {
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
  botBasket:any=[];


  ngOnInit() {

  }

  // chat sending and receiving
  onSubmit(value,scrollItem) {
    let jsonQuery = JSON.stringify({queryAnswer: this.queryAnswer, status: this.status});
    this.latestQuestion=this.queryAnswer.question;

    console.log("submitted" + jsonQuery);
    this.queryList.push(JSON.parse(jsonQuery));
    this.chatService.getQuery(jsonQuery).subscribe((value1: any) => {
      console.log(value1);
      this.scrollableH=scrollItem.scrollHeight;
      value1.forEach((data) => {
        if(data.status.suggested){

         this.suggested=true;
          this.suggestionList.push(data);

        } else {
          this.suggestionList=[];
          this.queryList.push(data);
          this.suggested=false;

        }
      });
    });
    console.log(this.queryList);
    console.log(this.suggestionList);
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
      console.log(value1);
    });
  }

}

import { Component, OnInit } from '@angular/core';
import {ChatHistoryService} from "../chat-history.service";

@Component({
  selector: 'app-chat-history',
  templateUrl: './chat-history.component.html',
  styleUrls: ['./chat-history.component.css']
})
export class ChatHistoryComponent implements OnInit {

  constructor(private chatService:ChatHistoryService) { }
  historyList:any=[];
  search:any;
  ngOnInit() {
  }

  onSubmit(value){
    this.chatService.getHistory(value).subscribe((data:any)=>{
      data.forEach(item=> {
          console.log(item);
          this.historyList.push(item);
        }
      );

    });

  }

}

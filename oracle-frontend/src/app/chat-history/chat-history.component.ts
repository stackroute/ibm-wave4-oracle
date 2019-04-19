import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-chat-history',
  templateUrl: './chat-history.component.html',
  styleUrls: ['./chat-history.component.css']
})
export class ChatHistoryComponent implements OnInit {

  constructor() { }

  search:any;
  ngOnInit() {
  }

  onSubmit(value,rm){
    console.log(value);
    console.log(rm)
  }

}

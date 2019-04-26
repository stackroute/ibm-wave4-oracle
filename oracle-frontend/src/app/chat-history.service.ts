import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from "../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class ChatHistoryService {

  constructor(private  http:HttpClient) { }

  getHistory(data){
    console.log(data);
    return this.http.post(`${environment.chatHistoryService}/getconversation`,data,{headers: new  HttpHeaders().set("Content-Type", "application/json")
      .append("Access-Control-Allow-Origin", "*")});
  }
}

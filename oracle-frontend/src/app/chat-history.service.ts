import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ChatHistoryService {

  constructor(private  http:HttpClient) { }

  getHistory(data){
    console.log(data);
    return this.http.post("http://172.23.239.126:9000/api/v1/getconversation",data,{headers: new  HttpHeaders().set("Content-Type", "application/json")
      .append("Access-Control-Allow-Origin", "*")});
  }
}

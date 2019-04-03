import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpHeaders,HttpClient} from "@angular/common/http";


@Injectable({
  providedIn: 'root'
})
export class ItChatServiceService {

  constructor(private http:HttpClient) { }
  //get chat response
  private options = { headers: new HttpHeaders().set('Content-Type', 'application/json') };
  savedQuery(query){

    return this.http.post("http://localhost:8090/api/v1/send/query",query,this.options);
  }
}

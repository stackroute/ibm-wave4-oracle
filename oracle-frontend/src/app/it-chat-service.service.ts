import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { HttpHeaders, HttpClient } from "@angular/common/http";
import {environment} from "../environments/environment";

@Injectable({
  providedIn: "root"
})
export class ItChatServiceService {
  constructor(private http: HttpClient) {}
  //get chat response
  private options = {
    headers: new HttpHeaders()
      .set("Content-Type", "application/json")
      .append("Access-Control-Allow-Origin", "*")
  };

  saveQuery(queryAnswer) {
    return this.http.post(
      `${environment.botService}/saveanswer`,
      queryAnswer,
      this.options
    );
  }

}

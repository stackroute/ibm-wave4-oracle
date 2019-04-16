import { Injectable } from "@angular/core";
import { HttpClient, HttpParams, HttpHeaders } from "@angular/common/http";

@Injectable({
  providedIn: "root"
})
export class RecastMovieServiceService {
  constructor(private http: HttpClient) {}

  httpOptions = {
    headers: new HttpHeaders({
      "Content-Type": "application/json",
      Authorization: "Token fca4da1a242af401ede19187e0d57da8"
    })
  };

  interactWithRecast(question) {
    let params = new HttpParams({});
    return this.http.post(
      "https://api.recast.ai/build/v1/dialog",
      {
        message: { type: "text", content: question },
        conversation_id: "597375b5-e4e4-465c-95f7-1f566d9f3ead"
      },
      this.httpOptions
    );
  }
}

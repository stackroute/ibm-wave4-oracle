import { Component } from "@angular/core";
import { RecastMovieServiceService } from "../recast-movie-service.service";

@Component({
  selector: "app-recast-movie",
  templateUrl: "./recast-movie.component.html",
  styleUrls: ["./recast-movie.component.css"]
})
export class RecastMovieComponent {
  constructor(private recast: RecastMovieServiceService) {}
  question;
  responseArr: any = [];
  check = false;
  askQuestion() {
    this.recast.interactWithRecast(this.question).subscribe(data => {
      console.log(data);
      console.log("normal query resp: ", this.responseArr.push(data));
      this.check = true;
    });
  }

  onClick(val) {
    this.recast.interactWithRecast(val).subscribe(data => {
      console.log(data);
      console.log("button click resp : ", this.responseArr.push(data));
      this.check = true;
    });
  }

  onClickMovieResult(val: String) {
    console.log("nothing");
    var x = val.toLowerCase();
    window.open(x, "_blank");
  }
}

// import { Component, OnInit } from '@angular/core';

// @Component({
//   selector: 'app-recast-movie',
//   templateUrl: './recast-movie.component.html',
//   styleUrls: ['./recast-movie.component.css']
// })
// export class RecastMovieComponent implements OnInit {

//   constructor() { }

//   ngOnInit() {
//   }

// }
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
      console.log("resp : ", this.responseArr.push(data));
      this.check = true;
    });
  }
}

import { Component, OnInit } from '@angular/core';



@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  user: any;

  message = "Query";

 constructor(){
 }



  admin: boolean = false;

  ngOnInit() {

    //console.log(localStorage.getItem("currentUser"), )
    let data = JSON.parse(localStorage.getItem("currentUser"));
    if (data.user.role === "ADMIN") {
      this.admin = true;
    } else {
      this.admin = false;
    }
  }

}

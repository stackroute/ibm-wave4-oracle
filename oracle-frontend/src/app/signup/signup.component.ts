import { Component, OnInit } from "@angular/core";
import { UserService } from "../user.service";
import { LoginAuthenticationService } from "../login-authentication.service";
import {FormControl, FormGroupDirective, NgForm, Validators} from '@angular/forms';
import {register} from "ts-node";
@Component({
  selector: "app-signup",
  templateUrl: "./signup.component.html",
  styleUrls: ["./signup.component.css"]
})
export class SignupComponent implements OnInit {
   user: any = {};
   registerSucessMsg: any="";
    registered:boolean=false;
  constructor(
    private userService: UserService,
    private authService: LoginAuthenticationService
  ) {

  }
  ngOnInit() {}
  saveUser(user: any, userForm: any) {
    user.enable = true;
    user.role="USER";
    this.userService.saveUser(user).subscribe(response => {
      if (response) {
        console.log(response);
        this.registered=true;
        this.registerSucessMsg = "Successfully Registered";
        userForm.reset();
      } else {
        this.registered=false;
        this.registerSucessMsg = "sorry something went wrong";
      }
    });
  }
}

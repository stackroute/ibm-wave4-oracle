import { Component, OnInit } from "@angular/core";
import { UserService } from "../user.service";
import { LoginAuthenticationService } from "../login-authentication.service";

@Component({
  selector: "app-signup",
  templateUrl: "./signup.component.html",
  styleUrls: ["./signup.component.css"]
})
export class SignupComponent implements OnInit {
  public user: any = {};
  public registerSucessMsg: any;

  constructor(
    private userService: UserService,
    private authService: LoginAuthenticationService
  ) {

  }
  ngOnInit() {}
  saveUser(user: any, userForm: any) {
    user.enable = true;
    this.userService.saveUser(user).subscribe(response => {
      if (response) {
        console.log(response);
        this.registerSucessMsg = "Successfully Registered";
        userForm.reset();
      } else {
        this.registerSucessMsg = "sorry something went wrong";
      }
    });
  }
}

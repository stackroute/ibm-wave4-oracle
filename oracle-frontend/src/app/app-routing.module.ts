import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { HomeComponent } from "./home/home.component";
import { SignupComponent } from "./signup/signup.component";
import { LoginComponent } from "./login/login.component";
import { UserdashboardComponent } from "./userdashboard/userdashboard.component";
import { AdmindashboardComponent } from "./admindashboard/admindashboard.component";
import { AuthGuard } from "./auth.guard";
import { ChatBotHomepageComponent } from "./chat-bot-homepage/chat-bot-homepage.component";
import { AllUsersProfileComponent } from "./all-users-profile/all-users-profile.component";
import { RecastMovieComponent } from "./recast-movie/recast-movie.component";
import { ProfileComponent } from "./profile/profile.component";
import { ManualAnswerComponent } from "./manual-answer/manual-answer.component";
import { TourismBotComponent } from "./tourism-bot/tourism-bot.component";

const routes: Routes = [
  { path: "home", component: HomeComponent },
  { path: "signup", component: SignupComponent },
  { path: "login", component: LoginComponent },
  { path: "manual", component: ManualAnswerComponent },
  { path: "chatbot", component: ChatBotHomepageComponent },
  { path: "recast", component: RecastMovieComponent },
  {
    path: "admindashboard",
    component: AdmindashboardComponent,
    canActivate: [AuthGuard],
    children: [
      {
        path: "profile",
        component: UserdashboardComponent
      },
      {
        path: "users",
        component: AllUsersProfileComponent
      }
    ]
  },
  { path: "profile", component: ProfileComponent },
  { path: "userdashboard", component: UserdashboardComponent },
  { path: "tourism-bot", component: TourismBotComponent },
  { path: "**", pathMatch: "full", redirectTo: "home" }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}

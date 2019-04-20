import { BrowserModule } from "@angular/platform-browser";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { NgModule } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { DragDropModule } from "@angular/cdk/drag-drop";
import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { DashboardComponent } from "./dashboard/dashboard.component";
import { HomeComponent } from "./home/home.component";
import { SignupComponent } from "./signup/signup.component";
import { LoginComponent } from "./login/login.component";
import { HttpClientModule } from "@angular/common/http";
import { UserService } from "./user.service";
import { AuthGuard } from "./auth.guard";
import { ChatBotHomepageComponent } from "./chat-bot-homepage/chat-bot-homepage.component";
import {
  MatButtonModule,
  MatButtonToggleModule,
  MatDatepickerModule,
  MatDividerModule, MatExpansionModule,
  MatInputModule,
  MatSidenavModule,
  MatTabsModule
} from "@angular/material";
import { UserdashboardComponent } from "./userdashboard/userdashboard.component";
import { AdmindashboardComponent } from "./admindashboard/admindashboard.component";
import { ProfileComponent } from './profile/profile.component';
import { AllUsersProfileComponent } from './all-users-profile/all-users-profile.component';
import { RecastMovieComponent } from './recast-movie/recast-movie.component';
import { ManualAnswerComponent } from './manual-answer/manual-answer.component';
import { TourismBotComponent } from './tourism-bot/tourism-bot.component';
import { CommonModule } from "@angular/common";
import { TourismService } from "./tourism.service";
import { InstantMessagingService } from './instant-messaging.service'
import { ChatHistoryComponent } from './chat-history/chat-history.component';

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    HomeComponent,
    SignupComponent,
    LoginComponent,
    ChatBotHomepageComponent,
    UserdashboardComponent,
    AdmindashboardComponent,
    ProfileComponent,
    AllUsersProfileComponent,
    RecastMovieComponent,
    ManualAnswerComponent,
    TourismBotComponent,
    RecastMovieComponent,
    ManualAnswerComponent,
    ChatHistoryComponent
  ],

  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    DragDropModule,
    MatSidenavModule,
    MatButtonModule,
    MatDividerModule,
    MatTabsModule,
    MatInputModule,
    MatDatepickerModule,
    MatButtonToggleModule,
    MatExpansionModule,
    CommonModule
  ],
  exports: [TourismBotComponent],
  providers: [UserService, AuthGuard,TourismService, InstantMessagingService],
  bootstrap: [AppComponent]
})
export class AppModule {}

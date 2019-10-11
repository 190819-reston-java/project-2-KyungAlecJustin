import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { FooterComponent } from './footer/footer.component';
import { SearchbarComponent } from './searchbar/searchbar.component';
import { LogoutComponent } from './logout/logout.component';
import { BannerComponent } from './banner/banner.component';
import { LoginSignupComponent } from './login-signup/login-signup.component';
import { MainComponent } from './main/main.component';
import { WatchlistComponent } from './watchlist/watchlist.component';
import { ChatroomComponent } from './chatroom/chatroom.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    FooterComponent,
    SearchbarComponent,
    LogoutComponent,
    BannerComponent,
    LoginSignupComponent,
    MainComponent,
    WatchlistComponent,
    ChatroomComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MainComponent } from './main/main.component';
import { WatchlistComponent } from './watchlist/watchlist.component';
import { ChatroomComponent } from './chatroom/chatroom.component';
import { BannerComponent } from './banner/banner.component';
import { ExploreComponent } from './explore/explore.component';


const routes: Routes = [
  {
    path: "banner",
    component: BannerComponent
  },
  {
    path: "main",
    component: MainComponent
  },
  {
    path: "watchlist",
    component: WatchlistComponent
  },
  {
    path: "explore",
    component: ExploreComponent
  },
  {
    path: "chat",
    component: ChatroomComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

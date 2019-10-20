import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-banner',
  templateUrl: './banner.component.html',
  styleUrls: ['./banner.component.css']
})
export class BannerComponent implements OnInit {
  
  loginPictures: string[] = ["bladerunner", "dunkirk", "hereditary", "interstellar", "lostintranslation",
    "shapeofwater", "spiritedaway", "theshining"];
  pictureRandomizer: number = Math.round(Math.random() * (this.loginPictures.length - 1));
  loginBanner: string = "../../assets/images/" + this.loginPictures[this.pictureRandomizer] + "-still.png";
  filmInfo: string;

  constructor() {}

  ngOnInit() {}

}
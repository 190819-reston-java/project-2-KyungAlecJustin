import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-banner',
  templateUrl: './banner.component.html',
  styleUrls: ['./banner.component.css']
})
export class BannerComponent implements OnInit {
  
  loginPictures: string[] = ["apocalypsenow", "bladerunner", "dunkirk", "her", "hereditary", "interstellar",
  "lostintranslation", "shapeofwater", "suspiria", "theshining"];
  pictureRandomizer: number = Math.round(Math.random() * (this.loginPictures.length - 1));
  loginBanner: string = "../assets/images/" + this.loginPictures[this.pictureRandomizer] + "-still.png"; //Be careful with file path of images
  filmInfo: string;

  constructor() {

    if (this.pictureRandomizer === 0) {
      this.filmInfo = 'Francis Coppola, "Apocalypse Now" (1979)';
    } else if (this.pictureRandomizer === 1) {
      this.filmInfo = 'Denis Villeneuve, "Blade Runner 2049" (2017)';
    } else if (this.pictureRandomizer === 2) {
      this.filmInfo = 'Christopher Nolan, "Dunkirk" (2017)';
    } else if (this.pictureRandomizer === 3) {
      this.filmInfo = 'Spike Jonze, "Her" (2013)';
    } else if (this.pictureRandomizer === 4) {
      this.filmInfo = 'Ari Aster, "Hereditary" (2018)';
    } else if (this.pictureRandomizer === 5) {
      this.filmInfo = 'Christopher Nolan, "Interstellar" (2014)';
    } else if (this.pictureRandomizer === 6) {
      this.filmInfo = 'Sofia Coppola, "Lost in Translation" (2003)';
    } else if (this.pictureRandomizer === 7) {
      this.filmInfo = 'Guillermo del Toro, "The Shape of Water" (2017)';
    } else if (this.pictureRandomizer === 8) {
      this.filmInfo = 'Dario Argento, "Suspiria" (1977)';
    } else if (this.pictureRandomizer === 9) {
      this.filmInfo = 'Stanley Kubrick, "The Shining" (1980)';
    }
  
  }

  ngOnInit() {}

}
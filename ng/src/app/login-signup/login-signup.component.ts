import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { HttpParams } from "@angular/common/http";
import { Router } from '@angular/router';


@Component({
	selector: 'app-login-signup',
	templateUrl: './login-signup.component.html',
	styleUrls: ['./login-signup.component.css']
})
export class LoginSignupComponent implements OnInit {
	constructor(private router: Router) {}

	username : string;
	password : string;

	showLogin = function(loginForm, signUpForm) {
		loginForm.hidden = false;
		signUpForm.hidden = true;
	}

	showSignUp = function(signUpForm, loginForm) {
		signUpForm.hidden = false;
		loginForm.hidden = true;
	}

	submitLoginInfo = function(event, username, password) {
		event.preventDefault();
		this.username = username;
		this.password = password;
		console.log(this.username, this.password);
		this.router.navigate(["main"]);
	}

	ngOnInit() {
	}
}

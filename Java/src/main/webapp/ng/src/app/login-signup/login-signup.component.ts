import { Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import { LoginAuthenticationService } from '../login-authentication.service';


@Component({
	selector: 'app-login-signup',
	templateUrl: './login-signup.component.html',
	styleUrls: ['./login-signup.component.css']
})
export class LoginSignupComponent implements OnInit {
	constructor(private router: Router, private loginAuthentication: LoginAuthenticationService) {}

	loginUri = "http://localhost:8080/cineplay/login";
	createUri = "http://localhost:8080/cineplay/createuser"

	userCreds: Object = {
		"username": null,
		"usrpwd": null,
		"email": null,
		"firstName": null,
		"lastName": null
	}

	userCreate: Object = {
		"username": null,
		"usrpwd": null,
		"email": null,
		"firstName": null,
		"lastName": null
	}



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
		this.userCreds.username = username;
		this.userCreds.usrpwd = password;
		this.loginAuthentication.http.post(this.loginUri, this.userCreds).subscribe(
			(response => {
				if (response.statusCode === "ACCEPTED") {
					this.router.navigate(['main']);
				} else {
					alert("Incorrect username or password");
				}
			})
		);
	}

	submitCreateUser = function(event, cusername, cpassword, cemail, cfirstname, clastname) {
		event.preventDefault();
		this.userCreate.username = cusername;
		this.userCreate.usrpwd = cpassword;
		this.userCreate.email = cemail;
		this.userCreate.firstName = cfirstname;
		this.userCreate.lastName = clastname;
		this.loginAuthentication.http.put(this.createUri, this.userCreate).subscribe(
			(response => {
				if (response.statuCode === "OK") {
					alert("USER CREATION SUCCESSFUL")
				} else {
					alert("USER CREATION UNSUCCESSFUL");
				}
			})
		);
	}

	ngOnInit() {
	}
}
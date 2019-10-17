package com.revature.session;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.revature.model.User;

@Component
public class UserSession {
	
	private User currentUser;
	
	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	
}

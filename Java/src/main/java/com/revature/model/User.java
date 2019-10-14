package com.revature.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "users")
@Component
public class User implements Serializable {

	private static final long serialVersionUID = 6253450157826597834L;
	
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "usrpwd")
	private String usrpwd;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	//AT Mapping CODE-------------------------------------------------------------------------------
	
	@OneToMany(mappedBy = "user")
	private List<Forum> forums;
	
	//AT Mapping CODE-------------------------------------------------------------------------------


	public User() {
		super();
	}

	public User(int userId, String username, String usrpwd, String email, String firstName, String lastName) {
		super();
		this.userId = userId;
		this.username = username;
		this.usrpwd = usrpwd;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsrpwd() {
		return usrpwd;
	}

	public void setUsrpwd(String usrpwd) {
		this.usrpwd = usrpwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, firstName, lastName, userId, username, usrpwd);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof User)) {
			return false;
		}
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(lastName, other.lastName) && userId == other.userId
				&& Objects.equals(username, other.username) && Objects.equals(usrpwd, other.usrpwd);
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", usrpwd=" + usrpwd + ", email=" + email
				+ ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
	
	
	

}

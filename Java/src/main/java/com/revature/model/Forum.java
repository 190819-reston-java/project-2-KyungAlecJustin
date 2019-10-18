package com.revature.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "forum")
//@Component
public class Forum implements Serializable {

	private static final long serialVersionUID = 1713982536513702898L;
	
	@Id
	@Column(name = "forum_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int forumId;
	
	@Column(name = "message")
	private String message;
	
	
	//AT Mapping CODE-------------------------------------------------------------------------------

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User writerId;


	public Forum() {
		super();
	}


	public Forum(int forumId, String message, User writerId) {
		super();
		this.forumId = forumId;
		this.message = message;
		this.writerId = writerId;
	}


	public int getForumId() {
		return forumId;
	}


	public void setForumId(int forumId) {
		this.forumId = forumId;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public User getWriterId() {
		return writerId;
	}


	public void setWriterId(User writerId) {
		this.writerId = writerId;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + forumId;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((writerId == null) ? 0 : writerId.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Forum other = (Forum) obj;
		if (forumId != other.forumId)
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (writerId == null) {
			if (other.writerId != null)
				return false;
		} else if (!writerId.equals(other.writerId))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Forum [forumId=" + forumId + ", message=" + message + ", writerId=" + writerId + "]";
	}
	
	//AT Mapping CODE-------------------------------------------------------------------------------

	


	
	

}

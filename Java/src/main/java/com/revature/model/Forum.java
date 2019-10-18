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
	
	@Column(name = "writer_id")
	private int writerId;
	
	@Column(name = "message")
	private String message;
	
	
	//AT Mapping CODE-------------------------------------------------------------------------------

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	//AT Mapping CODE-------------------------------------------------------------------------------


	public Forum() {
		super();
	}

	public Forum(int forumId, int writerId, String message) {
		super();
		this.forumId = forumId;
		this.writerId = writerId;
		this.message = message;
	}

	public int getForumId() {
		return forumId;
	}

	public void setForumId(int forumId) {
		this.forumId = forumId;
	}

	public int getWriterId() {
		return writerId;
	}

	public void setWriterId(int writerId) {
		this.writerId = writerId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + forumId;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + writerId;
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
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (writerId != other.writerId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Forum [forumId=" + forumId + ", writerId=" + writerId + ", message=" + message + ", user=" + user + "]";
	}


	
	

}

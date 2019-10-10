package com.revature.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "forum")
@Component
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
	
	@Column(name = "post_time")
	private String postTime;

	public Forum() {
		super();
	}

	public Forum(int forumId, int writerId, String message, String postTime) {
		super();
		this.forumId = forumId;
		this.writerId = writerId;
		this.message = message;
		this.postTime = postTime;
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

	public String getPostTime() {
		return postTime;
	}

	public void setPostTime(String postTime) {
		this.postTime = postTime;
	}

	@Override
	public int hashCode() {
		return Objects.hash(forumId, message, postTime, writerId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Forum)) {
			return false;
		}
		Forum other = (Forum) obj;
		return forumId == other.forumId && Objects.equals(message, other.message)
				&& Objects.equals(postTime, other.postTime) && writerId == other.writerId;
	}

	@Override
	public String toString() {
		return "Forum [forumId=" + forumId + ", writerId=" + writerId + ", message=" + message + ", postTime="
				+ postTime + "]";
	}
	
	

}

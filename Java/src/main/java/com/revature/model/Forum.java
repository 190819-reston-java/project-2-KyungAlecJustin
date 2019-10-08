package com.revature.model;

import java.io.Serializable;

public class Forum implements Serializable {

	private static final long serialVersionUID = 1713982536513702898L;
	
	private Long forumId;
	private Long writerId;
	private String message;
	private String postTime;

}

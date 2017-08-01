package net.zndo.training.data.entity;

import java.io.Serializable;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4580358651132223636L;
	
	private Integer id;
	private String username;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public User() {
		super();
	}

	public User(Integer id, String username) {
		super();
		this.id = id;
		this.username = username;
	}

}

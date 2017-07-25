package net.zndo.training.data.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User2 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4580358651132223636L;
	
	@Id
	@GeneratedValue
	private Integer id;
	
//	@Column(nullable=false, length=5)
	@Column(nullable=false)
	private String username;
	
	@Column(nullable=false)
	private String password;
	
	private String email;
	
	@Column(nullable=false)
	private Date create_time;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public User2() {
		super();
	}

	public User2(String username, String password, String email) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.create_time = new Date();
	}

}

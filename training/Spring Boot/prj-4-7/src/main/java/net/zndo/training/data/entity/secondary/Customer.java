package net.zndo.training.data.entity.secondary;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4580358651132223636L;
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(nullable=false)
	private String customername;
	
	private String cell_phone;
	
	@Column(nullable=false)
	private Date create_time;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getCell_phone() {
		return cell_phone;
	}

	public void setCell_phone(String cell_phone) {
		this.cell_phone = cell_phone;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Customer() {
		super();
	}

	public Customer(String customername, String cell_phone) {
		super();
		this.customername = customername;
		this.cell_phone = cell_phone;
		this.create_time = new Date();
	}

}

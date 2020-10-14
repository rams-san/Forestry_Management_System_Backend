package com.abridged.forestrymanagementsystem.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;

@Data
@Entity
@Table(name = "admin")
@JsonRootName("AdminInfo")
@JsonInclude (content = Include.NON_NULL)
public class Admin implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -6221821001379901363L;

	public Admin() {
		// No argument Constructor
	}
	
	@Id
	@Column(name="adminName")
	@JsonProperty
	private String adminName;
	
	@Column(name="adminPassword")
	@JsonProperty
	private String adminPassword;

}

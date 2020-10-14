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
@Table(name = "customer")
@JsonRootName("CustomerInfo")
@JsonInclude (content = Include.NON_NULL)
public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8079751225671935510L;

	public Customer() {
		// No argument Constructor
	}
	
	@Id
	@Column(name="customerId")
	@JsonProperty
	private String customerId;
	
	@Column(name="customerPassword")
	@JsonProperty
	private String customerPassword;
	
	@Column(name="customerName")
	@JsonProperty
	private String customerName;
	
	@Column(name="customerEmail")
	@JsonProperty
	private String customerEmail;
	
	@Column(name="customerAddress")
	@JsonProperty
	private String customerAddress;
	
	@Column(name="customerTown")
	@JsonProperty
	private String customerTown;
	
	@Column(name="customerPostalCode")
	@JsonProperty
	private String customerPostalCode;
	
	@Column(name="customerContact")
	@JsonProperty
	private String customerContact;
}

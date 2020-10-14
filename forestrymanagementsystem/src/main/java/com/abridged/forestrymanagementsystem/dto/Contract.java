package com.abridged.forestrymanagementsystem.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;

@Data
@Entity
@Table(name = "contract")
@JsonRootName("ContractInfo")
@JsonInclude (content = Include.NON_NULL)
public class Contract implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3662960915134859851L;

	@Id
	@Column(name="contractNumber")
	@JsonProperty
	private String contractNumber;
	
	@Column(name="customerId")
	@JsonProperty
	private String customerId;
	
	@Column(name="productId")
	@JsonProperty
	private String productId;
	
	@Column(name="deliveryPlace")
	@JsonProperty
	private String deliveryPlace;
	
	@Column(name="deliveryDate")
	@JsonProperty
	@JsonFormat(pattern="yyyy-MM-dd")
	private String deliveryDate;
	
	@Column(name="quantity")
	@JsonProperty
	private String quantity;
	
	@Column(name="schedulerId")
	@JsonProperty
	private String schedulerId;
}

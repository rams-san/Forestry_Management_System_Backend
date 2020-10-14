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
@Table(name = "product")
@JsonRootName("ProductInfo")
@JsonInclude (content = Include.NON_NULL)
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6185242453739280295L;

	public Product() {
		// No argument Constructor
	}
	
	@Id
	@Column(name="productId")
	@JsonProperty
	private String productId;
	
	@Column(name="productName")
	@JsonProperty
	private String productName;
	
	@Column(name="productQuantity")
	@JsonProperty
	private String productQuantity;
	
	@Column(name="productDescription")
	@JsonProperty
	private String productDescription;
}
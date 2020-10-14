package com.abridged.forestrymanagementsystem.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@Entity
@Table(name = "land")
@JsonRootName("LandInfo")
@JsonInclude (content = Include.NON_NULL)
public class Land implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 165437258374653312L;

	public Land() {
		// No argument Constructor
	}
	
	@Id
	@Column(name="surveyNumber")
	@JsonProperty
	private String surveyNumber;
	
	@Column(name="ownerName")
	@JsonProperty
	private String ownerName;
	
	@Column(name="landArea")
	@JsonProperty
	private String landArea;
	
}

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
@Table(name = "scheduler")
@JsonRootName("SchedulerInfo")
@JsonInclude (content = Include.NON_NULL)
public class Scheduler implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 468768639657425785L;

	public Scheduler() {
		// No argument Constructor
	}
	
	@Id
	@Column(name="schedulerId")
	@JsonProperty
	private String schedulerId;
	
	@Column(name="schedulerName")
	@JsonProperty
	private String schedulerName;
	
	@Column(name="schedulerContact")
	@JsonProperty
	private String schedulerContact;
	
	@Column(name="truckNumber")
	@JsonProperty
	private String truckNumber;
}

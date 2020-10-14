package com.abridged.forestrymanagementsystem.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(value=Include.NON_EMPTY, content = Include.NON_NULL)
public class LandResponse {

	private boolean isError;
	private int status;
	private String message;
	private String message1;
	private String message2;
	private String message3;
	private String message4;
	private Land land;
	private List<Land> landListInfo;
}

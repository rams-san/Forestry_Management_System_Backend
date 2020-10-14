package com.abridged.forestrymanagementsystem.service;

import java.util.List;

import com.abridged.forestrymanagementsystem.dto.Land;
import com.abridged.forestrymanagementsystem.dto.LandResponse;

public interface LandService {
	public Land serviceGetLand(String surveyNumber, LandResponse landResponse);

	public boolean serviceAddLand(Land land, LandResponse landResponse);

	public boolean serviceUpdateLand(Land land, LandResponse landResponse);

	public boolean serviceDeleteLand(String surveyNumber, LandResponse landResponse);
	
	public List<Land> serviceGetAllLands();
}

package com.abridged.forestrymanagementsystem.dao;

import java.util.List;

import com.abridged.forestrymanagementsystem.dto.Land;


public interface LandDao {
	public Land getLand(String surveyNumber);

	public boolean addLand(Land land);

	public boolean updateLand(Land land);

	public boolean deleteLand(String surveyNumber);
	
	public List<Land> getAllLands();
}

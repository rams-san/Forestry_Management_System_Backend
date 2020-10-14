package com.abridged.forestrymanagementsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abridged.forestrymanagementsystem.dao.LandDao;
import com.abridged.forestrymanagementsystem.dto.Land;
import com.abridged.forestrymanagementsystem.dto.LandResponse;
import com.abridged.forestrymanagementsystem.validation.ValidationImpl;

@Service
public class LandServiceImpl implements LandService {

	@Autowired
	private LandDao dao;

	static ValidationImpl validation = new ValidationImpl();
	
	boolean flag1 = false;
	boolean flag2 = false;
	boolean flag3 = false;

	@Override
	public Land serviceGetLand(String surveyNumber, LandResponse landResponse) {
		if (validation.idValidation(surveyNumber)) {
			landResponse.setStatus(200);
			return dao.getLand(surveyNumber);
		} else {
			landResponse.setStatus(401);
			landResponse.setMessage1("Please enter valid survey number (e.g range{1,4})");
		}
		return null;
	}

	@Override
	public boolean serviceAddLand(Land land, LandResponse landResponse) {

		if (validation.idValidation(land.getSurveyNumber())) {
			flag1 = true;
		} else {
			landResponse.setMessage1("Please enter valid survey Number (e.g range{1,4})");
		}
		if (validation.nameValidation(land.getOwnerName())) {
			flag2 = true;
		} else {
			landResponse.setMessage2("Please enter valid owner name (e.g suyash)");
		}
		if (validation.areaValidation(land.getLandArea())) {
			flag3 = true;
		} else {
			landResponse.setMessage3("Please enter valid land area customer password (e.g 15665.16)");
		}
		if (flag2 && flag3) {
			landResponse.setStatus(200);
			return dao.addLand(land);
		} else {
			landResponse.setStatus(401);
			landResponse.setMessage4("please check land properties");
		}
		return false;
	}

	@Override
	public boolean serviceUpdateLand(Land land, LandResponse landResponse) {

		if (validation.idValidation(land.getSurveyNumber())) {
			flag1 = true;
		} else {
			landResponse.setMessage1("Please enter valid survey Number (e.g range{1,4})");
		}
		if (validation.nameValidation(land.getOwnerName())) {
			flag2 = true;
		} else {
			landResponse.setMessage2("Please enter valid owner name (e.g suyash)");
		}
		if (validation.areaValidation(land.getLandArea())) {
			flag3 = true;
		} else {
			landResponse.setMessage3("Please enter valid land area customer password (e.g 15665.16)");
		}
		if (flag1 && flag2 && flag3) {
			landResponse.setStatus(200);
			return dao.updateLand(land);
		} else {
			landResponse.setStatus(401);
			landResponse.setMessage4("please check land properties");
		}
		return false;
	}

	@Override
	public boolean serviceDeleteLand(String surveyNumber, LandResponse landResponse) {
		if (validation.idValidation(surveyNumber)) {
			landResponse.setStatus(200);
			return dao.deleteLand(surveyNumber);
		} else {
			landResponse.setStatus(401);
			landResponse.setMessage1("Please enter valid survey number (e.g range{1,4})");
		}
		return false;
	}

	@Override
	public List<Land> serviceGetAllLands() {
		return dao.getAllLands();
	}

}

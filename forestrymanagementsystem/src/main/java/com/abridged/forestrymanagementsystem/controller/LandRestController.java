package com.abridged.forestrymanagementsystem.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.abridged.forestrymanagementsystem.dto.Land;
import com.abridged.forestrymanagementsystem.dto.LandResponse;
import com.abridged.forestrymanagementsystem.service.LandService;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class LandRestController {

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("yyyy-mm-dd"), true);

		binder.registerCustomEditor(Date.class, editor);
	}

	@Autowired
	private LandService service;

	/**
	 * This method is use to get land record.
	 * 
	 * @param surveyNumber is the parameter to getLandBySurveyNumber method.
	 * @return landResponse
	 */
	@GetMapping(path = "/getland", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public LandResponse getLandBySurveyNumber(String surveyNumber) {
		LandResponse landResponse = new LandResponse();
		Land info = service.serviceGetLand(surveyNumber, landResponse);

		if (info != null) {
			landResponse.setError(false);
			landResponse.setMessage("Get the record");
			landResponse.setLand(info);
		} else {
			landResponse.setError(true);
			landResponse.setMessage("Land id is not present");
			landResponse.setLand(info);
		}
		return landResponse;
	}// End of getLandBySurveyNumber()

	/**
	 * This method is use to add land record.
	 * 
	 * @param land is the parameter to addLand method.
	 * @return landResponse
	 */
	@PostMapping(path = "/addland", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public LandResponse addLand(@RequestBody Land land) {
		LandResponse landResponse = new LandResponse();
		System.out.println(land);
		boolean isAdded = service.serviceAddLand(land, landResponse);

		if (isAdded) {
			landResponse.setError(false);
			landResponse.setMessage("Land record added successfully");
		} else {
			landResponse.setError(true);
			landResponse.setMessage("Record id not added");
		}
		return landResponse;
	}// End of addLand()

	/**
	 * This method is use to delete land record.
	 * 
	 * @param surveyNumber is the parameter to deleteLandBySurveyNumber method.
	 * @return landResponse
	 */
	@DeleteMapping(path = "/deleteland", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public LandResponse deleteLandBySurveyNumber(String surveyNumber) {
		LandResponse landResponse = new LandResponse();
		boolean isDeleted = service.serviceDeleteLand(surveyNumber, landResponse);

		if (isDeleted) {
			landResponse.setError(false);
			landResponse.setMessage("Land record deleted successfully");
		} else {
			landResponse.setError(true);
			landResponse.setMessage("Record id not deleted");
		}
		return landResponse;
	}// End of deleteLandBySurveyNumber()

	/**
	 * This method is use to update land record.
	 * 
	 * @param land is the parameter to updateLand method.
	 * @return landResponse
	 */
	@PutMapping(path = "/updateland", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public LandResponse updateLand(@RequestBody Land land) {
		LandResponse landResponse = new LandResponse();
		boolean isUpdated = service.serviceUpdateLand(land, landResponse);

		if (isUpdated) {
			landResponse.setError(false);
			landResponse.setMessage("Land record updated successfully");
		} else {
			landResponse.setError(true);
			landResponse.setMessage("Record id not updated");
		}
		return landResponse;
	}// End of updateLand()

	/**
	 * This method is use to get all lands record.
	 * 
	 * @return landResponse
	 */
	@GetMapping(path = "getalllandsrecord", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public LandResponse getAllLandsDetails() {
		List<Land> listRecord = service.serviceGetAllLands();
		LandResponse landResponse = new LandResponse();
		if (listRecord != null) {
			landResponse.setError(false);
			landResponse.setMessage("All Land record");
			landResponse.setLandListInfo(listRecord);
		} else {
			landResponse.setError(true);
			landResponse.setMessage("Land record is not present");
			landResponse.setLandListInfo(listRecord);
		}
		return landResponse;
	}// End of getAllLandsDetails()
}// End of the class

package com.abridged.forestrymanagementsystem.validation;

public interface Validation {

	public boolean idValidation(String id);
	
	public boolean dateValidation(String date);
	
	public boolean quantityValidation(String quantity);
	
	public boolean descriptionValidation(String description);

	public boolean areaValidation(String area);

	public boolean contactValidation(String contact);

	public boolean nameValidation(String userName);

	public boolean truckNumberValidation(String truckNumber); 
	
	public boolean townValidation(String townName);

	public boolean emailValidation(String email);

	public boolean passwordValidation(String password);

	public boolean addressValidation(String address);
}

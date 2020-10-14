package com.abridged.forestrymanagementsystem.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationImpl implements Validation {

	Pattern pat = null;
	Matcher mat = null;

	public boolean idValidation(String id) {
		pat = Pattern.compile("([1-9]|[1-9][0-9]|[1-9][0-9][0-9]|[1-9][0-9][0-9][0-9])");
		mat = pat.matcher(id);
		return mat.matches();
	}
	
	public boolean dateValidation(String date) {
		return (date.matches("^(\\d{4})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$"));
	}
	
	public boolean quantityValidation(String quantity) {
		pat = Pattern.compile("\\b([1-9]|[1-9][0-9]|100)\\b");
		mat = pat.matcher(quantity);
		return mat.matches();
	}

	public boolean descriptionValidation(String description) {
		pat = Pattern.compile("[a-zA-Z .]*");
		mat = pat.matcher(description);
		return mat.matches();
	}
	
	public boolean truckNumberValidation(String truckNumber) {
		pat = Pattern.compile("[a-zA-Z0-9 ]*");
		mat = pat.matcher(truckNumber);
		return mat.matches();
	}
	
	public boolean contactValidation(String contact) {
		pat = Pattern.compile("\\d{10,10}");
		mat = pat.matcher(contact);
		return mat.matches();
	}

	public boolean postalCodeValidation(String postalCode) {
		pat = Pattern.compile("\\d{6}");
		mat = pat.matcher(postalCode);
		return mat.matches();
	}

	public boolean nameValidation(String userName) {
		pat = Pattern.compile("[A-Za-z ]{1,20}");
		mat = pat.matcher(userName);
		return mat.matches();
	}

	public boolean townValidation(String townName) {
		pat = Pattern.compile("[A-Za-z]{1,20}");
		mat = pat.matcher(townName);
		return mat.matches();
	}

	public boolean emailValidation(String email) {
		pat = Pattern.compile("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$");
		mat = pat.matcher(email);
		return mat.matches();
	}

	public boolean passwordValidation(String password) {
		pat = Pattern.compile("((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[*@#$%!]).{8,40})");
		mat = pat.matcher(password);
		return mat.matches();
	}

	public boolean addressValidation(String address) {
		pat = Pattern.compile("[a-zA-Z .]*");
		mat = pat.matcher(address);
		return mat.matches();
	}

	public boolean areaValidation(String area) {
		pat = Pattern.compile("[-+]?([0-9]*\\.[0-9]+|[0-9]+)");
		mat = pat.matcher(area);
		return mat.matches();
	}
}

package com.abridged.forestrymanagementsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionHandlers {
	@ExceptionHandler(value = NotFoundException.class)
	public ResponseEntity<Object> exception(NotFoundException exception) {
		return new ResponseEntity<>("Record not found", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = EmptyRecordException.class)
	public ResponseEntity<Object> exception(EmptyRecordException exception) {
		return new ResponseEntity<>("Record is empty", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = AlreadyPresentException.class)
	public ResponseEntity<Object> exception(AlreadyPresentException exception) {
		return new ResponseEntity<>("Duplicate record or record is not present", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = ContractNumberAlreadyPresentException.class)
	public ResponseEntity<Object> exception(ContractNumberAlreadyPresentException exception) {
		return new ResponseEntity<>("Contract Number is already present", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = AdminAlreadyPresentException.class)
	public ResponseEntity<Object> exception(AdminAlreadyPresentException exception) {
		return new ResponseEntity<>("Admin Name is already present", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = SchedulerAlreadyPresentException.class)
	public ResponseEntity<Object> exception(SchedulerAlreadyPresentException exception) {
		return new ResponseEntity<>("Scheduler Id is already present", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = ProductAlreadyPresentException.class)
	public ResponseEntity<Object> exception(ProductAlreadyPresentException exception) {
		return new ResponseEntity<>("Product Id is already present", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = LandAlreadyPresentException.class)
	public ResponseEntity<Object> exception(LandAlreadyPresentException exception) {
		return new ResponseEntity<>("Survey Number is already present", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = CustomerIdAlreadyPresentException.class)
	public ResponseEntity<Object> exception(CustomerIdAlreadyPresentException exception) {
		return new ResponseEntity<>("Customer Id is already present", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = OrderNumberAlreadyPresentException.class)
	public ResponseEntity<Object> exception(OrderNumberAlreadyPresentException exception) {
		return new ResponseEntity<>("Order Number is already present", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = SchedulerIdAlreadyPresentException.class)
	public ResponseEntity<Object> exception(SchedulerIdAlreadyPresentException exception) {
		return new ResponseEntity<>("Scheduler Id is already present", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = ValidationException.class)
	public ResponseEntity<Object> exception(ValidationException exception) {
		return new ResponseEntity<>("Id or Password is wrong", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = SchedulerIdAbsentException.class)
	public ResponseEntity<Object> exception(SchedulerIdAbsentException exception) {
		return new ResponseEntity<>("Scheduler Id is not present in scheduler table", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = ContractNumberAbsentException.class)
	public ResponseEntity<Object> exception(ContractNumberAbsentException exception) {
		return new ResponseEntity<>("Contract Number is not present in contract table", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = ProductIdAbsentException.class)
	public ResponseEntity<Object> exception(ProductIdAbsentException exception) {
		return new ResponseEntity<>("Product Id is not present in Product table", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = CustomerIdAbsentException.class)
	public ResponseEntity<Object> exception(CustomerIdAbsentException exception) {
		return new ResponseEntity<>("Customer Id is not present in Customer table", HttpStatus.NOT_FOUND);
	}
}
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
import com.abridged.forestrymanagementsystem.dto.Contract;
import com.abridged.forestrymanagementsystem.dto.ContractResponse;
import com.abridged.forestrymanagementsystem.service.ContractService;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ContractRestController {

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("yyyy-mm-dd"), true);

		binder.registerCustomEditor(Date.class, editor);
	}

	@Autowired
	private ContractService service;

	/**
	 * This method is use to get contract record.
	 * 
	 * @param contractNumber is the parameter to getContractByContractNumber method.
	 * @return contractResponse
	 */
	@GetMapping(path = "/getcontract", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ContractResponse getContractByContractNumber(String contractNumber) {
		ContractResponse contractResponse = new ContractResponse();
		Contract info = service.serviceGetContract(contractNumber, contractResponse);

		if (info != null) {
			contractResponse.setError(false);
			contractResponse.setMessage("Get the record");
			contractResponse.setContract(info);
		} else {
			contractResponse.setError(true);
			contractResponse.setMessage("Contract id is not present");
			contractResponse.setContract(info);
		}
		return contractResponse;
	}// End of getContractByContractNumber()

	/**
	 * This method is use to add contract record.
	 * 
	 * @param contract is the parameter to addContract method.
	 * @return contractResponse
	 */
	@PostMapping(path = "/addcontract", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ContractResponse addContract(@RequestBody Contract contract) {
		ContractResponse contractResponse = new ContractResponse();
		System.out.println(contract);
		boolean isAdded = service.serviceAddContract(contract, contractResponse);

		if (isAdded) {
			contractResponse.setError(false);
			contractResponse.setMessage("Contract record added successfully");
		} else {
			contractResponse.setError(true);
			contractResponse.setMessage("Record id not added");
		}
		return contractResponse;
	}// End of addContract()

	/**
	 * This method is use to delete contract record.
	 * 
	 * @param contractNumber is the parameter to deleteContractByContractNumber
	 *                       method.
	 * @return contractResponse
	 */
	@DeleteMapping(path = "/deletecontract", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ContractResponse deleteContractByContractNumber(String contractNumber) {
		ContractResponse contractResponse = new ContractResponse();
		boolean isDeleted = service.serviceDeleteContract(contractNumber, contractResponse);

		if (isDeleted) {
			contractResponse.setError(false);
			contractResponse.setMessage("Contract record deleted successfully");
		} else {
			contractResponse.setError(true);
			contractResponse.setMessage("Record id not deleted");
		}
		return contractResponse;
	}// End of deleteContractByContractNumber()

	/**
	 * This method is use to update contract record.
	 * 
	 * @param contract is the parameter to updateContract method.
	 * @return contractResponse
	 */
	@PutMapping(path = "/updatecontract", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ContractResponse updateContract(@RequestBody Contract contract) {
		ContractResponse contractResponse = new ContractResponse();
		boolean isUpdated = service.serviceUpdateContract(contract, contractResponse);

		if (isUpdated) {
			contractResponse.setError(false);
			contractResponse.setMessage("Contract record updated successfully");
		} else {
			contractResponse.setError(true);
			contractResponse.setMessage("Record id not updated");
		}
		return contractResponse;
	}// End of updateContract()

	/**
	 * This method is use to get all contracts record.
	 * 
	 * @return contractResponse
	 */
	@GetMapping(path = "/getallcontractsrecord", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ContractResponse getAllContractsDetails() {
		List<Contract> listRecord = service.serviceGetAllContracts();
		ContractResponse contractResponse = new ContractResponse();
		if (listRecord != null) {
			contractResponse.setError(false);
			contractResponse.setMessage("All Customer record");
			contractResponse.setContractListInfo(listRecord);
		} else {
			contractResponse.setError(true);
			contractResponse.setMessage("Customer record is not present");
			contractResponse.setContractListInfo(listRecord);
		}
		return contractResponse;
	}// End of getAllContractsDetails

}// End of the class
